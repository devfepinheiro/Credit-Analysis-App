package com.leonardo.creditanalysisapp.service.strategy.impl;

import com.leonardo.creditanalysisapp.domain.Proposal;
import com.leonardo.creditanalysisapp.service.strategy.IPointCalc;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Strategy implementation that evaluates if the user's income is compatible
 * with the proposal value. Assigns 0 points if income is less than proposal
 * amount, 100 points otherwise.
 */
@Order(1)
@Component
@Slf4j
public class IncompatibleIncomeImpl implements IPointCalc {

    private static final int PASSING_POINTS = 100;
    private static final int FAILING_POINTS = 0;

    @Override
    public int calc(Proposal proposal) {
        boolean hasCompatibleIncome = isIncomeCompatible(proposal);
        log.debug("Income compatibility check for proposal: income={}, value={}, result={}",
                proposal.getUser().getFinancialIncome(),
                proposal.getProposalValue(),
                hasCompatibleIncome);

        return hasCompatibleIncome ? PASSING_POINTS : FAILING_POINTS;
    }

    private boolean isIncomeCompatible(Proposal proposal) {
        double income = proposal.getUser().getFinancialIncome();
        double proposalValue = proposal.getProposalValue();

        return income >= proposalValue;
    }
}
