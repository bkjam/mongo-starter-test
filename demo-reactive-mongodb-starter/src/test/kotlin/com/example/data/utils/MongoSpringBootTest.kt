package com.example.data.utils

import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@Target(AnnotationTarget.CLASS)
@SpringBootTest(classes = [TestConfig::class])
@ContextConfiguration(initializers = [MongoInitializer::class])
@AutoConfigureDataMongo
annotation class MongoSpringBootTest
