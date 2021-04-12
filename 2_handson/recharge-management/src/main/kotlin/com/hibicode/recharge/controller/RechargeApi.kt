package com.hibicode.recharge.controller

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/recharges")
class RechargeApi(
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {

    @PostMapping
    fun new(@RequestBody rechargeRequest: RechargeRequest) {
        kafkaTemplate.send("recharge_realized", rechargeRequest)
    }

}