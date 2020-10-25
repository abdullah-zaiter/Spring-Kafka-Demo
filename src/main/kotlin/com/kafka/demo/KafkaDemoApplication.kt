package com.kafka.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import kotlin.system.exitProcess

@SpringBootApplication
class KafkaDemoApplication

fun main(args: Array<String>) {
	val application = runApplication<KafkaDemoApplication>(*args)
	SpringApplication.exit(application)
	exitProcess(0)
}
