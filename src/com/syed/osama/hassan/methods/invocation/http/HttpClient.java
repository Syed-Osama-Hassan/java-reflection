package com.syed.osama.hassan.methods.invocation.http;

public class HttpClient {
    private String sererAddress;

    public HttpClient(String sererAddress) {
        this.sererAddress = sererAddress;
    }

    public boolean sendRequest(String data) {
        System.out.println(String.format(
                "Request with body: %s successfully sent to server %s",
                data,
                sererAddress
        ));

        return true;
    }
}
