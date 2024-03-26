package com.leonardo.creditanalysisapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.leonardo.creditanalysisapp.domain.Proposal;
import com.leonardo.creditanalysisapp.exception.StrategyException;
import com.leonardo.creditanalysisapp.service.strategy.IPointCalc;
import com.leonardo.creditanalysisapp.statics.Messages;

@Service
public class CreditAnalysisService {

    private List<IPointCalc> pointCalcList;

    @Autowired
    private RabbitNotificationService rabbitNotificationService;

    @Value("${rabbitmq.completed-exchange.exchange}")
    private String exchangePropostaConcluida;

    public CreditAnalysisService(List<IPointCalc> pointCalcList) {
        this.pointCalcList = pointCalcList;
    }

    public void analysis(Proposal proposal) {
        try {
            var aprov = pointCalcList.stream()
                    .mapToInt(imple -> imple.calc(proposal)).sum() > 350 ? true : false;

            proposal.setApproved(aprov);
            proposal.setObservation(String.format(aprov ? Messages.APPROVED_NAME : Messages.NOT_APPROVED_NAME,
                    proposal.getUser().getName().toUpperCase()));

        } catch (StrategyException ex) {
            proposal.setApproved(false);
            proposal.setObservation(ex.getMessage());
        }
        rabbitNotificationService.notification(exchangePropostaConcluida, proposal);
    }

}
