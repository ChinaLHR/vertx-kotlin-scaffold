package io.github.lhr.core.verticle

import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.github.lhr.core.conf.HttpConf
import io.github.lhr.core.conf.httpConf
import io.github.lhr.core.conf.mongoDbConf
import io.github.lhr.core.dao.base.MongoDbPool
import io.github.lhr.core.dao.base.mongoClient
import io.vertx.core.json.Json
import io.vertx.core.json.JsonObject
import io.vertx.ext.mongo.MongoClient
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

        setConf(config)
        mongoClient = MongoClient.createShared(vertx, mongoDbConf)

        logger.info("Init CoreVerticle Success,Conf:{} ",
                config.toString())
    }

    /**
     * 配置conf
     */
    private fun setConf(conf: JsonObject) {
        mongoDbConf = conf.getJsonObject("mongoDB")
        val httpJsonObj = conf.getJsonObject("http")
        httpConf = httpJsonObj.mapTo(HttpConf::class.java)
    }


    abstract suspend fun runStart()
}