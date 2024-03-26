package com.leonardo.creditanalysisapp.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import com.leonardo.creditanalysisapp.domain.Proposal;
import com.leonardo.creditanalysisapp.service.CreditAnalysisService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class AnalysisProposalListener {

    private final CreditAnalysisService creditAnalysisService;

    @RabbitListener(queues = "${rabbitmq.queue.pending.proposal}")
    public void analysisProposal(Proposal proposal) {
        creditAnalysisService.analysis(proposal);
    }
}
