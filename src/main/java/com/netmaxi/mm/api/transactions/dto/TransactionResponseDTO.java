package com.netmaxi.mm.api.transactions.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.netmaxi.mm.api.program.Program;
import com.netmaxi.mm.api.transactions.Transaction;
import com.netmaxi.mm.api.transactions.TransactionType;

public record TransactionResponseDTO(Long id, LocalDate data, Integer quantidade, BigDecimal valor, TransactionType tipo, Program programSender, Program programReceiver) {
	public TransactionResponseDTO(Transaction transaction) {
		this(transaction.getId(), transaction.getDate(),  transaction.getAmount(), transaction.getPrice(), transaction.getTransactionType(), transaction.getProgramSender(), transaction.getProgramReceiver());
	}
}
