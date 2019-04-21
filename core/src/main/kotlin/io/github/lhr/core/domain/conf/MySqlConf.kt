package io.github.lhr.core.domain.conf

object MySqlConf {
    /**
     * pool : {"maxPendingQueries":10000,"queryTimeout":5000,"maxIdleTime":15}
     * base : {"password":"root","database":"test","port":3306,"host":"127.0.0.1","username":"root"}
     */


    var pool: PoolEntity = PoolEntity
    var base: BaseEntity = BaseEntity

    object PoolEntity {
        var queryTimeout: Long = 0
        var maxIdleTime: Long = 0
        var maxPendingQueries: Int = 0
    }

    object BaseEntity {
        lateinit var password: String
        lateinit var database: String
        var port: Int = 0
        lateinit var host: String
        lateinit var username: String

    }
}