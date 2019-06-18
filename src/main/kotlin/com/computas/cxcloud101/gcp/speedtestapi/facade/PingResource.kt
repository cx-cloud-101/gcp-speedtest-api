package com.computas.cxcloud101.gcp.speedtestapi.facade

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/ping")
class PingResource {

    @GetMapping
    fun ping() = "Pong!"

}
