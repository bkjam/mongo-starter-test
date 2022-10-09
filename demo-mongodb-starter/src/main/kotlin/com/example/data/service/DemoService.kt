package com.example.data.service

import com.example.data.dto.DemoDto
import com.example.data.mapper.DemoMapper
import com.example.data.repository.DemoRepository
import org.springframework.stereotype.Service

@Service
class DemoService(
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