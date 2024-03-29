package com.example.data

import com.example.data.features.demo.DemoDao
import com.example.data.features.demo.DemoDto
import com.example.data.features.demo.DemoMapper
import com.example.data.features.demo.DemoRepository
import com.example.data.utils.MongoSpringBootTest
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
    fun saveDemo_whenInsertingNewDemoObj_entityInDatabase() {
        // Arrange
        val useMongoTemplate = false
        val demoDto = DemoDto(id = "testId", name = "testName")

        // Act
        demoDao.saveDemo(demoDto, useMongoTemplate)

        // Assert
        Assertions.assertEquals(1, demoRepository.findAll().size)
        Assertions.assertTrue(demoRepository.findById("testId").isPresent)
    }

    @Test
    fun saveDemo_whenInsertingNewDemoObjUsingMongoTemplate_entityInDatabase() {
        // Arrange
        val useMongoTemplate = true
        val demoDto = DemoDto(id = "testId", name = "testName")

        // Act
        demoDao.saveDemo(demoDto, useMongoTemplate)

        // Assert
        Assertions.assertEquals(1, demoRepository.findAll().size)
        Assertions.assertTrue(demoRepository.findById("testId").isPresent)
    }

    @Test
    fun deleteDemo_whenDeletingExistingDemoObj_entityNotInDatabase() {
        // Arrange
        val useMongoTemplate = false
        val demoDto = DemoDto(id = "testId", name = "testName")
        demoRepository.save(DemoMapper.toEntity(demoDto))

        // Act
        demoDao.deleteDemo("testId", useMongoTemplate)

        // Assert
        Assertions.assertEquals(0, demoRepository.findAll().size)
        Assertions.assertFalse(demoRepository.findById("testId").isPresent)
    }

    @Test
    fun deleteDemo_whenDeletingExistingDemoObjUsingMongoTemplate_entityNotInDatabase() {
        // Arrange
        val useMongoTemplate = true
        val demoDto = DemoDto(id = "testId", name = "testName")
        demoRepository.save(DemoMapper.toEntity(demoDto))

        // Act
        demoDao.deleteDemo("testId", useMongoTemplate)

        // Assert
        Assertions.assertEquals(0, demoRepository.findAll().size)
        Assertions.assertFalse(demoRepository.findById("testId").isPresent)
    }

    @Test
    fun getDemoById_whenRetrievingExistingDemoObj_returnsDemoDto() {
        // Arrange
        val useMongoTemplate = false
        val demoDto = DemoDto(id = "testId", name = "testName")
        demoRepository.save(DemoMapper.toEntity(demoDto))

        // Act
        val retrievedDemoDto = demoDao.getDemoById("testId", useMongoTemplate)

        // Assert
        Assertions.assertNotNull(retrievedDemoDto)
        Assertions.assertEquals(demoDto, retrievedDemoDto)
    }

    @Test
    fun getDemoById_whenRetrievingExistingDemoObjUsingMongoTemplate_returnsDemoDto() {
        // Arrange
        val useMongoTemplate = true
        val demoDto = DemoDto(id = "testId", name = "testName")
        demoRepository.save(DemoMapper.toEntity(demoDto))

        // Act
        val retrievedDemoDto = demoDao.getDemoById("testId", useMongoTemplate)

        // Assert
        Assertions.assertNotNull(retrievedDemoDto)
        Assertions.assertEquals(demoDto, retrievedDemoDto)
    }
}