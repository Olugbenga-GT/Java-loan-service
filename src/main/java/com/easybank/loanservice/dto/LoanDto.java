package com.easybank.loanservice.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoanDto {

    @NotBlank(message = "Mobile number must not be empty")
    @Pattern(regexp = "^\\d{10,15}$", message = "Mobile number must be between 10 and 15 digits")
    private String mobileNumber;

    @NotBlank(message = "Loan type must not be empty")
    private String loanType;

    @NotNull(message = "Loan amount must not be null")
    @Positive(message = "Loan amount must be greater than zero")
    private BigDecimal loanAmount;

    private String loanNumber;

    private BigDecimal amountPaid;

    private BigDecimal outstandingAmount;

}
