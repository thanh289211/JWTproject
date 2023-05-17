package com.mkboss.MkbossManage.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/hello")
    private ResponseEntity<String> helloWorld(){
        return ResponseEntity.ok("Hello world");
    }
}
