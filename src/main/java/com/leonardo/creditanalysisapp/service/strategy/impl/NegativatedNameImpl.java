package com.leonardo.creditanalysisapp.service.strategy.impl;

import java.util.Random;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.leonardo.creditanalysisapp.domain.Proposal;
import com.leonardo.creditanalysisapp.exception.StrategyException;
import com.leonardo.creditanalysisapp.service.strategy.IPointCalc;
import com.leonardo.creditanalysisapp.statics.Messages;

@Order(2)
@Component
public class NegativatedNameImpl implements IPointCalc {

    @Override
    public int calc(Proposal proposal) {
        if (negativatedName()) {
            throw new StrategyException(String.format(Messages.NEGATIVATED_NAME, proposal.getUser().getName().toUpperCase()));
        }
        return 100;
    }

    public boolean negativatedName() {
        return new Random().nextBoolean();
    }

}
