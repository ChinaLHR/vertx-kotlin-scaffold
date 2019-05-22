package io.github.lhr.core.verticle

import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.github.lhr.core.conf.HttpConf
import io.github.lhr.core.conf.httpConf
import io.github.lhr.core.conf.mysqlConf
import io.github.lhr.core.dao.jdbcClient
import io.vertx.core.Vertx
import io.vertx.core.json.Json
import io.vertx.core.json.JsonObject
import io.vertx.ext.jdbc.JDBCClient
import io.vertx.kotlin.coroutines.CoroutineVerticle
import org.slf4j.LoggerFactory
import org.slf4j.LoggerFactory.getLogger


/**
 * @author lhr
 * @date 2019/4/20
 * 通用Verticle 提供通用配置获取
 *
 */
abstract class CoreVerticle : CoroutineVerticle() {

    override suspend fun start() {
        init()
        runStart()
    }

    private fun init() {
        //logger
        System.setProperty("vertx.logger-delegate-factory-class-name", "io.vertx.core.logging.SLF4JLogDelegateFactory");
        val logger = getLogger(LoggerFactory::class.java)
        Json.mapper.registerModule(KotlinModule())
        //配置Config
        setConf(config)
        //initJdbcClient
        initJdbcClient(vertx)
        logger.info("Init CoreVerticle Success,Conf:{} ",
                config.toString())
    }

    private fun setConf(conf: JsonObject) {
        val httpJsonObj = conf.getJsonObject("http")
        httpConf = httpJsonObj.mapTo(HttpConf::class.java)
        mysqlConf = conf.getJsonObject("mysql")
    }

    private fun initJdbcClient(vertx: Vertx){
        jdbcClient = JDBCClient.createShared(vertx, mysqlConf)
    }

    abstract suspend fun runStart()
}