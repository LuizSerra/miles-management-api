package com.netmaxi.mm.api.transactions.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.transactions.Transaction;
import com.netmaxi.mm.api.transactions.TransactionRepository;
import com.netmaxi.mm.api.transactions.dto.TransactionRequestDTO;
import com.netmaxi.mm.api.transactions.dto.TransactionResponseDTO;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	private final TransactionRepository transactionRepository;
	
	public TransactionServiceImpl(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	@Override
	public TransactionResponseDTO create(TransactionRequestDTO transactionRequestDTO) {
		Transaction transactionCreated = new Transaction(transactionRequestDTO);
		transactionRepository.save(transactionCreated);
		return new TransactionResponseDTO(transactionCreated);
	}

	@Override
	public Page<TransactionResponseDTO> list(Pageable pag) {
		var page = transactionRepository.findAll(pag);
		return page.map(TransactionResponseDTO::new);
	}

	@Override
	public TransactionResponseDTO findByID(Long id) {
		var transactionFound = transactionRepository.getReferenceById(id);
		return new TransactionResponseDTO(transactionFound);
	}

}
