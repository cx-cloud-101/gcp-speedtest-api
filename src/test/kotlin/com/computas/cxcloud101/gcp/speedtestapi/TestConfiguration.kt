package com.computas.cxcloud101.gcp.speedtestapi

import com.computas.cxcloud101.gcp.speedtestapi.dao.UserRepository
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate

@TestConfiguration
class TestConfiguration {

    @MockBean
    lateinit var pubSub: PubSubTemplate

    @MockBean
    lateinit var userRepository: UserRepository
}