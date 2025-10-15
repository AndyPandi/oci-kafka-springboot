package com.example.kafkademo.service;

import com.example.kafkademo.config.KafkaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // 임시로 비활성화 - DNS 문제 해결 후 활성화
    // @KafkaListener(topics = KafkaConfig.TOPIC_NAME, groupId = "spring-group")
    public void consume(String message) {
        logger.info("Received message: {}", message);
        
        // Broadcast message to all connected WebSocket clients
        messagingTemplate.convertAndSend("/topic/messages", message);
    }
}
