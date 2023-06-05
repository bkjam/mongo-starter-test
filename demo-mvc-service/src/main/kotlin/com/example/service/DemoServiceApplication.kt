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
		logger.info("[START] =========================================================")
		var tempItem = demoDao.getDemoById("1")
		logger.info("Retrieving Item 1")
		logger.info("Is Item found => $tempItem")
		logger.info("")

		val newItem1 = DemoDto(id = "1", "new item")
		logger.info("Inserting $newItem1 ")
		demoDao.saveDemo(newItem1)
		tempItem = demoDao.getDemoById("1")
		logger.info("Is Item found => $tempItem")
		logger.info("")

		logger.info("Deleting $tempItem")
		demoDao.deleteDemo("1")
		tempItem = demoDao.getDemoById("1")
		logger.info("Is Item found => $tempItem")
		logger.info("[END] =========================================================")
	}
}

fun main(args: Array<String>) {
	runApplication<DemoServiceApplication>(*args)
}
