package com.MarkRight.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class test {
    @GetMapping
    public String test() {
        return "test lol";
    }
}
