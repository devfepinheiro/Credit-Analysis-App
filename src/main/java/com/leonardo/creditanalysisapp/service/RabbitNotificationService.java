package com.leonardo.creditanalysisapp.service;

import com.leonardo.creditanalysisapp.domain.Proposal;
import com.leonardo.creditanalysisapp.dto.ProposalDTO;
import com.leonardo.creditanalysisapp.mapper.ProposalMapper;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitNotificationService {

    private final RabbitTemplate rabbitTemplate;
    private final ProposalMapper proposalMapper;

    /**
     * Sends a notification about the proposal status through RabbitMQ
     * 
     * @param exchange The exchange to publish the message to
     * @param proposal The proposal entity to be sent
     */
    public void notification(String exchange, Proposal proposal) {
        try {
            log.info("Sending notification to exchange: {} for proposal ID: {}", exchange,
                    proposal.getId() != null ? proposal.getId() : "new proposal");

            rabbitTemplate.convertAndSend(exchange, "", proposal);

            log.debug("Notification sent successfully");
        } catch (AmqpException e) {
            log.error("Failed to send notification to exchange: {}", exchange, e);
            // Consider implementing a retry mechanism or dead letter queue here
        }
    }

    /**
     * Overloaded method that accepts a DTO and converts it to an entity before
     * sending
     * 
     * @param exchange The exchange to publish the message to
     * @param proposalDTO The proposal DTO to be sent
     */
    public void notification(String exchange, ProposalDTO proposalDTO) {
        Proposal proposal = proposalMapper.toEntity(proposalDTO);
        notification(exchange, proposal);
    }
}
