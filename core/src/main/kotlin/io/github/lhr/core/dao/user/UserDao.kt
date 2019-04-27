package io.github.lhr.core.dao.user

import com.github.jasync.sql.db.util.flatMap
import com.github.jasync.sql.db.util.map
import io.github.lhr.core.dao.DbPool
import io.github.lhr.core.domain.user.User
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit


/**
 * @author lhr
 * @date 2019/4/23
 */
class UserDao {

    private var pool = DbPool.INSTANCE

    fun findById(id: Long): CompletableFuture<User> {
        val result = pool.connectionPool
                .connect()
                .get(5000, TimeUnit.MILLISECONDS)
                .sendPreparedStatement("select id,name from user where id = ?", listOf(id))
                .map { it.rows.first() }
                .map { User(it.getLong("id")!!, it.getString("name")!!) }


        return result
    }

    fun insertUser(name: String): CompletableFuture<Long> {
        return pool.connectionPool
                .connect()
                .get(5000, TimeUnit.MILLISECONDS)
                .sendPreparedStatement("insert into user (name) values (?)", listOf(name))
                .map { it.rowsAffected }
    }

    fun updateUser(user: User): CompletableFuture<Long> {
        return pool.connectionPool
                .connect()
                .get(5000, TimeUnit.MILLISECONDS)
                .sendPreparedStatement("update user set name = ? where id = ?", listOf(user.name, user.id))
                .map { it.rowsAffected }
    }

    fun findAll(): CompletableFuture<User> {
        return pool.connectionPool
                .connect()
                .get(5000, TimeUnit.MILLISECONDS)
                .sendQuery("select id,name from user")
                .map { it.rows[0] }
                .map { User(it.getLong("id")!!, it.getString("name")!!) }

    }
}