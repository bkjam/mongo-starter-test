package com.example.data.features.demo

import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component

@Component
class DemoDao(
    private val demoTemplate: MongoTemplate,
    private val demoRepository: DemoRepository
) {
    fun saveDemo(demoDto: DemoDto, withTemplate: Boolean? = false): DemoDto {
        return if (withTemplate!!) {
            DemoMapper.toDto(demoTemplate.save(DemoMapper.toEntity(demoDto)))
        } else {
            DemoMapper.toDto(demoRepository.save(DemoMapper.toEntity(demoDto)))
        }
    }

    fun deleteDemo(demoId: String, withTemplate: Boolean? = false) {
        if (withTemplate!!) {
            val query = Query(Criteria("_id").`is`(demoId))
            demoTemplate.findAndRemove(query, DemoEntity::class.java)
        } else {
            demoRepository.deleteById(demoId)
        }
    }

    fun getDemoById(demoId: String, withTemplate: Boolean? = false): DemoDto? {
        if (withTemplate!!) {
            val query = Query(Criteria("_id").`is`(demoId))
            val entity = demoTemplate.findOne(query, DemoEntity::class.java)
            if (entity != null) {
                return DemoMapper.toDto(entity)
            }
            return null
        } else {
            val optionalEntity = demoRepository.findById(demoId)
            if (optionalEntity.isPresent) {
                return DemoMapper.toDto(optionalEntity.get())
            }
            return null
        }
    }
}
