package com.netmaxi.mm.api.transactions.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.netmaxi.mm.api.program.Program;
import com.netmaxi.mm.api.transactions.TransactionType;

public record TransactionRequestDTO(
		LocalDate date, 
		TransactionType transactionType, 
		Integer amount,
		BigDecimal value,
		Program sourceProgram, 
		Program targetProgram) 
{}
