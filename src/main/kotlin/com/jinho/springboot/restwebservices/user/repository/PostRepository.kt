package com.jinho.springboot.restwebservices.user.repository

import com.jinho.springboot.restwebservices.user.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Int> {
}