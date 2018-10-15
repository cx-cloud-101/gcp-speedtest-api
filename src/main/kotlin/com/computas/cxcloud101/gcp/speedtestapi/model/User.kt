package com.computas.cxcloud101.gcp.speedtestapi.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.google.common.collect.ImmutableSet
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity
import org.springframework.cloud.gcp.data.datastore.core.mapping.Field
import org.springframework.data.annotation.Id
import java.io.Serializable

@Entity(name = "user")
data class User @JsonCreator constructor(
        @Id
        @Field(name = "name")
        val user: String,

        @Field(name = "devices")
        val devices: ImmutableSet<Int> = ImmutableSet.of()
) : Serializable