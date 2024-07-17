package com.jinho.springboot.restwebservices.filtering

import com.fasterxml.jackson.databind.ser.FilterProvider
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider
import org.springframework.http.converter.json.MappingJacksonValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class FilteringController {

    @GetMapping("/filtering")
    fun filtering() :MappingJacksonValue {
        val someBean = SomeBean("value1","value2","value3")

        val mappingJacksonValue: MappingJacksonValue = MappingJacksonValue(someBean)
        return filtering(mappingJacksonValue,"field2","field3")
    }

    @GetMapping("/filtering-list")
    fun filteringList() :MappingJacksonValue {
        val beanArr = listOf(
            SomeBean("value1","value2","value3"),
            SomeBean("value4","value5","value6"),
            SomeBean("value7","value8","value9")
        )
        val mappingJacksonValue: MappingJacksonValue = MappingJacksonValue(beanArr)
        return filtering(mappingJacksonValue,"field1","field2")
    }

    private fun filtering(mappingJacksonValue:MappingJacksonValue, vararg field:String) : MappingJacksonValue {
        val filter:SimpleBeanPropertyFilter  = SimpleBeanPropertyFilter.filterOutAllExcept(*field)
        val filters:FilterProvider = SimpleFilterProvider().addFilter("SomeBeanFilter",filter)
        mappingJacksonValue.filters = filters
        return mappingJacksonValue
    }
}