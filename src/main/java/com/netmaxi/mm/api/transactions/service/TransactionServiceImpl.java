package com.netmaxi.mm.api.transactions.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.transactions.Transaction;
import com.netmaxi.mm.api.transactions.TransactionRepository;
import com.netmaxi.mm.api.transactions.dto.TransactionRequestDTO;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	private final TransactionRepository transactionRepository;
	
	public TransactionServiceImpl(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	@Override
	public Transaction create(TransactionRequestDTO transactionRequestDTO) {
		Transaction transactionCreated = new Transaction(transactionRequestDTO);
		transactionRepository.save(transactionCreated);
		return transactionCreated;
	}

	@Override
	public Page<Transaction> list(Pageable pag) {
		var page = transactionRepository.findAll(pag);
		return page;
	}

	@Override
	public Transaction findByID(Long id) {
		var transactionFound = transactionRepository.getReferenceById(id);
		return transactionFound;
	}

}
