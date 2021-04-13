package com.relationtest.dbRelation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class Controllers {


    @GetMapping(value = "/test")
    public String testMethod() {
        return "tested ok!...";
    }
}
