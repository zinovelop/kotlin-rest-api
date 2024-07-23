package com.jinho.springboot.restwebservices.confnig

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SpringSecurityConfiguration{

    @Bean
    fun filterChain(http : HttpSecurity) : SecurityFilterChain{
        http
            .authorizeHttpRequests{it.anyRequest().authenticated()}
            .csrf { it.disable() }
            .httpBasic(withDefaults())
        return http.build()
    }
}