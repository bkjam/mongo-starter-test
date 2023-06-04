package com.example.data.utils

import org.testcontainers.containers.MongoDBContainer

object MongoContainerSingleton {
    val instance: MongoDBContainer by lazy { startMongoContainer() }
    private fun startMongoContainer(): MongoDBContainer =
        MongoDBContainer("mongo:6.0.3")
            .withReuse(true)
            .apply { start() }
}
