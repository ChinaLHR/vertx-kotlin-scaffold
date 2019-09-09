package io.github.lhr.api

import io.github.lhr.api.verticle.ApiMainVerticle
import io.github.lhr.api.verticle.ApiProxyVerticle
import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject
import io.vertx.kotlin.core.deployVerticleAwait
import org.slf4j.LoggerFactory
import java.io.File


/**
 * @author lhr
 * @date 2019/5/24
 * 本地调试用 idea启动需要配置Environment variables
 * k-> conf v-> 参考script
 */

fun main() {
    val logger = LoggerFactory.getLogger(LoggerFactory::class.java)

    val file = File(System.getenv("conf"))
    val jsonStr = file.readText(Charsets.UTF_8)
    val conf = JsonObject(jsonStr)
    val options = DeploymentOptions().setConfig(conf)
    val vertx = Vertx.vertx()
    vertx.deployVerticle(ApiMainVerticle(), options) { ar ->

        if (ar.succeeded()) {
            logger.info("Main ApiMainVerticle Started")
        } else {
            logger.error("Main ApiMainVerticle Error", ar.cause())
            System.exit(0)
        }
    }

    vertx.deployVerticle(ApiProxyVerticle(),options){
        ar ->
        if (ar.succeeded()) {
            logger.info("Main ApiProxyVerticle Started")
        } else {
            logger.error("Main ApiProxyVerticle Error", ar.cause())
            System.exit(0)
        }
    }
}