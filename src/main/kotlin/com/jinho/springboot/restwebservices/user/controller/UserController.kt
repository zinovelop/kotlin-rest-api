package com.jinho.springboot.restwebservices.user.controller


import com.jinho.springboot.restwebservices.exception.PostNotFoundException
import com.jinho.springboot.restwebservices.exception.UserNotFoundException
import com.jinho.springboot.restwebservices.user.entity.Post
import com.jinho.springboot.restwebservices.user.entity.User
import com.jinho.springboot.restwebservices.user.repository.PostRepository
import com.jinho.springboot.restwebservices.user.service.UserDaoService
import jakarta.validation.Valid
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import kotlin.jvm.optionals.getOrNull

@RestController
class UserController(
    val service: UserDaoService,
    val postRepository: PostRepository
) {
    @GetMapping("/users")
    fun findAll():List<User> = service.findAll()

    @GetMapping("/users/{id}")
    fun findById(@PathVariable id:Int): EntityModel<User> {
        val user = service.findById(id) ?: throw UserNotFoundException("ID:${id} 은(는) 존재하지 않습니다.")
        val entityModel:EntityModel<User> = EntityModel.of(user)
        val link = linkTo(methodOn(this.javaClass).findAll())

        entityModel.add(link.withRel("all-users"))

        return entityModel
    }
    @PostMapping("/users")
    fun saveUser(@Valid @RequestBody user: User): ResponseEntity<User> {
        service.save(user)
//        return ResponseEntity(user, HttpStatus.CREATED)
//        val location: URI = URI.create("/users/${user.id}")
//        val location: URI = ServletUriComponentsBuilder
//                .fromCurrentRequestUri()
//                .path("/${user.id}")
//                .build()
//                .toUri()
        val location:URI = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(user.id)
                .toUri()
        return ResponseEntity.created(location).build()
    }

    @DeleteMapping("/users/{id}")
    fun deleteById(@PathVariable id:Int) {
        return service.delete(id);
    }

    /****************************************/

    @GetMapping("/users/{id}/posts")
    fun retrievePosts(@PathVariable id:Int): List<Post>? {
        val user = service.findById(id)?:throw UserNotFoundException("ID:${id} 은(는) 존재하지 않습니다.")

        if(user.posts.isNullOrEmpty()) {
            throw PostNotFoundException("${user.name}에 대한 게시글을 찾을 수 없습니다.")
        }
        return user.posts
    }

    @PostMapping("/users/{id}/posts")
    fun doPost(@PathVariable id:Int, @Valid @RequestBody post: Post): ResponseEntity<Post> {
        println(post.toString())
        val user = service.findById(id)?:throw UserNotFoundException("ID:${id} 은(는) 존재하지 않습니다.")

        post.user = user
        val savedPost:Post = postRepository.save(post)

        val location: URI = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(savedPost.id)
                .toUri()

        return ResponseEntity.created(location).build()
    }

    @GetMapping("/users/{id}/posts/{postId}")
    fun findPostById(@PathVariable id:Int, @PathVariable postId:Int): ResponseEntity<Post> {
        val post:Post = postRepository.findById(postId).getOrNull()?:throw PostNotFoundException("게시글을 찾을 수 없습니다.")

        return ResponseEntity(post, HttpStatus.OK)
    }
}