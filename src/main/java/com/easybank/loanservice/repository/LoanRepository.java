package com.easybank.loanservice.repository;


import com.easybank.loanservice.entity.Loan;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    //select * from loan where mobile_number = 12345
    List<Loan> findByMobileNumber(String mobileNumber);

    Loan findByLoanNumber(String loanNumber);

    @Transactional
    void deleteByLoanNumber(String loanNumber);
}

