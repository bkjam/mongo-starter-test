package com.example.data

import com.example.data.features.demo.DemoDao
import com.example.data.features.demo.DemoDto
import com.example.data.features.demo.DemoMapper
import com.example.data.features.demo.DemoRepository
import com.example.data.utils.MongoSpringBootTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import reactor.test.StepVerifier

@ActiveProfiles("test")
@MongoSpringBootTest
class DemoDaoTest {
    @Autowired private lateinit var demoDao: DemoDao
    @Autowired private lateinit var demoRepository: DemoRepository

    @Test
    fun saveDemo_whenInsertingNewDemoObj_entityInDatabase() {
        // Arrange
        val usingMongoTemplate = false
        val demoDto = DemoDto(id = "testId", name = "testName")

        // Act
        val source = demoDao.saveDemo(demoDto, usingMongoTemplate)

        // Assert
        StepVerifier.create(source)
            .assertNext {
                Assertions.assertEquals("testId", it.id)
            }
            .verifyComplete()
    }

    @Test
    fun saveDemo_whenInsertingNewDemoObjWithMongoTemplate_entityInDatabase() {
        // Arrange
        val usingMongoTemplate = true
        val demoDto = DemoDto(id = "testId", name = "testName")

        // Act
        val source = demoDao.saveDemo(demoDto, usingMongoTemplate)

        // Assert
        StepVerifier.create(source)
            .assertNext {
                Assertions.assertEquals("testId", it.id)
            }
            .verifyComplete()
    }

    @Test
    fun deleteDemo_whenDeletingExistingDemoObj_entityNotInDatabase() {
        // Arrange
        val usingMongoTemplate = false
        val demoDto = DemoDto(id = "testId", name = "testName")

        // Act
        val source = demoRepository.save(DemoMapper.toEntity(demoDto))
            .flatMap { demoDao.deleteDemo(it.id, usingMongoTemplate) }
            .flatMap { demoRepository.findAll().collectList() }

        // Assert
        StepVerifier.create(source).expectNextCount(0).verifyComplete()
    }

    @Test
    fun deleteDemo_whenDeletingExistingDemoObjWithMongoTemplate_entityNotInDatabase() {
        // Arrange
        val usingMongoTemplate = true
        val demoDto = DemoDto(id = "testId", name = "testName")

        // Act
        val source = demoRepository.save(DemoMapper.toEntity(demoDto))
            .flatMap { demoDao.deleteDemo(it.id, usingMongoTemplate) }
            .flatMap { demoRepository.findAll().collectList() }

        // Assert
        StepVerifier.create(source).expectNextCount(0).verifyComplete()
    }

    @Test
    fun getDemoById_whenRetrievingExistingDemoObj_returnsDemoDto() {
        // Arrange
        val usingMongoTemplate = false
        val demoDto = DemoDto(id = "testId", name = "testName")

        // Act
        val source = demoRepository.save(DemoMapper.toEntity(demoDto))
            .flatMap { demoDao.getDemoById(it.id, usingMongoTemplate) }

        // Assert
        StepVerifier.create(source)
            .assertNext {
                Assertions.assertEquals("testId", it.id)
            }
            .verifyComplete()
    }

    @Test
    fun getDemoById_whenRetrievingExistingDemoObjWithMongoTemplate_returnsDemoDto() {
        // Arrange
        val usingMongoTemplate = true
        val demoDto = DemoDto(id = "testId", name = "testName")

        // Act
        val source = demoRepository.save(DemoMapper.toEntity(demoDto))
            .flatMap { demoDao.getDemoById(it.id, usingMongoTemplate) }

        // Assert
        StepVerifier.create(source)
            .assertNext {
                Assertions.assertEquals("testId", it.id)
            }
            .verifyComplete()
    }
}