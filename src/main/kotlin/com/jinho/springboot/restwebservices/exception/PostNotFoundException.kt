package com.jinho.springboot.restwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
class PostNotFoundException (message : String) : RuntimeException(message)
