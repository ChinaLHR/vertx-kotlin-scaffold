package io.github.lhr.core.dao

import io.github.lhr.core.entity.User
import io.reactivex.Single
import kotlin.streams.toList


/**
 * @author lhr
 * @date 2019/5/14
 */
class UserDao {

    fun getAll(): Single<List<User>> {
        return jdbcClient.rxQuery("select * from user")
                .map { it ->
                    it.rows.stream()
                            .map {
                               it.mapTo(User::class.java)
                            }
                            .toList()
                }
    }

}