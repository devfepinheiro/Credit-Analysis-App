package com.leonardo.creditanalysisapp.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.leonardo.creditanalysisapp.domain.Proposal;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RabbitNotificationService {
    
    private final RabbitTemplate rabbitTemplate;

    public void notification(String exchange, Proposal proposal) {
        rabbitTemplate.convertAndSend(exchange, "", proposal);
    }
}
