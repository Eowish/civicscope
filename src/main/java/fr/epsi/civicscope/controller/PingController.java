package fr.epsi.civicscope.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class PingController {
    
    @PostMapping("/ping") 
    public String Ping() {
        return "pong";
    }
}
