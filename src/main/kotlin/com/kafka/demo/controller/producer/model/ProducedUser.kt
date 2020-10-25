package com.kafka.demo.controller.producer.model

import com.google.gson.annotations.SerializedName

data class ProducedUser (
        private val firstname: String,
        private val lastname: String,
        @SerializedName("username")
        private val user: String,
        private val id: Long,
        private val age: Int,
        private val address: String,
        private val friends: List<ProducedUser>,
        private val iteration: Long
)