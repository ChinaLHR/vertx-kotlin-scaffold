package io.github.lhr.api.routes

import io.github.lhr.core.common.CoroutineRoute
import io.vertx.core.Vertx
import io.vertx.ext.web.Router


/**
 * @author lhr
 * @date 2019/5/20
 */

class Routes(val vertx: Vertx): CoroutineRoute(vertx) {
    fun createRouter(): Router {
        val subRouter = Router.router(vertx)

    }
}