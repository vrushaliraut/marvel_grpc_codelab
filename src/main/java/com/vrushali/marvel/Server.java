package com.vrushali.marvel;

import com.gojek.ApplicationConfiguration;
import com.gojek.Figaro;
import com.vrushali.marvel.factory.MarvelServiceFactory;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Server {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private io.grpc.Server server;

    private void start() throws IOException {
        int port = 8080;
        ApplicationConfiguration appConfig = Figaro.configure(null);
        server = ServerBuilder.forPort(port)
                .addService(MarvelServiceFactory.instance(appConfig, true))
                .build()
                .start();
        logger.debug("server start, listening on port ", port);

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