package com.example.data.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@EnableReactiveMongoRepositories(basePackages = ["com.example.data"])
@ComponentScan("com.example.data")
class ReactiveMongoAutoConfiguration
