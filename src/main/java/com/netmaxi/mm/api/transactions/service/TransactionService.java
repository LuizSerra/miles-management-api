package com.netmaxi.mm.api.transactions.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.netmaxi.mm.api.transactions.Transaction;
import com.netmaxi.mm.api.transactions.dto.TransactionRequestDTO;

public interface TransactionService {
	
	public Transaction create(TransactionRequestDTO transactionRequestDTO);
	
	public Page<Transaction> list(Pageable pag);
	
	public Transaction findByID(Long id);

}
