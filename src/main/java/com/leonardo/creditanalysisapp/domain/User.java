package com.leonardo.creditanalysisapp.domain; 

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String name;
    private String lastName;
    private String cpf;
    private String tellPhone;
    private Double financialIncome;
}
