package com.netmaxi.mm.api.transactions.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.transactions.TransactionRepository;
import com.netmaxi.mm.api.transactions.dto.TransactionRequestDTO;
import com.netmaxi.mm.api.transactions.dto.TransactionResponseDTO;
import com.netmaxi.mm.api.transactions.strategy.TransactionStrategy;
import com.netmaxi.mm.api.transactions.strategy.TransactionStrategyFactory;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	private final TransactionRepository transactionRepository;
	private final TransactionStrategyFactory transactionStrategyFactory;
	
	public TransactionServiceImpl(TransactionRepository transactionRepository,
			TransactionStrategyFactory transactionStrategyFactory) {
		this.transactionRepository = transactionRepository;
		this.transactionStrategyFactory = transactionStrategyFactory;
	}

	@Override
	public TransactionResponseDTO create(TransactionRequestDTO transactionRequestDTO) {
		TransactionStrategy transactionStrategy = this.transactionStrategyFactory.getStrategy(transactionRequestDTO.transactionType());
		var transaction = transactionStrategy.execute(transactionRequestDTO);
		
		transactionRepository.save(transaction);
		return new TransactionResponseDTO(transaction);
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
