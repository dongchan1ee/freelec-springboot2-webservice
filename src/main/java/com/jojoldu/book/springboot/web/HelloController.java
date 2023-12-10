package com.jojoldu.book.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Controller를 JSON으로 반환
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
