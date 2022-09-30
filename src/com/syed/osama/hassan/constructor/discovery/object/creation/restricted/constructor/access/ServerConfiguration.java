package com.syed.osama.hassan.constructor.discovery.object.creation.restricted.constructor.access;

import java.net.InetSocketAddress;

public class ServerConfiguration {
    private static ServerConfiguration instance;
    private final InetSocketAddress address;
    private final String greetingMesage;

    private ServerConfiguration(int port, String greetingMesage) {
        this.address = new InetSocketAddress("localhost", port);
        this.greetingMesage = greetingMesage;

        if(instance == null) {
            instance = this;
        }
    }

    public static ServerConfiguration getInstance() {
        return instance;
    }

    public InetSocketAddress getAddress() {
        return address;
    }

    public String getGreetingMesage() {
        return greetingMesage;
    }
}
