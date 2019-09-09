package io.github.lhr.api.verticle

import io.github.lhr.core.verticle.CoreVerticle
import io.vertx.core.DeploymentOptions
import io.vertx.kotlin.core.deployVerticleAwait
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.Exception

/**
 * @Author : lhr
 * @Date : 14:32 2019/9/9
 */
class ApiMainVerticle : CoreVerticle() {

    override suspend fun runStart() {

        try {
            vertx.deployVerticleAwait("io.github.lhr.api.verticle.ApiProxyVerticle",
                    DeploymentOptions().setConfig(config).setInstances(2))
            vertx.deployVerticleAwait("io.github.lhr.api.verticle.ApiServerVerticle",
                    DeploymentOptions().setConfig(config).setInstances(2))
            log.info("deployVerticle Success")
        } catch (ex: Exception) {
            log.info("deployVerticle Fail", ex)
        }

    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(ApiMainVerticle::class.java)
    }
}