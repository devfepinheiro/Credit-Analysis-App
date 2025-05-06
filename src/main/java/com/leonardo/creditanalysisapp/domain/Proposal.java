package com.leonardo.creditanalysisapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Proposal {
    private Long id;
    private Double proposalValue;
    private Integer paymentTerm;
    private Boolean approved;
    private Boolean integrated;
    private String observation;
    private User user;
}
