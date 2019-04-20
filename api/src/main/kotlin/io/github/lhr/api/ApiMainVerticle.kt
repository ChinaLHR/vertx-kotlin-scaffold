package io.github.lhr.api

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future


/**
 * @author lhr
 * @date 2019/4/14
 */
class ApiMainVerticle : AbstractVerticle() {

    override fun start(startFuture: Future<Void>) {

        val port = config().getInteger("port")
        vertx
                .createHttpServer()
                .requestHandler { req ->
                    req.response()
                            .putHeader("content-type", "text/plain")
                            .end("Hello from Vert.x-Stack Api!")
                }
                .listen(port) { http ->
                    if (http.succeeded()) {
                        startFuture.complete()
                        println("HTTP server started on port $port")
                    } else {
                        startFuture.fail(http.cause())
                    }
                }

    }

}