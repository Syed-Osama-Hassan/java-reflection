package com.syed.osama.hassan.dynamicproxy.external;

public interface HttpClient {

    void initialize();
    String sendRequest(String request);
}
