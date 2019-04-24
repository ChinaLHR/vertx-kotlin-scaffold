package io.github.lhr.core.dao.user

import io.github.lhr.core.dao.DbPool
import io.github.lhr.core.domain.user.User
import java.util.concurrent.TimeUnit


/**
 * @author lhr
 * @date 2019/4/23
 */
class UserDao {

    private var pool = DbPool.INSTANCE

    fun findById(id: Long): User {
        val result = pool.connectionPool
                .connect()
                .get(5000, TimeUnit.MILLISECONDS)
                .sendPreparedStatement("select id,name from user where id = ?", listOf(id))

        val user = result.get()
                .rows
                .map { User(it.getLong("id")!!, it.getString("name")!!) }
                .first()

        return user
    }

    fun insertUser(name: String) {
        pool.connectionPool
                .connect()
                .get(5000, TimeUnit.MILLISECONDS)
                .sendPreparedStatement("insert into user (name) values (?)", listOf(name))
    }

    fun updateUser(user: User) {
        pool.connectionPool
                .connect()
                .get(5000, TimeUnit.MILLISECONDS)
                .sendPreparedStatement("update user set name = ? where id = ?", listOf(user.name, user.id))
    }

    fun findAll(): List<User> {
        val result = pool.connectionPool
                .connect()
                .get(5000, TimeUnit.MILLISECONDS)
                .sendQuery("select id,name from user")

        val userList = result.get().rows.map {
            User(it[0] as Long, it[1] as String)
        }

        return userList
    }
}