package com.jinho.springboot.restwebservices.user.service

import com.jinho.springboot.restwebservices.user.entity.User
import com.jinho.springboot.restwebservices.user.repository.UserRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse
import kotlin.jvm.optionals.getOrNull

@Service
class UserDaoServiceImpl (
    private val reposiroty: UserRepository
): UserDaoService  {

    override fun findAll(): List<User> = reposiroty.findAll()

    override fun save(user: User): User = reposiroty.save(user)

    override fun findById(id:Int): User? = reposiroty.findById(id).getOrNull()

    override fun delete(id: Int) = reposiroty.deleteById(id)

}