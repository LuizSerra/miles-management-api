package com.netmaxi.mm.api.transactions.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.program.service.ProgramService;
import com.netmaxi.mm.api.transactions.TransactionRepository;
import com.netmaxi.mm.api.transactions.dto.TransactionRequestDTO;
import com.netmaxi.mm.api.transactions.dto.TransactionResponseDTO;
import com.netmaxi.mm.api.transactions.strategy.TransactionStrategy;
import com.netmaxi.mm.api.transactions.strategy.TransactionStrategyFactory;
import com.netmaxi.mm.api.user.service.UserService;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	private final TransactionRepository transactionRepository;
	private final ProgramService programService;
	private final UserService userService;
	private final TransactionStrategyFactory transactionStrategyFactory;
	
	public TransactionServiceImpl(TransactionRepository transactionRepository, ProgramService programService,
			UserService userService, TransactionStrategyFactory transactionStrategyFactory) {
		this.transactionRepository = transactionRepository;
		this.programService = programService;
		this.userService = userService;
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
