package com.anyj.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RequestMapping("/test")
@RestController
public class TestController {

    @PostMapping("/get")
    public String test() {
        System.out.println("teeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        return "test";
    }
}
