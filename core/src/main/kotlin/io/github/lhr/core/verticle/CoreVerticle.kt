package io.github.lhr.core.verticle

import io.github.lhr.core.dao.DbPool
import io.github.lhr.core.domain.conf.HttpConf
import io.github.lhr.core.domain.conf.MySqlConf
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.json.JsonObject


lateinit var httpConf: HttpConf
lateinit var mysqlConf: MySqlConf

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
        //init dbPool
        DbPool.INSTANCE

        runStart(startFuture)
    }

    private fun setConf(conf: JsonObject) {
        val httpJsonObj = conf.getJsonObject("http")
        httpConf = httpJsonObj.mapTo(HttpConf.javaClass)
        val mysqlJsonObj = conf.getJsonObject("mysql")
        mysqlConf = mysqlJsonObj.mapTo(MySqlConf.javaClass)
    }


    abstract fun runStart(startFuture: Future<Void>)
}