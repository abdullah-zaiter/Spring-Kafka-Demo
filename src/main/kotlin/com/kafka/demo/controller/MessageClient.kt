package com.kafka.demo.controller


import com.kafka.demo.controller.ConsoleColors.ANSI_BLUE
import com.kafka.demo.controller.ConsoleColors.ANSI_RESET
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class MessageClient {
    @KafkaListener(topics = ["test_topic"], groupId = "test_id")
    fun consume(message: String) {
        println("\nMessageClient:\n\tconsume()\n\t\t$ANSI_BLUE Received: $message$ANSI_RESET\n\tconsume\n")
    }
}