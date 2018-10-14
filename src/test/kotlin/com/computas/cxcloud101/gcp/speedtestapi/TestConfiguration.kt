package com.computas.cxcloud101.gcp.speedtestapi

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate

@TestConfiguration
class TestConfiguration {

    @MockBean
    lateinit var pubSub: PubSubTemplate
}