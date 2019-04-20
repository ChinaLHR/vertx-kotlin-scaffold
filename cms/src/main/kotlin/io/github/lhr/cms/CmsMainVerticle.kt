package io.github.lhr.cms

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future


/**
 * @author lhr
 * @date 2019/4/14
 */
class CmsMainVerticle : AbstractVerticle() {

    override fun start(startFuture: Future<Void>) {
        val port = config().getInteger("port")

        vertx
                .createHttpServer()
                .requestHandler { req ->
                    req.response()
                            .putHeader("content-type", "text/plain")
                            .end("Hello from Vert.x-Stack Cms!")
                }
                .listen(port) { http ->
                    if (http.succeeded()) {
                        startFuture.complete()
                        println("HTTP server started on port 10020")
                    } else {
                        startFuture.fail(http.cause())
                    }
                }

    }

}