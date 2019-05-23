package io.github.lhr.core.ext

import io.vertx.core.json.Json
import io.vertx.ext.web.RoutingContext


/**
 * @author lhr
 * @date 2019/5/20
 */
fun RoutingContext.ok(result: Any) {
    this.response()
            .putHeader("content-type","application/json")
            .setStatusCode(200)
            .end(Json.encodePrettily(result))
}