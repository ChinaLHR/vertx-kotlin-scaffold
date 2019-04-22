package io.github.lhr.core.domain.conf


/**
 * @author lhr
 * @date 2019/4/22
 */
data class HttpConf (
    val port: Port
)

data class Port(
    val api: Int,
    val cms: Int
)