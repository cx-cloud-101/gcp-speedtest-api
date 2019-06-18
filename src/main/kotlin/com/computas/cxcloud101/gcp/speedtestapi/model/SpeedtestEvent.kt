package com.computas.cxcloud101.gcp.speedtestapi.model

import java.io.Serializable

data class SpeedtestEvent(
        val user: String,
        val device: Int,
        val data: Result,
        val timestamp: Long
) : Serializable

data class Result(
        val speeds: Speeds,
        val client: Client,
        val server: Server
) : Serializable

data class Speeds(
        val download: Number,
        val upload: Number
) : Serializable

data class Client(
        val ip: String?,
        val lat: Number?,
        val lon: Number?,
        val isp: String?,
        val country: String?
) : Serializable

data class Server(
        val host: String?,
        val lat: Number?,
        val lon: Number?,
        val country: String?,
        val distance: Number?,
        val ping: Number?,
        val id: String?
) : Serializable
