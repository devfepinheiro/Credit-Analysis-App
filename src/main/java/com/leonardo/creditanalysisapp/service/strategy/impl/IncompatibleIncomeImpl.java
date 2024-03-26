package com.leonardo.creditanalysisapp.service.strategy.impl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.leonardo.creditanalysisapp.domain.Proposal;
import com.leonardo.creditanalysisapp.service.strategy.IPointCalc;

@Order(1)
@Component
public class IncompatibleIncomeImpl implements IPointCalc {

    @Override
    public int calc(Proposal proposal) {
        if (incompatibleIncome(proposal)) {
            return 0;
        }
        return 100;
    }

    private boolean incompatibleIncome(Proposal proposal) {
        var income = proposal.getUser().getFinancialIncome();
        var proposalValue = proposal.getProposalValue();

        return income < proposalValue ? true : false;
    }

}
