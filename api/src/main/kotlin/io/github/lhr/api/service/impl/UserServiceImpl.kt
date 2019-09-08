package io.github.lhr.api.service.impl

import io.github.lhr.api.handler.UserHandler
import io.github.lhr.api.service.UserService
import io.github.lhr.core.dao.UserDao
import io.github.lhr.core.domain.entity.User
import io.github.lhr.core.domain.vo.IdPageVO
import io.github.lhr.core.domain.vo.PageVO
import io.github.lhr.core.domain.vo.UserVO
import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject
import io.vertx.ext.mongo.FindOptions
import io.vertx.kotlin.core.json.json
import io.vertx.kotlin.core.json.obj
import org.slf4j.LoggerFactory


/**
 * @author lhr
 * @date 2019/9/7
 */
class UserServiceImpl(vertx: Vertx) : UserService {

    private val userDao = UserDao()

    override suspend fun findById(id: String): User {

        val user = userDao.read<User>(id)
        return user
    }

    override suspend fun insertUser(userVo: UserVO) {
        userDao.save(userVo)
    }

    override suspend fun findAll(): List<User> {
        val users = userDao.list<User>()
        return users
    }

    /**
     * 翻页查询
     * 传统分页思路:skip()+limit()
     */
    override suspend fun pageTurn(pageVO: PageVO): List<User> {

        val findOptions = FindOptions()
                .setLimit(pageVO.pageSize)
                .setSkip((pageVO.pageNum - 1) * pageVO.pageSize)

        val users = userDao.findWithOption<User>(findOptions, JsonObject())
        return users
    }

    /**
     * 翻页查询V2版本
     * Seek Method分页思路:
     * lastId = -1 表示第一页
     */
    override suspend fun pageTurnV2(idPageVO: IdPageVO): List<User> {
        val lastId = idPageVO.lastId
        var query =
                if (lastId == "-1")
                    JsonObject()
                else
                    json {
                        obj("_id" to obj("\$gt" to lastId))
                    }


        val findOptions = FindOptions()
                .setLimit(idPageVO.pageSize)

        val users = userDao.findWithOption<User>(query = query, options = findOptions)
        return users
    }

    companion object {
        private val log = LoggerFactory.getLogger(UserHandler::class.java)
    }
}
