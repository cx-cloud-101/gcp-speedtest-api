package com.computas.cxcloud101.gcp.speedtestapi.model

import com.fasterxml.jackson.annotation.JsonCreator
import java.io.Serializable

data class SpeedtestEvent @JsonCreator constructor(
        val user: String,
        val device: Int,
        val data: String,
        val timestamp: Long) : Serializable
