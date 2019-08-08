package io.github.lhr.api.handler

import io.github.lhr.core.dao.UserDao
import io.github.lhr.core.entity.User
import io.github.lhr.core.exception.BusinessException
import io.github.lhr.core.ext.ok
import io.konform.validation.Valid
import io.konform.validation.Validation
import io.konform.validation.jsonschema.minimum
import io.vertx.ext.web.RoutingContext
import sun.plugin.util.UserProfile


/**
 * @author lhr
 * @date 2019/4/23
 */
class UserHandler {

    private val userDao = UserDao()

    private val validateUserId = Validation<User> {
        User::id {
            minimum(1) hint "id需要大于0"
        }
    }

    suspend fun findById(ctx: RoutingContext) {
        val id = ctx.request().getParam("id")
        val result = userDao.getById(id)
        ctx.ok(result)
    }

    suspend fun insertUser(ctx: RoutingContext) {
        val name = ctx.request().getParam("name")
        val sexType = ctx.request().getParam("sexType").toInt()
        userDao.insertUser(name, sexType)
        ctx.ok("操作成功")
    }

    suspend fun updateUser(ctx: RoutingContext) {
        val user = ctx.bodyAsJson.mapTo(User::class.java)
        validateUserId.validate(user)[User::id]?.let {
            throw BusinessException(it.toString())
        }
        userDao.updateById(user)
        ctx.ok("操作成功")
    }

    suspend fun findAll(ctx: RoutingContext) {
        val result = userDao.getAll()
        ctx.ok(result)
    }

}