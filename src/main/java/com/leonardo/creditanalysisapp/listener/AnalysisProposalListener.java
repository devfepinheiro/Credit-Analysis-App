package com.leonardo.creditanalysisapp.listener;

import com.leonardo.creditanalysisapp.dto.ProposalDTO;
import com.leonardo.creditanalysisapp.service.CreditAnalysisService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class AnalysisProposalListener {

    private final CreditAnalysisService creditAnalysisService;

    @RabbitListener(queues = "${rabbitmq.queue.pending.proposal}")
    public void analysisProposal(ProposalDTO proposal) {
        try {
            log.info("Received proposal for analysis with value: {}", proposal.getProposalValue());
            creditAnalysisService.analysis(proposal);
            log.debug("Proposal analysis completed successfully");
        } catch (Exception e) {
            log.error("Error processing proposal: {}", e.getMessage(), e);
            // Consider implementing a dead letter queue for failed messages
        }
    }
}
