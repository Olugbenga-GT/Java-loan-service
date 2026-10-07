package com.easybank.loanservice.controller;

import com.easybank.loanservice.constants.LoanConstants;
import com.easybank.loanservice.dto.LoanDto;
import com.easybank.loanservice.dto.RepayLoanDto;
import com.easybank.loanservice.dto.repayResponseDto;
import com.easybank.loanservice.dto.ResponseDto;
import com.easybank.loanservice.service.ILoanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("api/loan/")

@Tag(
        name ="CRUD REST APIs for Loan service for FinApp application ",
        description = "Rest APIs to create, repay, fetch and delete loans for FinApp banking app."
)
public class LoanController {

    @Autowired
    private ILoanService loanService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestBody LoanDto loanDto) {
        loanService.createLoan(loanDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(LoanConstants.MESSAGE_201));
    }

    @GetMapping("fetch-all-loans")
    public ResponseEntity<List<LoanDto>> fetchLoans(@RequestParam String mobileNumber) {
        List<LoanDto> loanDtos = loanService.fetchLoans(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loanDtos);
    }

    @GetMapping("fetch-loan")
    public  ResponseEntity<LoanDto> fetchLoan(@RequestParam String loanNumber){
        LoanDto loanDto = loanService.fetchLoan(loanNumber);
        return  ResponseEntity.status(HttpStatus.OK).body(loanDto);
    }

    @DeleteMapping("delete")
    public ResponseEntity<ResponseDto> deleteLoan(@RequestParam String loanNumber) {
        boolean isDeleted = loanService.deleteLoan(loanNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoanConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoanConstants.MESSAGE_417_DELETE));
        }
    }

    @PutMapping("repay-loan")
    public ResponseEntity<repayResponseDto > repayLoan(@RequestBody  RepayLoanDto repayLoanDto){
        repayResponseDto outstandingAmount = loanService.repayLoan(repayLoanDto);
        return ResponseEntity.ok(outstandingAmount);
    }

}
