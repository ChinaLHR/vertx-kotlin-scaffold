package io.github.lhr.core.domain.vo

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @Author : lhr
 * @Date : 15:10 2019/8/30
 */
data class PageVO(
        @JsonProperty("pageNum")
        var pageNum: Int,

        @JsonProperty("pageSize")
        var pageSize: Int
)