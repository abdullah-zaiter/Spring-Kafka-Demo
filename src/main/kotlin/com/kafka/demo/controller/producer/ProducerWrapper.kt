package com.kafka.demo.controller.producer

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kafka.demo.controller.ConsoleColors
import com.kafka.demo.controller.producer.model.ProducedUser

class ProducerWrapper(
        private var counter: Long = 0,
        private var messageProducer: MessageProducer = MessageProducer(),
        private var gson: Gson = GsonBuilder().setPrettyPrinting().create()
) {

    companion object {
        private var ITERATION_INTERVAL: Long = 2000
    }

    fun run() {
        while (true){
            val payload = getPayload(++counter)
            val message = gson.toJson(payload)
            println("${ConsoleColors.ANSI_YELLOW}$payload${ConsoleColors.ANSI_RESET}")
            val result = messageProducer.sendMessage(message)
            println(ConsoleColors.ANSI_YELLOW + result + ConsoleColors.ANSI_RESET)
            Thread.sleep(ITERATION_INTERVAL)
        }
    }

    fun setIterationInterval(interval: Number): ProducerWrapper {
        ITERATION_INTERVAL = (interval.toDouble()*1000).toLong()
        return this
    }

    private fun getPayload(counter: Long): ProducedUser {
        return ProducedUser(
                "Abdullah",
                "Zaiter",
                "a.zaiter",
                12345L,
                24,
                "Brasilia, Brazil",
                listOf(),
                counter
        )
    }
}