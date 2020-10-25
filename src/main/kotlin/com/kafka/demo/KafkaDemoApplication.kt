package com.kafka.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import kotlin.system.exitProcess
import com.kafka.demo.util.ConsoleColors.ANSI_BOLD
import com.kafka.demo.util.ConsoleColors.ANSI_GREEN
import com.kafka.demo.util.ConsoleColors.ANSI_RESET
import com.kafka.demo.util.ConsoleColors.ANSI_UNDERLINE
import com.kafka.demo.controller.producer.ProducerWrapper

@SpringBootApplication
class KafkaDemoApplication
private const val RUNNING_TIME_IN_SECONDS = 15L
private const val ITERATION_INTERVAL = 2

fun main(args: Array<String>) {
	val application = runApplication<KafkaDemoApplication>(*args)
	execute()
	SpringApplication.exit(application)
	exitProcess(0)
}

private fun execute() {
	Executors.newSingleThreadExecutor().submit {
		ProducerWrapper().setIterationInterval(ITERATION_INTERVAL).run()
	}.let {
		try {
			it.get(RUNNING_TIME_IN_SECONDS, TimeUnit.SECONDS)
		} catch (e: TimeoutException) {
			println(ANSI_GREEN + ANSI_BOLD + ANSI_UNDERLINE + "Done running for ${RUNNING_TIME_IN_SECONDS}s" + ANSI_RESET)
			it.cancel(true)
		}
	}
}
