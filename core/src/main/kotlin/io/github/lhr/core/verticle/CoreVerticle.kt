package io.github.lhr.core.verticle

import io.github.lhr.core.conf.HttpConf
import io.github.lhr.core.conf.HttpConf.port
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.json.JsonObject


lateinit var httpConf: HttpConf

/**
 * @author lhr
 * @date 2019/4/20
 * 通用Verticle 提供通用配置获取
 */
abstract class CoreVerticle : AbstractVerticle() {

    override fun start(startFuture: Future<Void>) {
        val conf = config()

        //配置Config
        setConf(conf)
        runStart(startFuture)
    }

    private fun setConf(conf: JsonObject) {
        val httpJsonObj = conf.getJsonObject("http")
        setHttpConf(httpJsonObj)
    }

    private fun setHttpConf(httpJsonObj: JsonObject) {
        httpConf = httpJsonObj.mapTo(HttpConf.javaClass)
    }

    abstract fun runStart(startFuture: Future<Void>)
}