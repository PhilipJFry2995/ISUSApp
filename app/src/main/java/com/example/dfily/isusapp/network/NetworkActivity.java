package com.example.dfily.isusapp.network;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dfily.isusapp.R;

import java.util.HashMap;
import java.util.Map;

public class NetworkActivity extends AppCompatActivity implements NetworkUpdatable {

    TextView logsView;
    NetworkInterface network;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        logsView = (TextView) findViewById(R.id.logView);

        //network = new NetworkVolley(this);
        network = new NetworkSimple();
    }

    @Override
    public void update(final String response) {
        runOnUiThread(()->{
            String text = logsView.getText().toString();
            text = response + "\n" + text;
            logsView.setText(text);
        });
    }

    public void sendCoordinates(View view) {
        EditText address = (EditText) findViewById(R.id.ip);
        String url = "http://" + address.getText().toString() + "/coordinates";
        Map<String, String> parameters = new HashMap<>();
        EditText latitude = (EditText) findViewById(R.id.latitude);
        EditText longitude = (EditText) findViewById(R.id.longitude);
        parameters.put("latitude", latitude.getText().toString());
        parameters.put("longitude", longitude.getText().toString());
        network.sendPostRequest(url, parameters, this);
    }

    public void getCoordinates(View view) {
        EditText address = (EditText) findViewById(R.id.ip);
        String url = "http://" + address.getText().toString() + "/coordinates";
        network.sendGetRequest(url, this);
    }

}
