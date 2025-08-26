package com.paravar;

import com.paravar.basic.BasicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {

    private final BasicService basicService;

    @GetMapping("/basic")
    public String sendSimple() {
        basicService.sendBasicMessage("Hello");
        return "Message sent";
    }
}