package com.paravar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping("/")
    String home(){
        return "index";
    }

    @GetMapping("/secure")
    String secure(){
        return "secure";
    }
}
