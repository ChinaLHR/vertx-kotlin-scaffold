package io.github.lhr.api.handler

import io.github.lhr.core.ext.ok
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext


/**
 * @author lhr
 * @date 2019/5/25
 */
class GlobalErrorHandler : Handler<RoutingContext> {

    override fun handle(ctx: RoutingContext) {
        //todo 全局异常 可针对状态码
        ctx.ok("系统繁忙")
    }
}