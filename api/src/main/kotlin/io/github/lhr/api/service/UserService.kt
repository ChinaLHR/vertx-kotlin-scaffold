package io.github.lhr.api.service

import io.github.lhr.api.service.impl.UserServiceImpl
import io.github.lhr.api.service.proxy.UserServiceEventBusProxy
import io.github.lhr.api.service.proxy.UserServiceProxyImpl
import io.github.lhr.core.domain.entity.User
import io.github.lhr.core.domain.vo.IdPageVO
import io.github.lhr.core.domain.vo.PageVO
import io.github.lhr.core.domain.vo.UserVO
import io.vertx.core.Vertx

interface UserService {

    companion object {

        fun createProxy(vertx: Vertx): UserService {
            return UserServiceEventBusProxy(vertx, ADDRESS)
        }

        fun createProxyHandler(vertx: Vertx) {
            val userService = UserServiceImpl(vertx)
            UserServiceProxyImpl(vertx, userService)
                    .register(vertx.eventBus(), ADDRESS)
        }

        private const val ADDRESS = "user-service"

    }

    suspend fun findById(id: String): User
    suspend fun insertUser(userVo: UserVO)
    suspend fun findAll(): List<User>
    suspend fun pageTurn(pageVO: PageVO): List<User>
    suspend fun pageTurnV2(idPageVO: IdPageVO): List<User>
}
