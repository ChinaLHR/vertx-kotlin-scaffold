package io.github.lhr.api.routes

import io.github.lhr.api.handler.UserHandler
import io.github.lhr.core.common.CoroutineRoute
import io.vertx.core.Vertx
import io.vertx.ext.web.Router


/**
 * @author lhr
 * @date 2019/5/20
 */

class Routes(val vertx: Vertx) : CoroutineRoute(vertx) {

    fun initRoute(router: Router){
        val userHandler = UserHandler()

        val subRouter = Router.router(vertx)
        val apply = Router.router(vertx).apply {
            subRouter.route("/User/findAll").coroutineHandler { ctx -> userHandler.findAll(ctx) }
        }

        router.mountSubRouter("/api", apply)
    }
}