package com.example.database.mapper

import com.example.database.dto.DemoDto
import com.example.database.entity.DemoEntity

object DemoMapper {
    fun toDto(entity: DemoEntity): DemoDto {
        return DemoDto(id = entity.id, name = entity.name)
    }

    fun toEntity(dto: DemoDto): DemoEntity {
        return DemoEntity(id = dto.id, name = dto.name)
    }
}