package io.github.lhr.core.domain.vo

import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.types.ObjectId

/**
 * @Author : lhr
 * @Date : 17:30 2019/8/30
 * Seek Method PageTurn VO
 */
data class IdPageVO(

        @JsonProperty("pageSize")
        var pageSize: Int,

        @JsonProperty("lastId")
        var lastId: String
)