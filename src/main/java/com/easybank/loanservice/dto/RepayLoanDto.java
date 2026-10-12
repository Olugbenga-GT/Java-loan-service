package com.easybank.loanservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RepayLoanDto {

    @NotNull(message = "Repayment amount must not be null")
    @Positive(message = "Repayment amount must be greater than zero")
    private BigDecimal repaymentAmount;

    @NotBlank(message = "Loan number must not be empty")
    private String loanNumber;
}
