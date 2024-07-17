package com.jinho.springboot.restwebservices.entity.post

import com.fasterxml.jackson.annotation.JsonIgnore
import com.jinho.springboot.restwebservices.user.entity.User
import jakarta.persistence.*


@Entity
class Post (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Int,

    var description: String,

    @ManyToOne
    @JsonIgnore
    var user: User

){

}