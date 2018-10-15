package com.computas.cxcloud101.gcp.speedtestapi.model

import com.fasterxml.jackson.annotation.JsonCreator
import java.io.Serializable

data class TriggerMessage @JsonCreator constructor(
        val user: String,
        val device: Int = -1) : Serializable
