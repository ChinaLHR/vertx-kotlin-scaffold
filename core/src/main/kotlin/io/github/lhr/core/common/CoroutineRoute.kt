package io.github.lhr.core.common

import io.github.lhr.core.exception.BusinessException
import io.github.lhr.core.ext.ok
import io.vertx.core.Vertx
import io.vertx.ext.web.Route
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import java.lang.IllegalArgumentException
import kotlin.coroutines.CoroutineContext


/**
 * @author lhr
 * @date 2019/5/20
 */

open class CoroutineRoute(vertx: Vertx) : CoroutineScope {
    override val coroutineContext: CoroutineContext by lazy { vertx.dispatcher() }

    fun Route.coroutineHandler(fn: suspend (RoutingContext) -> Unit) {
        val logger = LoggerFactory.getLogger(LoggerFactory::class.java)
        handler { ctx ->
            launch {
                try {
                    fn(ctx)
                } catch (e: Exception) {
                    //todo 业务异常捕获处理
                    logger.error("Handler Error URL:{}", ctx.request().uri(), e)

                    when (e) {
                        is IllegalArgumentException -> ctx.ok("数据缺失")
                        is BusinessException -> e.message?.let { ctx.ok(it) }
                        else -> ctx.ok("系统繁忙")
                    }
                }
            }
        }
    }
}