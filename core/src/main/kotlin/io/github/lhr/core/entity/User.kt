package io.github.lhr.core.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty


/**
 * @author lhr
 * @date 2019/4/23
 */

class User(
        var id: Long,
        var name: String,

        @JsonProperty("sex_type")
        var sexType: Int,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
        @JsonProperty("update_time")
        var updateTime: String,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
        @JsonProperty("create_time")
        var createTime: String
)