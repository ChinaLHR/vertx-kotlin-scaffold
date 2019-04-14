package io.github.lhr.api

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future


/**
 * @author lhr
 * @date 2019/4/14
 */
class MainVerticle : AbstractVerticle() {

    override fun start(startFuture: Future<Void>) {
        vertx
                .createHttpServer()
                .requestHandler { req ->
                    req.response()
                            .putHeader("content-type", "text/plain")
                            .end("Hello from Vert.x-Stack Api!")
                }
                .listen(10010) { http ->
                    if (http.succeeded()) {
                        startFuture.complete()
                        println("HTTP server started on port 10010")
                    } else {
                        startFuture.fail(http.cause())
                    }
                }

    }

}