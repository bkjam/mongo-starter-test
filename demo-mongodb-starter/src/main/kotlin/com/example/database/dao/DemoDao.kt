package com.example.database.dao

import com.example.database.dto.DemoDto
import com.example.database.mapper.DemoMapper
import com.example.database.repository.DemoRepository
import org.springframework.stereotype.Component

@Component
class DemoDao(
    private val demoRepository: DemoRepository
) {
    fun insertDemo(demoDto: DemoDto) {
        demoRepository.save(DemoMapper.toEntity(demoDto))
    }

    fun deleteDemo(demoId: String) {
        demoRepository.deleteById(demoId)
    }

    fun getDemo(demoId: String): DemoDto? {
        val optionalEntity = demoRepository.findById(demoId)
        if (optionalEntity.isPresent) {
            return DemoMapper.toDto(optionalEntity.get())
        }
        return null
    }
}