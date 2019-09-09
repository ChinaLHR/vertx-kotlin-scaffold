package io.github.lhr.api.service.proxy

import io.github.lhr.api.service.UserService
import io.github.lhr.api.service.proxy.abstarct.AbstractEventBusProxy
import io.github.lhr.core.domain.entity.User
import io.github.lhr.core.domain.entity.converters.ModelConverter
import io.github.lhr.core.domain.vo.IdPageVO
import io.github.lhr.core.domain.vo.PageVO
import io.github.lhr.core.domain.vo.UserVO
import io.github.lhr.core.ext.ok
import io.vertx.core.Vertx
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext


/**
 * @author lhr
 * @date 2019/9/7
 */
class UserServiceEventBusProxy(vertx: Vertx, address: String) : UserService, AbstractEventBusProxy(vertx, address) {
    override suspend fun insertUser(userVo: UserVO) {
        this.send("insertUser", ModelConverter.toJson(userVo)) as JsonObject
    }

    override suspend fun findAll(): List<User> {
        return ModelConverter.fromJson(this.send("findAll", JsonObject()) as JsonArray)
    }

    override suspend fun pageTurn(pageVO: PageVO): List<User> {
        return ModelConverter.fromJson(this.send("pageTurn", ModelConverter.toJson(pageVO)) as JsonArray)
    }

    override suspend fun pageTurnV2(idPageVO: IdPageVO): List<User> {
        return ModelConverter.fromJson(this.send("pageTurnV2", ModelConverter.toJson(idPageVO)) as JsonArray)
    }

    override suspend fun findById(id: String): User {
        return ModelConverter.fromJson(this.send("findById", JsonObject.mapFrom(mapOf("id" to id))) as JsonObject)
    }


}
