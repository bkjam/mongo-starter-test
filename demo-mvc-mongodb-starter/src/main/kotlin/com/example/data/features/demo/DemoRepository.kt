package com.example.data.features.demo

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface DemoRepository: MongoRepository<DemoEntity, String> {

}