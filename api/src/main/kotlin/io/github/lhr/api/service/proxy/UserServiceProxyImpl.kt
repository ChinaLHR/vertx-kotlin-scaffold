package io.github.lhr.api.service.proxy

import io.github.lhr.api.service.UserService
import io.github.lhr.core.domain.entity.User
import io.github.lhr.core.domain.entity.converters.ModelConverter
import io.github.lhr.core.domain.vo.IdPageVO
import io.github.lhr.core.domain.vo.PageVO
import io.github.lhr.core.domain.vo.UserVO
import io.github.lhr.core.exception.BusinessException
import io.github.lhr.core.proxy.abstarct.AbstractProxyHandler
import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject


/**
 * @author lhr
 * @date 2019/9/7
 */
class UserServiceProxyImpl(vertx: Vertx, val service: UserService) : AbstractProxyHandler(vertx) {


    override suspend fun handle(action: String, message: JsonObject): Any {
        when (action) {
            "findAll" -> {
                return ModelConverter.toJson(service.findAll())
            }
            "findById" -> {
                return ModelConverter.toJson(service.findById(message.getString("id")))
            }
            "insertUser" -> {
                val userVO = ModelConverter.fromJson<UserVO>(message)
                service.insertUser(userVO)
            }
            "pageTurn" -> {
                val pageVO = ModelConverter.fromJson<PageVO>(message)
                return ModelConverter.toJson(service.pageTurn(pageVO))
            }
            "pageTurnV2" -> {
                val idPageVO = ModelConverter.fromJson<IdPageVO>(message)
                return ModelConverter.toJson(service.pageTurnV2(idPageVO))
            }
        }

        throw throw BusinessException("action $action is not valid")
    }

}
