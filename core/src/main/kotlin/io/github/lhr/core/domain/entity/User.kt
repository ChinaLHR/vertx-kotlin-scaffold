package io.github.lhr.core.domain.entity

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @Author : lhr
 * @Date : 11:43 2019/8/27
 */
data class User(

        @JsonProperty(value = "_id")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        var _id: String,

        @JsonProperty("name")
        var name: String,

        @JsonProperty("age")
        var age: Int,

        @JsonProperty("mobile")
        var mobile: String,

        @JsonProperty("sexType")
        var sexType: Int

)