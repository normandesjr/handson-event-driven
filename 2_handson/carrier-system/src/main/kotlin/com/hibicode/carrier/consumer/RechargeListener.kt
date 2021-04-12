package com.hibicode.carrier.consumer

import com.hibicode.recharge.controller.RechargeRequest
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class RechargeListener {

    @KafkaListener(topics = ["recharge_realized"], groupId = "carrier-system")
    fun handleNewRecharge(@Payload newRecharge: RechargeRequest) {
        println(">>>> received: $newRecharge")
    }

}