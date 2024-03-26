package com.leonardo.creditanalysisapp.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Proposal {
    private Long id;
    private Double proposalValue;
    private int paymentTerm;
    private Boolean approved;
    private boolean integrated;
    private String observation;
    private User user;
}
