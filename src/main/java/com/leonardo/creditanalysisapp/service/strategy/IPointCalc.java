package com.leonardo.creditanalysisapp.service.strategy;

import com.leonardo.creditanalysisapp.domain.Proposal;

public interface IPointCalc {
    int calc(Proposal proposal);
}
