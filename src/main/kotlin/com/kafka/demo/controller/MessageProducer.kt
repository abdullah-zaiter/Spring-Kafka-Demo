package com.kafka.demo.controller

import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.Future

@RestController
class MessageProducer @Autowired constructor() {

    var kafkaTemplate: KafkaTemplate<String, String> = KafkaProducerConfig().kafkaTemplate()
    val topic: String = "test_topic"

    @GetMapping("/send")
    fun sendMessage(@RequestParam("message") message: String): ResponseEntity<String> {
        val sendResult = kafkaTemplate.send(topic, message).get()
        return ResponseEntity.ok(sendResult.producerRecord.value() + " sent to topic")
    }

    @GetMapping("/produce")
    fun produceMessage(@RequestParam("message") message: String): ResponseEntity<String> {
        val producerRecord: ProducerRecord<String, String> = ProducerRecord(topic, message)
        val producer = KafkaProducerConfig().producerFactory().createProducer()
        val future: Future<RecordMetadata> = producer.send(producerRecord)
        return ResponseEntity.ok("message: '$message' sent to " + future.get().topic())
    }
}