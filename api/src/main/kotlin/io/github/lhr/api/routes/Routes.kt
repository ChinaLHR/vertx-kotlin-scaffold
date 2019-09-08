package io.github.lhr.api.routes

import io.github.lhr.api.handler.UserHandler
import io.github.lhr.api.service.proxy.UserServiceEventBusProxy
import io.github.lhr.api.service.proxy.UserServiceProxyImpl
import io.github.lhr.core.common.CoroutineRoute
import io.vertx.core.Vertx
import io.vertx.ext.web.Router


/**
 * @author lhr
 * @date 2019/5/20
 */

class Routes(val vertx: Vertx) : CoroutineRoute(vertx) {

    fun initRoute(router: Router) {
        val userHandler = UserHandler(vertx)
        val userHandlerProxy = UserHandler(vertx, true)

        //method call handler
        router.get("/api/user/findAll").coroutineHandler { ctx -> userHandler.findAll(ctx) }
        router.get("/api/user/findOne").coroutineHandler { ctx -> userHandler.findById(ctx) }
        router.get("/api/user/save").coroutineHandler { ctx -> userHandler.insertUser(ctx) }
        router.get("/api/user/listByPageV1").coroutineHandler { ctx -> userHandler.pageTurn(ctx) }
        router.get("/api/user/listByPageV2").coroutineHandler { ctx -> userHandler.pageTurnV2(ctx) }

        //event bus call handler
        router.get("/proxy/user/findAll").coroutineHandler { ctx -> userHandlerProxy.findAll(ctx) }
        router.get("/proxy/user/findOne").coroutineHandler { ctx -> userHandlerProxy.findById(ctx) }
        router.get("/proxy/user/save").coroutineHandler { ctx -> userHandlerProxy.insertUser(ctx) }
        router.get("/proxy/user/listByPageV1").coroutineHandler { ctx -> userHandlerProxy.pageTurn(ctx) }
        router.get("/proxy/user/listByPageV2").coroutineHandler { ctx -> userHandlerProxy.pageTurnV2(ctx) }

    }
}
