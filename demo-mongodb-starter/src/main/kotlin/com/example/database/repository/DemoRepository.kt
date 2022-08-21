package com.example.database.repository

import com.example.database.entity.DemoEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface DemoRepository: MongoRepository<DemoEntity, String> {

}