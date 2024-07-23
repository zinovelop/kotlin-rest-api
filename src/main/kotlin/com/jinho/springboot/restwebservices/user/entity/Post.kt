package com.jinho.springboot.restwebservices.user.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.validation.constraints.Size


@Entity
class Post (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Int,

    @field:Size(min = 5)
    var description: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    var user: User?
){

    override fun toString(): String {
        return "Post(id=$id, description='$description', user=$user)"
    }
}