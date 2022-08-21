package com.example.database

import com.example.database.dao.DemoDao
import com.example.database.dto.DemoDto
import com.example.database.mapper.DemoMapper
import com.example.database.repository.DemoRepository
import com.example.database.utils.MongoSpringBootTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@MongoSpringBootTest
class DemoDaoTest {
    @Autowired private lateinit var demoDao: DemoDao
    @Autowired private lateinit var demoRepository: DemoRepository

    @AfterEach
    fun emptyCollections() {
        demoRepository.deleteAll()
    }

    @Test
    fun insertDemo_whenInsertingNewDemoObj_entityInDatabase() {
        // Arrange
        val demoDto = DemoDto(id = "testId", name = "testName")

        // Act
        demoDao.insertDemo(demoDto)

        // Assert
        Assertions.assertEquals(1, demoRepository.findAll().size)
        Assertions.assertTrue(demoRepository.findById("testId").isPresent)
    }

    @Test
    fun deleteDemo_whenDeletingExistingDemoObj_entityNotInDatabase() {
        // Arrange
        val demoDto = DemoDto(id = "testId", name = "testName")
        demoRepository.save(DemoMapper.toEntity(demoDto))

        // Act
        demoDao.deleteDemo("testId")

        // Assert
        Assertions.assertEquals(0, demoRepository.findAll().size)
        Assertions.assertFalse(demoRepository.findById("testId").isPresent)
    }

    @Test
    fun getDemo_whenRetrievingExistingDemoObj_returnsDemoDto() {
        // Arrange
        val demoDto = DemoDto(id = "testId", name = "testName")
        demoRepository.save(DemoMapper.toEntity(demoDto))

        // Act
        val retrievedDemoDto = demoDao.getDemo("testId")

        // Assert
        Assertions.assertNotNull(retrievedDemoDto)
        Assertions.assertEquals(demoDto, retrievedDemoDto)
    }
}