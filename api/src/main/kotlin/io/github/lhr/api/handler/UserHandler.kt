package io.github.lhr.api.handler

import io.github.lhr.core.dao.user.UserDao
import io.vertx.ext.web.RoutingContext


/**
 * @author lhr
 * @date 2019/4/23
 */
class UserHandler {

    private val userDao = UserDao()

    fun findById(ctx: RoutingContext) {
//        userDao.
    }

    fun insertUser(ctx: RoutingContext) {

    }

    fun updateUser(ctx: RoutingContext) {

    }

    fun findAll(ctx: RoutingContext) {

    }

}