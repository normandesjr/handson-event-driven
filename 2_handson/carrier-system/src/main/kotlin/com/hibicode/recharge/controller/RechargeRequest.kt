package com.hibicode.recharge.controller

import java.math.BigDecimal

data class RechargeRequest(val number: String, val value: BigDecimal)