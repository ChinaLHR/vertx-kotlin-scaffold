package io.github.lhr.api.routes

import io.github.lhr.api.handler.UserHandler
import io.github.lhr.core.common.CoroutineRoute
import io.github.lhr.core.ext.ok
import io.vertx.core.Vertx
import io.vertx.ext.web.Router


/**
 * @author lhr
 * @date 2019/5/20
 */

class Routes(vertx: Vertx) : CoroutineRoute(vertx) {

    fun initRoute(router: Router) {
        val userHandler = UserHandler()
        router.mountSubRouter("/api", router)
        router.get("/user/findAll").coroutineHandler { ctx -> userHandler.findAll(ctx) }
        router.get("/user/findOne").coroutineHandler { ctx -> userHandler.findById(ctx) }
        router.post("/user/update").coroutineHandler { ctx -> userHandler.updateUser(ctx) }
        router.get("/user/save").coroutineHandler { ctx -> userHandler.insertUser(ctx) }
    }
}