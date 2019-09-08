package io.github.lhr.api.handler

import io.github.lhr.api.service.UserService
import io.github.lhr.api.service.impl.UserServiceImpl
import io.github.lhr.core.dao.UserDao
import io.github.lhr.core.domain.entity.User
import io.github.lhr.core.domain.entity.converters.ModelConverter
import io.github.lhr.core.domain.vo.IdPageVO
import io.github.lhr.core.domain.vo.PageVO
import io.github.lhr.core.domain.vo.UserVO
import io.github.lhr.core.ext.ok
import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject
import io.vertx.ext.mongo.FindOptions
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.core.json.json
import io.vertx.kotlin.core.json.obj
import org.slf4j.LoggerFactory


/**
 * @author lhr
 * @date 2019/4/23
 */
class UserHandler(val vertx: Vertx, usesEventBus: Boolean = false) {

    private val userService = getService(usesEventBus)

    suspend fun findById(ctx: RoutingContext) {
        val id = ctx.request().getParam("id")
        val result = userService.findById(id)
        ctx.ok(result)
    }

    suspend fun insertUser(ctx: RoutingContext) {
        val user = ModelConverter.fromJson<UserVO>(ctx.bodyAsJson)
        userService.insertUser(user)
        ctx.ok("操作成功")
    }

    suspend fun findAll(ctx: RoutingContext) {
        val result = userService.findAll()
        ctx.ok(result)
    }

    /**
     * 翻页查询
     * 传统分页思路:skip()+limit()
     */
    suspend fun pageTurn(ctx: RoutingContext) {

        val pageVO = ModelConverter.fromJson<PageVO>(ctx.bodyAsJson)
        val result = userService.pageTurn(pageVO)
        ctx.ok(result)
    }

    /**
     * 翻页查询V2版本
     * Seek Method分页思路:
     * lastId = -1 表示第一页
     */
    suspend fun pageTurnV2(ctx: RoutingContext) {
        val idPageVO = ModelConverter.fromJson<IdPageVO>(ctx.bodyAsJson)
        val result = userService.pageTurnV2(idPageVO)
        ctx.ok(result)
    }

    private fun getService(usesEventBus: Boolean): UserService {
        if (usesEventBus) {
            return UserService.createProxy(this.vertx)
        }
        return UserServiceImpl(this.vertx)
    }
}
