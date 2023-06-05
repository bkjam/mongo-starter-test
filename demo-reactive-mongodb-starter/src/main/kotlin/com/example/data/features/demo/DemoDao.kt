package com.example.data.features.demo

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class DemoDao(
    private val demoTemplate: ReactiveMongoTemplate,
    private val demoRepository: DemoRepository
) {
    fun saveDemo(demoDto: DemoDto, withTemplate: Boolean? = false): Mono<DemoDto> {
        return if (withTemplate!!) {
            demoTemplate.save(DemoMapper.toEntity(demoDto))
                .map(DemoMapper::toDto)
        } else {
            demoRepository.save(DemoMapper.toEntity(demoDto))
                .map(DemoMapper::toDto)
        }
    }

    fun deleteDemo(demoId: String, withTemplate: Boolean? = false): Mono<Void> {
        return if (withTemplate!!) {
            val query = Query(Criteria("_id").`is`(demoId))
            demoTemplate.remove(query).then()
        } else {
            demoRepository.deleteById(demoId)
        }
    }

    fun getDemoById(demoId: String, withTemplate: Boolean? = false): Mono<DemoDto> {
        val fallback = Mono.error<DemoDto> { throw Exception("Demo Item $demoId is not found!") }
        return if (withTemplate!!) {
            demoTemplate.findById(demoId, DemoEntity::class.java)
                .map(DemoMapper::toDto)
                .switchIfEmpty(fallback)
        } else {
            demoRepository.findById(demoId)
                .map(DemoMapper::toDto)
                .switchIfEmpty(fallback)
        }
    }
}
