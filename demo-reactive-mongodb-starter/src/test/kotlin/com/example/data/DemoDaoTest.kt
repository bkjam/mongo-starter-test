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
        val demoDto = DemoDto(id = "testId", name = "testName")

        // Act
        val source = demoDao.saveDemo(demoDto)

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
        val demoDto = DemoDto(id = "testId", name = "testName")

        // Act
        val source = demoRepository.save(DemoMapper.toEntity(demoDto))
            .flatMap { demoDao.deleteDemo(it.id) }
            .flatMap { demoRepository.findAll().collectList() }

        // Assert
        StepVerifier.create(source).expectNextCount(0).verifyComplete()
    }

    @Test
    fun getDemoById_whenRetrievingExistingDemoObj_returnsDemoDto() {
        // Arrange
        val demoDto = DemoDto(id = "testId", name = "testName")

        // Act
        val source = demoRepository.save(DemoMapper.toEntity(demoDto))
            .flatMap { demoDao.getDemoById(it.id) }

        // Assert
        StepVerifier.create(source)
            .assertNext {
                Assertions.assertEquals("testId", it.id)
            }
            .verifyComplete()
    }
}