package com.netmaxi.mm.api.transactions.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.transactions.TransactionRepository;
import com.netmaxi.mm.api.transactions.dto.TransactionRequestDTO;
import com.netmaxi.mm.api.transactions.dto.TransactionResponseDTO;
import com.netmaxi.mm.api.transactions.strategy.TransactionStrategy;
import com.netmaxi.mm.api.transactions.strategy.TransactionStrategyFactory;

import lombok.extern.log4j.Log4j2;

@Log4j2
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
		log.info("CREATING TRANSACTION:::START::: TYPE: {}", transactionRequestDTO.transactionType());
		TransactionStrategy transactionStrategy = this.transactionStrategyFactory.getStrategy(transactionRequestDTO.transactionType());
		var transaction = transactionStrategy.execute(transactionRequestDTO);
		transactionRepository.save(transaction);
		log.info("CREATING TRANSACTION:::END");
		return new TransactionResponseDTO(transaction);
	}

	@Override
	public Page<TransactionResponseDTO> list(Pageable pag) {
		log.info("LISTING TRANSACTIONS:::START");
		var page = transactionRepository.findAll(pag);
		log.info("LISTING TRANSACTIONS:::END");
		return page.map(TransactionResponseDTO::new);
	}

	@Override
	public TransactionResponseDTO findByID(Long id) {
		log.info("LISTING TRANSACTION BY ID:::START");
		var transactionFound = transactionRepository.getReferenceById(id);
		log.info("LISTING TRANSACTION BY ID:::END::: TRANSACTION FOUND: {}", transactionFound);
		return new TransactionResponseDTO(transactionFound);
	}

}
