package com.jinho.springboot.restwebservices.exception

import java.time.LocalDate

class ErrorDetails(
    private var message: String,
    private var timestamp: LocalDate,
    private var details: String
)