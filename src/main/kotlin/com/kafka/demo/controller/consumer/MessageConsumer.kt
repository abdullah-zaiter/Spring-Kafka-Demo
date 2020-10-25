package com.kafka.demo.controller.consumer


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kafka.demo.util.ConsoleColors.ANSI_BLUE
import com.kafka.demo.util.ConsoleColors.ANSI_RESET
import com.kafka.demo.controller.consumer.model.ConsumedUser
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class MessageConsumer (
        private val gson: Gson = GsonBuilder().setPrettyPrinting().create()
) {
    @KafkaListener(topics = ["test_topic"], groupId = "test_id")
    fun consume(message: String) {
        println("\n${ANSI_BLUE}Received:\n$message${ANSI_RESET}")
        val consumedUser: ConsumedUser = gson.fromJson(message, ConsumedUser::class.java)
        println("$ANSI_BLUE$consumedUser$ANSI_RESET\n")
    }
}