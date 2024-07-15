package com.jinho.springboot.restwebservices.user.service

import com.jinho.springboot.restwebservices.user.entity.User

interface UserDaoService {

    fun findAll(): List<User>

    fun save(user: User): User

    fun findById(id:Int): User?

    fun delete(id: Int)

}