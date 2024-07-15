package com.jinho.springboot.restwebservices.user.service

import com.jinho.springboot.restwebservices.user.entity.User
import jakarta.persistence.EntityNotFoundException
import java.time.LocalDate

class UserDaoServiceInMemoryImpl: UserDaoService {

    companion object {
        var id: Int = 0

        var userList: MutableList<User> = arrayListOf(
            User(++id, "Han", LocalDate.now().minusYears(17)),
            User(++id, "Jin", LocalDate.now().minusYears(30)),
            User(++id, "Ho", LocalDate.now().minusYears(29))
        )

    }

    override fun findAll(): List<User> = userList

    override fun save(user: User): User {
        userList.add(user)
        return user
    }

    override fun findById(id: Int): User? {
        return userList.firstOrNull() { user -> user.id == id }
    }

    override fun delete(id: Int) {
        userList.removeIf { user -> user.id == id }
    }
}