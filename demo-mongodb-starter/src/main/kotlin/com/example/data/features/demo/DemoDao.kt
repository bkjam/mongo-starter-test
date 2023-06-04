package com.example.data.features.demo

import org.springframework.stereotype.Component

@Component
class DemoDao(
    private val demoRepository: DemoRepository
) {
    fun saveDemo(demoDto: DemoDto): DemoDto {
        return DemoMapper.toDto(demoRepository.save(DemoMapper.toEntity(demoDto)))
    }

    fun deleteDemo(demoId: String) {
        demoRepository.deleteById(demoId)
    }

    fun getDemoById(demoId: String): DemoDto? {
        val optionalEntity = demoRepository.findById(demoId)
        if (optionalEntity.isPresent) {
            return DemoMapper.toDto(optionalEntity.get())
        }
        return null
    }
}