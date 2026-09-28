package com.easybank.loanservice.service.impl;


import com.easybank.loanservice.dto.LoanDto;
import com.easybank.loanservice.dto.RepayLoanDto;
import com.easybank.loanservice.entity.Loan;
import com.easybank.loanservice.mapper.LoanMapper;
import com.easybank.loanservice.repository.LoanRepository;
import com.easybank.loanservice.service.ILoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class LoanServiceImpl implements ILoanService {

    @Autowired
    private LoanRepository loanRepository;


    /**
     * @param loanDto - CustomerDto Object
     */
    @Override
    public void createLoan(LoanDto loanDto) {
        Loan newLoan = LoanMapper.mapToLoan(loanDto, new Loan());
        newLoan.setLoanNumber(generateLoanNumber());
        Loan savedLoan = loanRepository.save(newLoan);
        loanRepository.save(savedLoan);
    }


    private String generateLoanNumber() {
        long randomLoanNumber = 10000 + new Random().nextInt(9000);
        return "easyloan-" + randomLoanNumber;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Loans based on a given mobileNumber
     */
    @Override
    public List<LoanDto> fetchLoans(String mobileNumber) {
        List<Loan> loans = loanRepository.findByMobileNumber(mobileNumber);

        List<LoanDto> loanDtos = new ArrayList<>();

        for (Loan loan : loans) {
            LoanDto loanDto = LoanMapper.mapToLoanDto(loan,new LoanDto());
            loanDtos.add(loanDto);
        }

        return loanDtos;
    }

    @Override
    public LoanDto fetchLoan ( @RequestParam String loanNumber){
        Loan loan = loanRepository.findByLoanNumber(loanNumber);
        return LoanMapper.mapToLoanDto(loan,new LoanDto());
    }

    @Override
//    public BigDecimal repayLoan(@RequestParam BigDecimal repaymentAmount, String loanNumber) {
    public BigDecimal repayLoan(RepayLoanDto repayLoanDto) {

        BigDecimal repaymentAmount = repayLoanDto.getRepaymentAmount();
        String loanNumber = repayLoanDto.getLoanNumber();

        Loan loan = loanRepository.findByLoanNumber(loanNumber);

        if (loan == null) {
            throw new RuntimeException("Loan not found");
        }

        BigDecimal currentAmountPaid = loan.getAmountPaid() != null ? loan.getAmountPaid() : BigDecimal.ZERO;
        BigDecimal outstandingAmount = loan.getOutstandingAmount() != null ? loan.getOutstandingAmount() : loan.getTotalLoan();

        if (repaymentAmount.compareTo(outstandingAmount) > 0) {
            throw new RuntimeException("Repayment amount exceeds outstanding loan balance");
        }

        BigDecimal newAmountPaid = currentAmountPaid.add(repaymentAmount);
        BigDecimal newOutstandingAmount = outstandingAmount.subtract(repaymentAmount);

        loan.setAmountPaid(newAmountPaid);
        loan.setOutstandingAmount(newOutstandingAmount);

        loanRepository.save(loan);

        return newOutstandingAmount;
    }

    ;


    /**
     * @param loanNumber - Input Mobile Number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    @Override
    public boolean deleteLoan(String loanNumber) {
        Loan loan = loanRepository.findByLoanNumber(loanNumber);
        if(loan != null){
            loanRepository.deleteByLoanNumber(loanNumber);
            return true;
        }
        return false;
    }

}
