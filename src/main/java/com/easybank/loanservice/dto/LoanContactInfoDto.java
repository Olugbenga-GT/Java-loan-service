package com.easybank.loanservice.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

// Binds the "loans.*" properties served by the config server
@ConfigurationProperties(prefix = "loans")
public record LoanContactInfoDto(String message, Map<String, String> contactDetails, List<String> onCallSupport) {
}
