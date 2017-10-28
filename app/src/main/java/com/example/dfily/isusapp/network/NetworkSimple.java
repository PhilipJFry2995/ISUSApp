package com.example.dfily.isusapp.network;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by dfily on 28.10.2017.
 */

public class NetworkSimple implements NetworkInterface {

    @Override
    public void sendGetRequest(String url, NetworkUpdatable updatable) {
        new NetworkAsync(url, updatable).execute();
    }

    @Override
    public void sendPostRequest(String url, Map<String, String> parameters, NetworkUpdatable updatable) {
        new NetworkAsync(url, parameters, updatable).execute();
    }

    private class NetworkAsync extends AsyncTask<Void, Void, Void> {

        private String url;
        private Map<String, String> parameters;
        private NetworkUpdatable updatable;
        private String method = "GET";

        public NetworkAsync(String url, NetworkUpdatable updatable) {
            this.url = url;
            this.updatable = updatable;
        }

        public NetworkAsync(String url, Map<String, String> parameters, NetworkUpdatable updatable) {
            this.url = url;
            this.parameters = parameters;
            this.updatable = updatable;
            this.method = "POST";
        }

        @Override
        protected Void doInBackground(Void... voids) {
            HttpURLConnection urlConnection = null;
            try {
                URL url1 = new URL(url);
                urlConnection = (HttpURLConnection) url1.openConnection();
                urlConnection.setRequestMethod(method);

                if(parameters != null){
                    OutputStream os = urlConnection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    writer.write(parseParameters(parameters));
                    writer.flush();
                    writer.close();
                }

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader r = new BufferedReader(new InputStreamReader(in));
                StringBuilder total = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    total.append(line);
                }
                updatable.update(total.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return null;
        }

        String parseParameters(Map<String, String> parameters){
            StringBuilder result = new StringBuilder();
            for(String key : parameters.keySet()){
                if(result.toString().isEmpty()){
                    result.append(key).append("=").append(parameters.get(key));
                } else {
                    result.append("&").append(key).append("=").append(parameters.get(key));
                }
            }
            return result.toString();
        }
    }
}
