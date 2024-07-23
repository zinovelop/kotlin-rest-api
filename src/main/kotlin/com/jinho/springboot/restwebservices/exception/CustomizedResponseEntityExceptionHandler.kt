package com.jinho.springboot.restwebservices.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class CustomizedResponseEntityExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(value=[Exception::class])
    fun handleAllExceptions(ex: Exception, request: WebRequest): ResponseEntity<ErrorDetails> {
        val errorDetails:ErrorDetails = ErrorDetails(LocalDateTime.now(), ex.message.toString(), request.getDescription(false))
        return ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(value = [UserNotFoundException::class, PostNotFoundException::class])
    fun handleUserNotFoundExceptions(ex: Exception, request: WebRequest): ResponseEntity<ErrorDetails> {
        val errorDetails:ErrorDetails = ErrorDetails(LocalDateTime.now(), ex.message.toString(), request.getDescription(false))
        return ResponseEntity(errorDetails, HttpStatus.NOT_FOUND)
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        header: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val errorDetails:ErrorDetails = ErrorDetails(LocalDateTime.now(), ex.fieldError?.defaultMessage.toString(), request.getDescription(false))
        return ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST)
    }
}