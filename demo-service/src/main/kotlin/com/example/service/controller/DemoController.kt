package com.example.service.controller

import com.example.database.dto.DemoDto
import com.example.service.service.DemoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/demo")
class DemoController(
    private val demoService: DemoService
) {
    @PostMapping("/insert")
    fun insertDemo(@RequestBody demoDto: DemoDto): ResponseEntity<Void> {
        demoService.insertDemo(demoDto)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{demoId}")
    fun deleteDemo(@PathVariable("demoId") demoId: String,): ResponseEntity<Void> {
        demoService.deleteDemo(demoId)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/{demoId}")
    fun getDemo(@PathVariable("demoId") demoId: String,): ResponseEntity<DemoDto> {
        val demoDto = demoService.getDemo(demoId)
        if (demoDto != null) {
            return ResponseEntity(demoDto, HttpStatus.OK)
        }
        return ResponseEntity.notFound().build()
    }
}