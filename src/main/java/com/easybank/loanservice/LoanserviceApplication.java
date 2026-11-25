package com.easybank.loanservice;

import org.springframework.boot.SpringApplication;
import com.easybank.loanservice.dto.LoanContactInfoDto;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableConfigurationProperties(value = {LoanContactInfoDto.class})
@SpringBootApplication
@EnableJpaAuditing
public class LoanserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanserviceApplication.class, args);
	}

}
