package io.github.lhr.core.common

import io.vertx.core.Vertx
import io.vertx.ext.web.Route
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


/**
 * @author lhr
 * @date 2019/5/20
 */

open class CoroutineRoute(vertx: Vertx): CoroutineScope {
    override val coroutineContext: CoroutineContext by lazy { vertx.dispatcher() }

    fun Route.coroutineHandler(fn:suspend (RoutingContext) -> Unit){
        handler {ctx ->
            launch {
                try {
                    fn(ctx)
                } catch (e: Exception) {
                    e.printStackTrace()
                    ctx.fail(e)
                }
            }
        }
    }
}