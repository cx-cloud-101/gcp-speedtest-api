package com.computas.cxcloud101.gcp.speedtestapi

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@Import(TestConfiguration::class)
class SpeedtestApiApplicatonTests {

    @Test
    fun contextLoads() {
    }

}
