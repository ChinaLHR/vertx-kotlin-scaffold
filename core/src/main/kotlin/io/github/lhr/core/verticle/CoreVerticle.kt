package io.github.lhr.core.verticle

import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.github.lhr.core.conf.HttpConf
import io.github.lhr.core.conf.httpConf
import io.github.lhr.core.conf.mysqlConf
import io.github.lhr.core.dao.jdbcClient
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.Vertx
import io.vertx.core.json.Json
import io.vertx.core.json.JsonObject
import io.vertx.ext.jdbc.JDBCClient
import org.slf4j.LoggerFactory
import org.slf4j.LoggerFactory.getLogger


/**
 * @author lhr
 * @date 2019/4/20
 * 通用Verticle 提供通用配置获取
 *
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
        //initJdbcClient
        initJdbcClient(vertx)
        logger.info("Init CoreVerticle Success,Conf:{} ",
                conf.toString())
    }

    private fun setConf(conf: JsonObject) {
        val httpJsonObj = conf.getJsonObject("http")
        httpConf = httpJsonObj.mapTo(HttpConf::class.java)
        mysqlConf = conf.getJsonObject("mysql")
    }

    private fun initJdbcClient(vertx: Vertx){
        jdbcClient = JDBCClient.createShared(vertx, mysqlConf)
//                .createShared(vertx, mysqlConf)
    }

    abstract fun runStart(startFuture: Future<Void>)
}