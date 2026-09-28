package com.easybank.loanservice.mapper;


import com.easybank.loanservice.dto.LoanDto;
import com.easybank.loanservice.entity.Loan;


public class LoanMapper {

    public static LoanDto mapToLoanDto(Loan loan, LoanDto loanDto) {
        loanDto.setLoanAmount(loan.getLoanAmount());
        loanDto.setLoanType(loan.getLoanType());
        loanDto.setMobileNumber(loan.getMobileNumber());
        return loanDto;
    }

    public static Loan mapToLoan(LoanDto loanDto, Loan loan) {
        loan.setLoanAmount(loanDto.getLoanAmount());
        loan.setLoanType(loanDto.getLoanType());
        loan.setMobileNumber(loanDto.getMobileNumber());
        return loan;
    }

}
