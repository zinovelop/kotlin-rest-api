package com.jinho.springboot.restwebservices.versioning

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class VersioningPersonController {

    @GetMapping("/v1/person")
    fun getFirstVersionOfPerson() : PersonV1 {
        return PersonV1("John")
    }

    @GetMapping("/v2/person")
    fun getSecondVersionOfPerson() : PersonV2 {
        return PersonV2(Name("John","Doe"))
    }

    @GetMapping(path= ["/person"], params = ["version=1"])
    fun getFirstFromParameterPerson() : PersonV1 {
        return PersonV1("John")
    }

    @GetMapping(value= ["/person"], params = ["version=2"])
    fun getSecondFromParameterPerson() : PersonV2 {
        return PersonV2(Name("John","Doe"))
    }

    @GetMapping(path= ["/person/header"], headers = ["X-API-VERSION=1"])
    fun getFirstFromHeaderPerson() : PersonV1 {
        return PersonV1("John")
    }

    @GetMapping(value= ["/person/header"], headers = ["X-API-VERSION=2"])
    fun getSecondFromHeaderPerson() : PersonV2 {
        return PersonV2(Name("John","Doe"))
    }

    @GetMapping(path= ["/person/accept"], produces = ["application/vnd.company.app-v1+json"])
    fun getFirstFromAcceptPerson() : PersonV1 {
        return PersonV1("John")
    }

    @GetMapping(value= ["/person/accept"], produces = ["application/vnd.company.app-v2+json"])
    fun getSecondFromAcceptPerson() : PersonV2 {
        return PersonV2(Name("John","Doe"))
    }

}