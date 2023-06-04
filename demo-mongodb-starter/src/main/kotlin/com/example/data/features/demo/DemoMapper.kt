package com.example.data.features.demo

import com.example.data.features.demo.DemoDto
import com.example.data.features.demo.DemoEntity

object DemoMapper {
    fun toDto(entity: DemoEntity): DemoDto {
        return DemoDto(id = entity.id, name = entity.name)
    }

    fun toEntity(dto: DemoDto): DemoEntity {
        return DemoEntity(id = dto.id, name = dto.name)
    }
}
