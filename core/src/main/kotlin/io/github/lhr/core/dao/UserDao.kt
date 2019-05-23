package io.github.lhr.core.dao

import io.github.lhr.core.entity.User
import io.vertx.core.json.JsonArray
import io.vertx.kotlin.ext.sql.queryAwait
import io.vertx.kotlin.ext.sql.queryWithParamsAwait
import io.vertx.kotlin.ext.sql.updateWithParamsAwait


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

    suspend fun getById(id: String): User {
        return jdbcClient.queryWithParamsAwait("select * from user where id = ?", JsonArray(listOf(id)))
                .rows
                .map {
                    it.mapTo(User::class.java)
                }
                .first()
    }

    suspend fun updateById(user: User) {
        val param = JsonArray(listOf(user.name, user.id))
        jdbcClient.updateWithParamsAwait("update user set name = ? where id = ?", param)
    }

    suspend fun insertUser(name: String) {
        jdbcClient.updateWithParamsAwait("insert into user (name) values (?)", JsonArray(listOf(name)))
    }

}