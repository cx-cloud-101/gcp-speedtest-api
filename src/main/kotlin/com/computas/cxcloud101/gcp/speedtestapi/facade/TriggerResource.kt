package com.computas.cxcloud101.gcp.speedtestapi.facade

import com.computas.cxcloud101.gcp.speedtestapi.dao.UserRepository
import com.computas.cxcloud101.gcp.speedtestapi.model.TriggerMessage
import com.computas.cxcloud101.gcp.speedtestapi.model.User
import com.google.common.collect.ImmutableSet
import com.google.common.collect.Sets
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/trigger")
class TriggerResource(val pubSub: PubSubTemplate,
                      @Value("\${trigger-topic}")
                      val triggerTopic: String,
                      val userRepository: UserRepository) {

    val gson: Gson = Gson()

    @GetMapping
    fun getTrigger(): ResponseEntity<Void> {
        userRepository.findAll()
                .flatMap { user -> user.devices.map { device -> TriggerMessage(user.user, device) } }
                .map { gson.toJson(it) }
                .forEach { this.pubSub.publish(triggerTopic, it)}

        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping(path = ["register"], consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun postRegistration(@RequestBody registration: TriggerMessage): ResponseEntity<User> {
        if (registration.device < 0) {
            return ResponseEntity.badRequest().build();
        }

        val user = userRepository.findById(registration.user)
                .map { user -> User(user.user, ImmutableSet.builder<Int>().addAll(user.devices).add(registration.device).build()) }
                .orElseGet { User(registration.user, ImmutableSet.of(registration.device)) }

        return ResponseEntity.ok(userRepository.save(user));
    }

    @DeleteMapping(path = ["register"], consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun deleteRegistration(@RequestBody registration: TriggerMessage): ResponseEntity<User> {
        if (registration.device < 0) {
            return ResponseEntity.badRequest().build();
        }

        return userRepository.findById(registration.user)
                .map { user -> User(user.user, setWithout(user.devices, registration.device)) }
                .map { userRepository.save(it) }
                .map { ResponseEntity.ok(it) }
                .orElseGet { ResponseEntity(HttpStatus.NOT_FOUND) }
    }

    private fun <E> setWithout(set: Set<E>, element: E): ImmutableSet<E> {
        return ImmutableSet.copyOf(Sets.difference(set, ImmutableSet.of(element)))
    }
}
