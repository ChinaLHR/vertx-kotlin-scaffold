package io.github.lhr.core.domain.vo

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @Author : lhr
 * @Date : 15:38 2019/8/27
 */
data class UserVO(

        @JsonProperty("name")
        var name: String,

        @JsonProperty("age")
        var age: Int,

        @JsonProperty("mobile")
        var mobile: String,

        @JsonProperty("sexType")
        var sexType: Int
)