package com.example.dfily.isusapp.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dfily on 28.10.2017.
 */

public class NetworkVolley implements NetworkInterface {

    private Context context;

    public NetworkVolley(Context context) {
        this.context = context;
    }

    @Override
    public void sendGetRequest(String url, final NetworkUpdatable updatable) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        updatable.update(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error != null && error.getMessage() != null) {
                    updatable.update(error.getMessage());
                } else {
                    Log.e("mylogs", "Ошибка подключения к серверу");
                }
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @Override
    public void sendPostRequest(String url, final Map<String, String> parameters, final NetworkUpdatable updatable) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        updatable.update(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error != null && error.getMessage() != null) {
                            updatable.update(error.getMessage());
                        } else {
                            Log.e("mylogs", "Ошибка подключения к серверу");
                        }
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                return parameters;
            }
        };
        queue.add(postRequest);
    }

}
