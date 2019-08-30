package io.github.lhr.api.handler

import io.github.lhr.core.dao.UserDao
import io.github.lhr.core.domain.entity.User
import io.github.lhr.core.domain.entity.converters.ModelConverter
import io.github.lhr.core.domain.vo.PageVO
import io.github.lhr.core.domain.vo.UserVO
import io.github.lhr.core.ext.ok
import io.vertx.core.json.JsonObject
import io.vertx.ext.mongo.FindOptions
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
        val user = ModelConverter.fromJson<UserVO>(ctx.bodyAsJson)
        userDao.save(user)
        ctx.ok("操作成功")
    }

    suspend fun findAll(ctx: RoutingContext) {
        val result = userDao.list<User>()
        ctx.ok(result)
    }

    /**
     * 翻页查询
     * 传统分页思路:skip()+limit()
     */
    suspend fun pageTurn(ctx: RoutingContext) {

        val pageVO = ModelConverter.fromJson<PageVO>(ctx.bodyAsJson)
        val findOptions = FindOptions()
                .setLimit(pageVO.pageSize)
                .setSkip(pageVO.pageNum - 1)

        val result = userDao.findWithOption<User>(findOptions)
        ctx.ok(result)
    }

    /**
     * 翻页查询V2版本
     * Seek Method分页思路:
     */
    suspend fun pageTurnV2(ctx: RoutingContext){

    }
}