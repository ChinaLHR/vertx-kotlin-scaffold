package io.github.lhr.api.verticle

import io.github.lhr.api.service.UserService
import io.vertx.kotlin.coroutines.CoroutineVerticle

/**
 * @Author : lhr
 * @Date : 11:28 2019/9/9
 */
class ApiProxyVerticle: CoroutineVerticle(){

    override suspend fun start() {
        UserService.createProxyHandler(vertx)
    }
}