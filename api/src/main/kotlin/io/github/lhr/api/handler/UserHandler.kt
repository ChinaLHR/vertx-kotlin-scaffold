package io.github.lhr.api.handler

import io.github.lhr.core.dao.UserDao
import io.github.lhr.core.entity.User
import io.github.lhr.core.exception.BusinessException
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
        val result = userDao.getById(id)
        ctx.ok(result)
    }

    suspend fun insertUser(ctx: RoutingContext) {
        val name = ctx.request().getParam("name")
        userDao.insertUser(name)
        ctx.ok("操作成功")
    }

    suspend fun updateUser(ctx: RoutingContext) {
        val user = ctx.bodyAsJson.mapTo(User::class.java)
        user ?: throw BusinessException("输入参数为Null")
        userDao.updateById(user)
        ctx.ok("操作成功")
    }

    suspend fun findAll(ctx: RoutingContext) {
        val result = userDao.getAll()
        ctx.ok(result)
    }

}