package com.leonardo.creditanalysisapp.mapper;

import com.leonardo.creditanalysisapp.domain.Proposal;
import com.leonardo.creditanalysisapp.dto.ProposalDTO;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProposalMapper {

    private final UserMapper userMapper;

    public ProposalDTO toDTO(Proposal entity) {
        if (entity == null) {
            return null;
        }

        return ProposalDTO.builder()
                .id(entity.getId())
                .proposalValue(entity.getProposalValue())
                .paymentTerm(entity.getPaymentTerm())
                .approved(entity.getApproved())
                .integrated(entity.getIntegrated())
                .observation(entity.getObservation())
                .user(userMapper.toDTO(entity.getUser()))
                .build();
    }

    public Proposal toEntity(ProposalDTO dto) {
        if (dto == null) {
            return null;
        }

        return Proposal.builder()
                .id(dto.getId())
                .proposalValue(dto.getProposalValue())
                .paymentTerm(dto.getPaymentTerm())
                .approved(dto.getApproved())
                .integrated(dto.getIntegrated())
                .observation(dto.getObservation())
                .user(userMapper.toEntity(dto.getUser()))
                .build();
    }
}
