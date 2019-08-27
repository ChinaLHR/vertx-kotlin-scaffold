package io.github.lhr.core.dao

import io.github.lhr.core.conf.mongoDbConf
import io.github.lhr.core.entity.ModelConverter
import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject
import io.vertx.ext.mongo.MongoClient
import io.vertx.kotlin.ext.mongo.findAwait
import io.vertx.kotlin.ext.mongo.findOneAwait
import io.vertx.kotlin.ext.mongo.removeDocumentAwait
import io.vertx.kotlin.ext.mongo.saveAwait

/**
 * @Author : lhr
 * @Date : 18:35 2019/8/26
 */
abstract class AbstractDao<T, ID>(vertx: Vertx, val collection: String)  {

    val client: MongoClient = MongoClient.createNonShared(vertx, mongoDbConf)

    suspend inline fun <reified T>list(query: JsonObject = JsonObject()): List<T> {
        val result = client.findAwait(collection, query)
        return ModelConverter.fromJson(result)
    }

    suspend inline fun <reified T>save(entity: T): String? {
        val document = ModelConverter.toJson(entity)
        return client.saveAwait(collection, document)
    }

    suspend inline fun <reified T>read(id: ID, fields: JsonObject = JsonObject()): T {
        val query = JsonObject().put("_id", id)
        val result = client.findOneAwait(collection, query, fields) ?: JsonObject()
        return ModelConverter.fromJson(result)
    }

    suspend fun delete(id: ID) {
        val query = JsonObject().put("_id", id)
        client.removeDocumentAwait(collection, query)
    }
}