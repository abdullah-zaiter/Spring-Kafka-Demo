package com.kafka.demo

import com.kafka.demo.controller.ConsoleColors.ANSI_RESET
import com.kafka.demo.controller.ConsoleColors.ANSI_YELLOW
import com.kafka.demo.controller.MessageProducer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import kotlin.random.Random


@SpringBootApplication
class KafkaDemoApplication

fun main(args: Array<String>) {
	runApplication<KafkaDemoApplication>(*args)
	val messageProducer = MessageProducer()
	var count = 0
	while (true){
		val result = messageProducer.sendMessage("load {data= ${Random.nextInt()}, iteration= ${++count}}")
		println(ANSI_YELLOW+result+ ANSI_RESET)
		Thread.sleep(2000)
	}
}

