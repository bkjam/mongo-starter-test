package com.example.data.repository

import com.example.data.entity.DemoEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface DemoRepository: MongoRepository<DemoEntity, String> {

}