package io.github.lhr.core.domain.conf


/**
 * @author lhr
 * @date 2019/4/22
 */
data class MySqlConf(
        val base: Base,
        val pool: Pool
)

data class Base(
        val database: String,
        val host: String,
        val password: String,
        val port: Int,
        val username: String
)

data class Pool(
        val maxIdleTime: Long,
        val maxPendingQueries: Int,
        val queryTimeout: Long
)