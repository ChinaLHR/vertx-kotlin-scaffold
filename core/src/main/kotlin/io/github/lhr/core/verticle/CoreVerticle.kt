package io.github.lhr.core.verticle

import io.github.lhr.core.conf.HttpConf
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.json.JsonObject


lateinit var httpConf: HttpConf

/**
 * @author lhr
 * @date 2019/4/20
 * 通用Verticle
 */
abstract class CoreVerticle : AbstractVerticle() {

    override fun start(startFuture: Future<Void>) {
        val port = config().getJsonObject("http").getJsonObject("port")
        //配置Config
        setConf(port)
        runStart(startFuture)
    }

    //todo 引入jackson
    private fun setConf(port: JsonObject) {
        httpConf = HttpConf
        httpConf.port.api = port.getInteger("api")
        httpConf.port.cms = port.getInteger("cms")
    }

    abstract fun runStart(startFuture: Future<Void>)
}