package com.netmaxi.mm.api.transactions.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.netmaxi.mm.api.transactions.TransactionType;

public record TransactionRequestDTO(
		LocalDate date,
		LocalDate expiration,
		Integer amount,
		BigDecimal price,
		TransactionType transactionType,
		Long programSender,
		Long programReceiver,
		Long user
		) 
{}
