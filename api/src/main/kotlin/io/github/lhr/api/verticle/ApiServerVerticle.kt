package io.github.lhr.api.verticle

import io.github.lhr.api.handler.GlobalErrorHandler
import io.github.lhr.api.routes.Routes
import io.github.lhr.core.conf.httpConf
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.*
import io.vertx.kotlin.core.http.listenAwait
import io.vertx.kotlin.coroutines.CoroutineVerticle


/**
 * @author lhr
 * @date 2019/4/14
 */
class ApiServerVerticle : CoroutineVerticle() {
    override suspend fun start() {
        val router = Router.router(vertx)
        //可接受body参数
        router.route().handler(BodyHandler.create())
        //设置response类型为json类似于response..putHeader("Content-Type", "application/json")
        router.route().handler(ResponseContentTypeHandler.create())
        //请求日志记录
        router.route().handler(LoggerHandler.create(LoggerFormat.DEFAULT))
        //程序处理超时时间
        router.route().handler(TimeoutHandler.create(5000))
        //限流 使用Nginx进行ip限流处理
        //router.route().handler(LimitHandler())
        //全局异常处理
        router.route().last().handler(GlobalErrorHandler())
        Routes(vertx).initRoute(router)
        vertx.createHttpServer()
                .requestHandler(router)
                .listenAwait(httpConf.port.api)
    }
}
