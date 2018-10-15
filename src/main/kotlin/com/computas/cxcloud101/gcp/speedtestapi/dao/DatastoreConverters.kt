package com.computas.cxcloud101.gcp.speedtestapi.dao

import com.google.common.collect.ImmutableSet
import org.springframework.cloud.gcp.data.datastore.core.convert.DatastoreCustomConversions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter

@Configuration
class DatastoreConverters {

    @Bean
    fun datastoreCustomConversions(): DatastoreCustomConversions {
        return DatastoreCustomConversions(
                arrayListOf(
                        Converter { source: List<Any> -> ImmutableSet.copyOf(source) }
                )
        )
    }
}