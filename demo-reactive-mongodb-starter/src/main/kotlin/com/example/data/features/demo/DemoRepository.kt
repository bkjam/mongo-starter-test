package com.example.data.features.demo

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface DemoRepository: ReactiveMongoRepository<DemoEntity, String> {

}
