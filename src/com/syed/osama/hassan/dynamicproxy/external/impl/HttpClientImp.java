package com.syed.osama.hassan.dynamicproxy.external.impl;

import com.syed.osama.hassan.dynamicproxy.external.HttpClient;

public final class HttpClientImp implements HttpClient {

    @Override
    public void initialize() {
        System.out.println("Initializing http client");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String sendRequest(String request) {
        System.out.println("Sending request " + request);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Received response");
        return "Some response data";
    }
}
