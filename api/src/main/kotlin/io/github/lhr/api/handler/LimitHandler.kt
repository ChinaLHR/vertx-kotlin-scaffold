package io.github.lhr.api.handler

import com.google.common.util.concurrent.RateLimiter
import io.github.lhr.core.ext.ok
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import org.slf4j.LoggerFactory


/**
 * @author lhr
 * @date 2019/5/28
 *
 * 限流Handler
 * todo lhr 待优化 只针对部分handler做限流
 */
class LimitHandler : Handler<RoutingContext> {

    val logger = LoggerFactory.getLogger(LoggerFactory::class.java)

    /**
     * key = uri-host value rateLimiter
     */
    var ipLimitMap = mutableMapOf<String, RateLimiter>()

    override fun handle(ctx: RoutingContext) {
        val host = ctx.request().remoteAddress().host()
        val rateLimiter = ipLimitMap[host]

        if (rateLimiter==null){
            /**
             * ip qps限制为10
             */
            val limiter = RateLimiter.create(10.0)
            ipLimitMap[host] = limiter

        }else{
            val tryAcquire = rateLimiter.tryAcquire()
            if (!tryAcquire) {
                ctx.ok("请求限制,请稍后重试")
                return
            }
        }
        ctx.next()
    }


}