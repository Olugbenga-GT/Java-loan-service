package com.easybank.loanservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
ResponseStatus(value = HttpStatus.BAD_REQUEST);
public class LoanNotFoundException  extends RuntimeException{

    public LoanNotFoundException  (String message) {
        super(message);
    }

}
