package com.example.kafkademo.controller;

import com.example.kafkademo.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/publish")
    public ResponseEntity<String> publishMessage(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        if (message != null && !message.trim().isEmpty()) {
            kafkaProducerService.sendMessage(message);
            return ResponseEntity.ok("Message sent successfully");
        } else {
            return ResponseEntity.badRequest().body("Message cannot be empty");
        }
    }
}
