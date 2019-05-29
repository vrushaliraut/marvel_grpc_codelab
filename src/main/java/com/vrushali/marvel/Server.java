package com.vrushali.marvel;

import com.gojek.ApplicationConfiguration;
import com.gojek.Figaro;
import com.vrushali.marvel.factory.MarvelServiceFactory;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Logger;

public class Server {
    private static final Logger logger = Logger.getLogger(Server.class.getName());
    private io.grpc.Server server;

    private void start() throws IOException {
        int port = 50051;
        ApplicationConfiguration appConfig = Figaro.configure(null);
        server = ServerBuilder.forPort(port)
                .addService(MarvelServiceFactory.instance(appConfig, true))
                .build()
                .start();
        logger.info("server start, listening on port " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(Server.this::stop));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final Server server = new Server();
        server.start();
        server.blockUntilShutdown();
    }
}