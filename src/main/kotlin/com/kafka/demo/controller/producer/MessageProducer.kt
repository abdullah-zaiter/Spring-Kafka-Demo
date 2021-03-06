package com.kafka.demo.controller.producer

import com.kafka.demo.controller.KafkaConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.http.ResponseEntity
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageProducer {

    var kafkaTemplate: KafkaTemplate<String, String> = KafkaConfig().kafkaTemplate()
    val topic: String = "test_topic"

    @GetMapping("/send")
    fun sendMessage(@RequestParam("message") message: String): ResponseEntity<String> {
        val sendResult = kafkaTemplate.send(topic, message).get()
        return ResponseEntity.ok(sendResult.producerRecord.value() + " sent to topic ${sendResult.producerRecord.topic()}")
    }

    @GetMapping("/produce")
    fun produceMessage(@RequestParam("message") message: String): ResponseEntity<String> {
        val producer = KafkaConfig().producerFactory().createProducer()
        val result = producer.send(ProducerRecord(topic, message)).get()
        return ResponseEntity.ok("message: '$message' sent to " + result.topic())
    }
}