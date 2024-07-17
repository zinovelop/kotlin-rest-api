package com.jinho.springboot.restwebservices.user.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.jinho.springboot.restwebservices.entity.post.Post
import jakarta.persistence.*
import jakarta.validation.constraints.Past
import jakarta.validation.constraints.Size
import java.time.LocalDate

@Entity(name = "user_details")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @field:Size(min = 2, max = 10, message = "이름은 최소 2자 이상 10자 이하로 정해야 합니다.")
    @JsonProperty("user_name")
    var name: String,

    @field:Past(message = "생일이 현재보다 과거여야 합니다.")
    val birthDate: LocalDate,

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    var posts:List<Post>
    ){

    override fun toString(): String {
        return "User(id=$id, name='$name', birthDate=$birthDate)"
    }

}