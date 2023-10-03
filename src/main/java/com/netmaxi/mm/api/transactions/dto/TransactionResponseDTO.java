package com.netmaxi.mm.api.transactions.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.netmaxi.mm.api.transactions.Transaction;
import com.netmaxi.mm.api.transactions.TransactionType;

public record TransactionResponseDTO(Long id, LocalDate data, TransactionType tipo, Integer quantidade, BigDecimal valor) {
	public TransactionResponseDTO(Transaction transaction) {
		this(transaction.getId(), transaction.getDate(), transaction.getTransactionType(), transaction.getAmount(), transaction.getValue());
	}
}
