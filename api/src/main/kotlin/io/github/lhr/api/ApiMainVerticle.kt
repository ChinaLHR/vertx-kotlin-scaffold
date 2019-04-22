package io.github.lhr.api

import io.github.lhr.core.domain.conf.httpConf
import io.github.lhr.core.verticle.CoreVerticle
import io.vertx.core.Future


/**
 * @author lhr
 * @date 2019/4/14
 */
class ApiMainVerticle : CoreVerticle() {


    override fun runStart(startFuture: Future<Void>) {

        vertx.createHttpServer()
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