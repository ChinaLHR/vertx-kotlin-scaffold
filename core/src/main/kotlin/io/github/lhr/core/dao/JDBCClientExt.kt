package io.github.lhr.core.dao

import io.vertx.ext.jdbc.JDBCClient
import io.vertx.ext.sql.SQLConnection
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


/**
 * @author lhr
 * @date 2019/5/20
 */

suspend fun JDBCClient.getConnection(): SQLConnection = suspendCoroutine { cont ->
    this.getConnection { conn ->
        if (conn.succeeded()) {
            cont.resume(conn.result())
        } else {
            cont.resumeWithException(conn.cause())
        }
    }
}
