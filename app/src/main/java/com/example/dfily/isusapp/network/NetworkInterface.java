package com.example.dfily.isusapp.network;

import java.util.Map;

/**
 * Created by dfily on 28.10.2017.
 */

public interface NetworkInterface {

    void sendGetRequest(String url, NetworkUpdatable updatable);

    void sendPostRequest(String url, Map<String, String> parameters, NetworkUpdatable updatable);

}
