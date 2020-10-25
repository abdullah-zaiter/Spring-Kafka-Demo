package com.kafka.demo.controller.consumer.model

import com.google.gson.annotations.SerializedName

data class ConsumedUser (
        private val firstname: String,
        private val lastname: String,
        @SerializedName("username")
        private val user: String,
        private val id: Long,
        private val age: Int,
        private val address: String,
        private val friends: List<ConsumedUser>,
        private val iteration: Long
)