package com.jinho.springboot.restwebservices.user.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate

@Entity(name = "user_table")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    var name: String,
    var birthDate: LocalDate
    ){

    override fun toString(): String {
        return "User(id=$id, name='$name', birthDate=$birthDate)"
    }

}