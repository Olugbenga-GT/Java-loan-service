package com.easybank.loanservice.service;

import com.easybank.loanservice.dto.LoanDto;
import com.easybank.loanservice.dto.RepayLoanDto;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface ILoanService {
    void createLoan(LoanDto loanDto);
    List<LoanDto> fetchLoans(String mobileNumber);
    boolean deleteLoan(String loanNumber);
    LoanDto fetchLoan(String loanNumber);
    @Transactional
    LoanDto repayLoan(RepayLoanDto repayLoanDto);
//    BigDecimal repayLoan(BigDecimal repaymentAmount, String loanNumber);
}
