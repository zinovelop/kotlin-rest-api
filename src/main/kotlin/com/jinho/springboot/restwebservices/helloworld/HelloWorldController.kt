package com.jinho.springboot.restwebservices.helloworld

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController {

    @GetMapping("/hello-world")
    fun helloWorld():String = "Hello World"

    @GetMapping("/hello-world-bean")
    fun helloWorldBean():HelloWorldBean = HelloWorldBean("Hello World")

    @GetMapping("/hello-world/path-variable/{name}")
    fun helloWorldJson(@PathVariable name:String):HelloWorldBean = HelloWorldBean("Hello ${name}")

}