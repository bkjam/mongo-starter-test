package com.example.database.utils

import org.testcontainers.containers.MongoDBContainer

object MongoContainerSingleton {
    val instance: MongoDBContainer by lazy { startMongoContainer() }
    private fun startMongoContainer(): MongoDBContainer =
        MongoDBContainer("mongo:5.0.10")
            .withReuse(true)
            .apply { start() }
}
