package com.jinho.springboot.restwebservices.filtering

import com.fasterxml.jackson.annotation.JsonFilter
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

//@JsonIgnoreProperties(value = ["field1","field2"])
@JsonFilter("SomeBeanFilter")
class SomeBean(
    val field1: String,
//    @JsonIgnore
    var field2: String,
    var field3: String
) {
    override fun toString(): String {
        return "SomeBean(field1='$field1', field2='$field2', field3='$field3')"
    }
}
