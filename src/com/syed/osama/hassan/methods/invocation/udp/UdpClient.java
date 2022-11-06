package com.syed.osama.hassan.methods.invocation.udp;

public class UdpClient {

    public void sendAndForget(String requestPayload) {
        System.out.println(String.format("Request: %s sent through UDP", requestPayload));
    }

}
