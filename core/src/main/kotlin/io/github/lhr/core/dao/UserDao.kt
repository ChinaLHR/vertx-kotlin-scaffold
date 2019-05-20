package io.github.lhr.core.dao

import io.github.lhr.core.entity.User
import io.vertx.kotlin.ext.sql.queryAwait


/**
 * @author lhr
 * @date 2019/5/14
 */
class UserDao {

    suspend fun getAll(): List<User> {
        return jdbcClient.queryAwait(sql = "select * from user")
                .rows
                .map {
                    it.mapTo(User::class.java)
                }
    }



}