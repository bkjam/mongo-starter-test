package com.example.data.features.demo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "demo")
data class DemoEntity(
    @Id val id: String,
    val name: String
)
