package com.test.http.https;

import com.test.util.Runner;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.net.JksOptions;

public class Server extends AbstractVerticle {
    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        Runner.runExample(Server.class);
    }

    @Override
    public void start() throws Exception {
      // https://vertx.io/docs/vertx-core/java/#_writing_http_servers_and_clients
        /*String path = "/src/main/java/" + this.getClass()
                .getPackage().getName().replace(".", "/") +
                "/server-keystore.jks";*/
        String path = "/Users/gonghojin/Documents/git/vertx/mod-test-1/src/main/java/com/test/http/https/server-keystore.jks";
        HttpServer server = vertx.createHttpServer(
                new HttpServerOptions().setSsl(true).setKeyStoreOptions(
                        new JksOptions().setPath(path).setPassword("wibble")
                )
        );

        server.requestHandler(req -> {
            req.response().putHeader("content-type", "text/html").end("<html><body><h1>Hello from vert.x!</h1></body></html>");
        }).listen(4443);
    }
}
