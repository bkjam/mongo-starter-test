package com.example.service

import com.example.data.features.demo.DemoDao
import com.example.data.features.demo.DemoDto
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import javax.annotation.PostConstruct

@SpringBootApplication
class DemoServiceApplication(private val demoDao: DemoDao) {
	companion object {
		private val logger = LoggerFactory.getLogger(DemoServiceApplication::class.java)
	}

	@PostConstruct
	fun initProject() {
		val demoId = "1"

		val retrieveDemoMono = demoDao.getDemoById(demoId)
			.map {
				logger.info("Retrieving Item 1")
				logger.info("Is Item found => $it")
			}
			.onErrorReturn(logger.info("Item $demoId is not found!"))

		val newItem1 = DemoDto(id = demoId, name = "new item")
		val saveDemoMono = demoDao.saveDemo(newItem1)
			.map { logger.info("Inserting $it ") }

		retrieveDemoMono
			.then(saveDemoMono)
			.then(retrieveDemoMono)
			.subscribe()

	}
}

fun main(args: Array<String>) {
	runApplication<DemoServiceApplication>(*args)
}
