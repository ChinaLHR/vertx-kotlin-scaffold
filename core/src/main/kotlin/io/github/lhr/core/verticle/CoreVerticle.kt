package io.github.lhr.core.verticle

import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.github.lhr.core.dao.DbPool
import io.github.lhr.core.domain.conf.HttpConf
import io.github.lhr.core.domain.conf.MySqlConf
import io.github.lhr.core.domain.conf.httpConf
import io.github.lhr.core.domain.conf.mysqlConf
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.json.Json
import io.vertx.core.json.JsonObject
import org.slf4j.LoggerFactory
import org.slf4j.LoggerFactory.getLogger


/**
 * @author lhr
 * @date 2019/4/20
 * 通用Verticle 提供通用配置获取
 *
 * TODO 健康检查
 */
abstract class CoreVerticle : AbstractVerticle() {

    override fun start(startFuture: Future<Void>) {
        init()
        runStart(startFuture)
    }

    private fun init() {
        //logger
        System.setProperty("vertx.logger-delegate-factory-class-name", "io.vertx.core.logging.SLF4JLogDelegateFactory");
        val logger = getLogger(LoggerFactory::class.java)
        Json.mapper.registerModule(KotlinModule())
        val conf = config()
        //配置Config
        setConf(conf)
        //init dbPool
        DbPool.INSTANCE

        logger.info("Init CoreVerticle Success,Conf:{} ",
                conf.toString())
    }

    private fun setConf(conf: JsonObject) {
        val httpJsonObj = conf.getJsonObject("http")
        httpConf = httpJsonObj.mapTo(HttpConf::class.java)
        val mysqlJsonObj = conf.getJsonObject("mysql")
        mysqlConf = mysqlJsonObj.mapTo(MySqlConf::class.java)
    }


    abstract fun runStart(startFuture: Future<Void>)
}