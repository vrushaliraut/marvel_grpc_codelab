package com.vrushali.marvel;

import com.gojek.ApplicationConfiguration;
import com.gojek.Figaro;
import com.sun.javafx.collections.UnmodifiableListSet;
import com.vrushali.marvel.factory.MarvelServiceFactory;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Server {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private io.grpc.Server server;

    private void start() throws IOException {
        int port = 8080;
        final ApplicationConfiguration appConfig =
                Figaro.configure(new UnmodifiableListSet<>(getMandatoryVariables()));
        server = ServerBuilder.forPort(port)
                .addService(MarvelServiceFactory.instance(appConfig))
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

    private static List<String> getMandatoryVariables() {
        return Arrays.asList("DB_HOST",
                "DB_PASSWORD",
                "DB_USERNAME",
                "LEAK_THRESHOLD",
                "DB_MAX_POOL_SIZE",
                "DB_NAME",
                "DB_PORT",
                "MARVEL_TIMEOUT_IN_SEC");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final Server server = new Server();
        server.start();
        server.blockUntilShutdown();
    }
}