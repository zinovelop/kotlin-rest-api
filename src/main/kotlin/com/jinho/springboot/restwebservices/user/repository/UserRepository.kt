package com.jinho.springboot.restwebservices.user.repository

import com.jinho.springboot.restwebservices.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {

}