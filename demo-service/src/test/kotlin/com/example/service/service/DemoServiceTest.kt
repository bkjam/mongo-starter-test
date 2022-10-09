package com.example.service.service

import com.example.data.dao.DemoDao
import com.example.data.dto.DemoDto
import com.example.service.helper.MockitoHelper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@ExtendWith(MockitoExtension::class)
class DemoServiceTest {
    @Mock private lateinit var demoDao: DemoDao
    @InjectMocks private lateinit var demoService: DemoService

    @Test
    fun insertDemo_whenInsertingNewDemoObj_daoInsertDemoIsCalled() {
        // Arrange
        val demoDto = DemoDto(id = "testId", name = "testName")

        // Act
        demoService.insertDemo(demoDto)

        // Assert
        Mockito.verify(demoDao, Mockito.times(1)).insertDemo(MockitoHelper.anyObject())
    }

    @Test
    fun deleteDemo_whenDeletingExistingDemoObj_daoDeleteDemoIsCalled() {
        // Arrange
        val demoDto = DemoDto(id = "testId", name = "testName")

        // Act
        demoService.deleteDemo("testId")

        // Assert
        Mockito.verify(demoDao, Mockito.times(1)).deleteDemo(Mockito.anyString())
    }

    @Test
    fun getDemo_whenRetrievingExistingDemoObj_daoGetDemoIsCalled() {
        // Arrange
        val demoDto = DemoDto(id = "testId", name = "testName")
        Mockito.`when`(demoDao.getDemo(Mockito.anyString())).thenReturn(demoDto)

        // Act
        val retrievedDemoDto = demoService.getDemo("testId")

        // Assert
        Mockito.verify(demoDao, Mockito.times(1)).getDemo(Mockito.anyString())
        Assertions.assertEquals(demoDto, retrievedDemoDto)
    }
}