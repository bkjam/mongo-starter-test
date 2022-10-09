package com.example.data.mapper

import com.example.data.dto.DemoDto
import com.example.data.entity.DemoEntity

object DemoMapper {
    fun toDto(entity: DemoEntity): DemoDto {
        return DemoDto(id = entity.id, name = entity.name)
    }

    fun toEntity(dto: DemoDto): DemoEntity {
        return DemoEntity(id = dto.id, name = dto.name)
    }
}
