package com.leonardo.creditanalysisapp.service.strategy;

import com.leonardo.creditanalysisapp.domain.Proposal;

/**
 * Interface for credit analysis scoring strategies. Each implementation
 * calculates points for a specific aspect of the credit analysis.
 */
public interface IPointCalc {
    /**
     * Calculates points for a proposal based on a specific analysis criterion.
     * 
     * @param proposal The proposal to analyze
     * @return An integer representing the points assigned by this strategy
     */
    int calc(Proposal proposal);
}
