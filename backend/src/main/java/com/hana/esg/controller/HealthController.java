package com.hana.esg.controller;

import com.hana.esg.dto.response.HealthResponse;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public HealthResponse health() {
        return new HealthResponse("ok", LocalDateTime.now().toString());
    }
}
