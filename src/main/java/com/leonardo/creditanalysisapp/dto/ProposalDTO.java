package com.leonardo.creditanalysisapp.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProposalDTO {
    private Long id;

    @NotNull(message = "Proposal value is required")
    @Positive(message = "Proposal value must be positive")
    private Double proposalValue;

    @NotNull(message = "Payment term is required")
    @PositiveOrZero(message = "Payment term must be zero or positive")
    private Integer paymentTerm;

    private Boolean approved;
    private Boolean integrated;
    private String observation;

    @Valid
    @NotNull(message = "User information is required")
    private UserDTO user;
}
