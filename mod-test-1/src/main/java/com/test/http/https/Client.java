package com.test.http.https;

import com.test.util.Runner;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpClientOptions;

public class Client extends AbstractVerticle {

    public static void main(String[] args) {
        Runner.runExample(Client.class);
    }


    @Override
    public void start() throws Exception {
        // Note! in real-life you wouldn't often set trust all to true as it could leave you open to man in the middle attacks.
        /*
            추가 설명 :
            If the trustALl is set to true on the client, then the client will trust all server certificates.
            The connection will still be encrypted but this mode is vulnerable to 'man in the middle' attacks.
            I.e. you can’t be sure who you are connecting to. Use this with caution. Default value is false.
         */
        vertx.createHttpClient(
                new HttpClientOptions()
                        .setSsl(true)
                        .setTrustAll(true)
        ).getNow(4443, "localhost", "/",
                resp -> {
                    System.out.println("Got Response + " + resp.statusCode());
                    resp.bodyHandler(body -> System.out.println("Got Data" + body.toString("ISO_8859-1")));
                }
        );
    }
}
