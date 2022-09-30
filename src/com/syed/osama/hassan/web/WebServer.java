package com.syed.osama.hassan.web;

import com.sun.net.httpserver.HttpServer;
import com.syed.osama.hassan.constructor.discovery.object.creation.restricted.constructor.access.ServerConfiguration;

import java.io.IOException;
import java.io.OutputStream;

public class WebServer {

    public void startServer() throws IOException {
        HttpServer httpServer = HttpServer.create(
                ServerConfiguration.getInstance().getAddress(),
                0
        );

        httpServer.createContext("/greeting").setHandler(exchange -> {
            String responseMessage = ServerConfiguration.getInstance().getGreetingMesage();
            exchange.sendResponseHeaders(200, responseMessage.length());

            OutputStream os = exchange.getResponseBody();
            os.write(responseMessage.getBytes());
            os.flush();
            os.close();
        });

        System.out.println(String.format("Starting server on %s:%d",
                    ServerConfiguration.getInstance().getAddress().getHostName(),
                    ServerConfiguration.getInstance().getAddress().getPort()
                ));

        httpServer.start();
    }
}
