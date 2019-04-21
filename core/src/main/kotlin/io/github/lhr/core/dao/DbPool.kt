package io.github.lhr.core.dao

import com.github.jasync.sql.db.Configuration
import com.github.jasync.sql.db.ConnectionPoolConfiguration
import com.github.jasync.sql.db.mysql.pool.MySQLConnectionFactory
import com.github.jasync.sql.db.pool.ConnectionPool
import io.github.lhr.core.domain.conf.MySqlConf.base
import io.github.lhr.core.domain.conf.MySqlConf.pool
import java.util.concurrent.TimeUnit

/**
 * DB Pool 单例实现
 */
class DbPool private constructor() {

    private val configuration = Configuration(
            base.username,
            base.host,
            base.port,
            base.password,
            base.database)

    private val poolConfiguration = ConnectionPoolConfiguration(
            maxIdleTime = TimeUnit.MINUTES.toMillis(pool.maxIdleTime),
            maxPendingQueries = pool.maxPendingQueries,
            queryTimeout = pool.queryTimeout
    )

    val connectionPool = ConnectionPool(
            factory = MySQLConnectionFactory(configuration),
            configuration = poolConfiguration)


    /**
     * 伴生对象 替代了 Java 静态方法
     */
    companion object {
        val INSTANCE = DbPoolHolder.holder
    }

    private object DbPoolHolder {
        val holder = DbPool()
    }
}