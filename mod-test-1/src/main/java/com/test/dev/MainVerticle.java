package com.test.dev;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

public class MainVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        super.start();
        System.out.println("#####################");
        System.out.println("### Start " + this.getClass().getSimpleName());

        Vertx vertx = Vertx.vertx();
        learnEventBus(vertx);

    }

    public void learnEventBus(Vertx vertx) {
        /**
         * 이벤트 버스는 하나 이상의 핸들러를 가질 수 있다.
         */
        EventBus eb = vertx.eventBus();

        // 핸들러 등록
        eb.consumer("news.uk.sport", message -> {
            System.out.println("I have received a message: " + message.body());
        });


    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.out.println("#####################");
        System.out.println("### End " + this.getClass().getSimpleName());
    }

}
