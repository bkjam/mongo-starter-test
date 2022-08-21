package com.example.service.service

import com.example.database.dao.DemoDao
import com.example.database.dto.DemoDto
import org.springframework.stereotype.Service

@Service
class DemoService(
    private val demoDao: DemoDao
) {
    fun insertDemo(demoDto: DemoDto) {
        demoDao.insertDemo(demoDto)
    }

    fun deleteDemo(demoId: String) {
        demoDao.deleteDemo(demoId)
    }

    fun getDemo(demoId: String): DemoDto? {
        return demoDao.getDemo(demoId)
    }
}
