package com.leonardo.creditanalysisapp.service.strategy.impl;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.leonardo.creditanalysisapp.domain.Proposal;
import com.leonardo.creditanalysisapp.service.strategy.IPointCalc;

@Component
public class OtherLoansImpl implements IPointCalc {

    @Override
    public int calc(Proposal proposal) {
        return otherLoans() ? -80 : 0;
    }

    public boolean otherLoans() {
        return new Random().nextBoolean();
    }
}
