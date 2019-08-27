package io.github.lhr.core.dao

import io.github.lhr.core.dao.base.AbstractDao
import io.github.lhr.core.entity.User
import io.vertx.core.Vertx

/**
 * @Author : lhr
 * @Date : 11:52 2019/8/27
 */
class UserDao(vertx: Vertx) : AbstractDao<User, String>(vertx, "user")