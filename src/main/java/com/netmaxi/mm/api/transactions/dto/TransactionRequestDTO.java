package com.netmaxi.mm.api.transactions.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.netmaxi.mm.api.program.Program;
import com.netmaxi.mm.api.transactions.TransactionType;

public record TransactionRequestDTO(
		LocalDate date, 
		Integer amount,
		BigDecimal value,
		TransactionType transactionType,
		Program program 
		) 
{}
