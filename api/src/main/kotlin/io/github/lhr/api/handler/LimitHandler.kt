package io.github.lhr.api.handler

import com.google.common.util.concurrent.RateLimiter
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import org.slf4j.LoggerFactory


/**
 * @author lhr
 * @date 2019/5/28
 *
 * 限流Handler
 */
class LimitHandler : Handler<RoutingContext> {

    val logger = LoggerFactory.getLogger(LoggerFactory::class.java)

    var ipLimitMap = mapOf<String, RateLimiter>()

    override fun handle(event: RoutingContext) {
        val host = event.request().remoteAddress().host()
        val uri = event.request().uri()
        logger.info("key:$host:$uri")
        event.next()
    }


}