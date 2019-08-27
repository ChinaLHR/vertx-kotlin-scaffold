package io.github.lhr.core.dao.base

import io.github.lhr.core.entity.converters.ModelConverter
import io.vertx.core.json.JsonObject
import io.vertx.kotlin.ext.mongo.findAwait
import io.vertx.kotlin.ext.mongo.findOneAwait
import io.vertx.kotlin.ext.mongo.removeDocumentAwait
import io.vertx.kotlin.ext.mongo.saveAwait

/**
 * @Author : lhr
 * @Date : 18:35 2019/8/26
 */
abstract class AbstractDao<T, ID>(val collection: String)  {

    suspend inline fun <reified T>list(query: JsonObject = JsonObject()): List<T> {
        val result = mongoClient.findAwait(collection, query)
        return ModelConverter.fromJson(result)
    }

    suspend inline fun <reified T>save(entity: T): String? {
        val document = ModelConverter.toJson(entity)
        return mongoClient.saveAwait(collection, document)
    }

    suspend inline fun <reified T>read(id: ID, fields: JsonObject = JsonObject()): T {
        val query = JsonObject().put("_id", id)
        val result = mongoClient.findOneAwait(collection, query, fields) ?: JsonObject()
        return ModelConverter.fromJson(result)
    }

    suspend fun delete(id: ID) {
        val query = JsonObject().put("_id", id)
        mongoClient.removeDocumentAwait(collection, query)
    }
}