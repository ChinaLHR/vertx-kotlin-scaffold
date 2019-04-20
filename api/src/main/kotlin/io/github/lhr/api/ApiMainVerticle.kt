package io.github.lhr.api

import io.github.lhr.core.verticle.CoreVerticle
import io.github.lhr.core.verticle.httpConf
import io.vertx.core.Future


/**
 * @author lhr
 * @date 2019/4/14
 */
class ApiMainVerticle : CoreVerticle() {
    override fun runStart(startFuture: Future<Void>) {

        vertx
                .createHttpServer()
                .requestHandler { req ->
                    req.response()
                            .putHeader("content-type", "text/plain")
                            .end("Hello from Vert.x-Stack Api!")
                }
                .listen(httpConf.port.api) { http ->
                    if (http.succeeded()) {
                        startFuture.complete()
                        println("HTTP server started on port ${httpConf.port.api}")
                    } else {
                        startFuture.fail(http.cause())
                    }
                }

    }

}