package com.example.service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class DemoServiceApplication

fun main(args: Array<String>) {
	runApplication<DemoServiceApplication>(*args)
}
