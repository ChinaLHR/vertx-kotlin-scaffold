package io.github.lhr.api.handler

import io.github.lhr.core.dao.UserDao
import io.github.lhr.core.entity.User
import io.github.lhr.core.ext.ok
import io.vertx.ext.web.RoutingContext


/**
 * @author lhr
 * @date 2019/4/23
 */
class UserHandler {

    private val userDao = UserDao()

    suspend fun findById(ctx: RoutingContext) {
        val id = ctx.request().getParam("id")
        val result = userDao.read<User>(id)
        ctx.ok(result)
    }

    suspend fun insertUser(ctx: RoutingContext) {
        userDao.save(ctx.bodyAsJson)
        ctx.ok("操作成功")
    }

    suspend fun findAll(ctx: RoutingContext) {
        val result = userDao.list<User>()
        ctx.ok(result)
    }

}