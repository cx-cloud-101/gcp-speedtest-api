package com.computas.cxcloud101.gcp.speedtestapi.dao

import com.computas.cxcloud101.gcp.speedtestapi.model.User
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository

interface UserRepository: DatastoreRepository<User, String>