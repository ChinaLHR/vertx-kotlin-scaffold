package io.github.lhr.cms

import io.github.lhr.core.conf.httpConf
import io.github.lhr.core.verticle.CoreVerticle
import io.vertx.core.Future


/**
 * @author lhr
 * @date 2019/4/14
 */
class CmsMainVerticle : CoreVerticle() {
    override fun runStart(startFuture: Future<Void>) {
        vertx
                .createHttpServer()
                .requestHandler { req ->
                    req.response()
                            .putHeader("content-type", "text/plain")
                            .end("Hello from Vert.x-Stack Cms!")
                }
                .listen(httpConf.port.cms) { http ->
                    if (http.succeeded()) {
                        startFuture.complete()
                        println("HTTP server started on port ${httpConf.port.cms}")
                    } else {
                        startFuture.fail(http.cause())
                    }
                }
    }


}