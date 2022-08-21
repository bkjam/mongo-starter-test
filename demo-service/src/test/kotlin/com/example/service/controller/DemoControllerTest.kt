package com.example.service.controller

import com.example.database.dto.DemoDto
import com.example.service.service.DemoService
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest
@ActiveProfiles("test")
@ContextConfiguration(classes = [DemoController::class])
class DemoControllerTest {
    @Autowired private lateinit var mockMvc: MockMvc
    @Autowired private lateinit var objectMapper: ObjectMapper
    @MockBean private lateinit var demoService: DemoService

    @Test
    fun insertDemo_whenInsertingNewDemoObj_responseOk() {
        // Arrange
        val demoDto = DemoDto(id = "testId", name = "testName")
        val requestJson = objectMapper.writeValueAsString(demoDto)

        // Act + Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/demo/insert")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isNoContent)
    }

    @Test
    fun deleteDemo_whenDeletingExistingDemoObj_responseOk() {
        // Arrange
        val demoDto = DemoDto(id = "testId", name = "testName")

        // Act + Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/demo/testId"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isNoContent)
    }

    @Test
    fun getDemo_whenRetrievingExistingDemoObj_responseOkWithDemoDto() {
        // Arrange
        val demoDto = DemoDto(id = "testId", name = "testName")
        val responseJson = objectMapper.writeValueAsString(demoDto)
        Mockito.`when`(demoService.getDemo(Mockito.anyString())).thenReturn(demoDto)

        // Act + Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/demo/testId"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(responseJson))
    }
}