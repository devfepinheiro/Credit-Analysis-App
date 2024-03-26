package com.leonardo.creditanalysisapp.service.strategy.impl;

import java.util.Random;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.leonardo.creditanalysisapp.domain.Proposal;
import com.leonardo.creditanalysisapp.exception.StrategyException;
import com.leonardo.creditanalysisapp.service.strategy.IPointCalc;
import com.leonardo.creditanalysisapp.statics.Messages;

@Component
public class ScoreNameImpl implements IPointCalc {
    @Order(3)
    @Override
    public int calc(Proposal proposal) {
        int score = score();

        if (score < 200) {
            throw new StrategyException(String.format(Messages.LOW_SCORE_NAME, proposal.getUser().getName().toUpperCase()));
        }

        if (score <= 400) {
            return 150;
        }
        if (score <= 650) {
            return 200;
        }

        return 220;
    }

    private int score() {
        return new Random().nextInt(0, 1000);
    }
}
