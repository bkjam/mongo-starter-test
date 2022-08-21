package com.example.service

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan

@SpringBootTest()
@ComponentScan("com.example.service", "com.example.database")
class DemoServiceApplicationTests {
	@Test
	fun contextLoads() {
	}
}
