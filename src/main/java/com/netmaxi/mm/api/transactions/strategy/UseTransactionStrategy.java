package com.netmaxi.mm.api.transactions.strategy;

import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.program.ProgramRepository;
import com.netmaxi.mm.api.program.service.ProgramService;
import com.netmaxi.mm.api.transactions.Transaction;
import com.netmaxi.mm.api.transactions.dto.TransactionRequestDTO;
import com.netmaxi.mm.api.user.UserRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UseTransactionStrategy implements TransactionStrategy {

	private final ProgramRepository programRepository;
	private final UserRepository userRepository;
	private final ProgramService programService;
	
	
	public UseTransactionStrategy(ProgramRepository programRepository, UserRepository userRepository, ProgramService programService) {
		this.programRepository = programRepository;
		this.userRepository = userRepository;
		this.programService = programService;
	}

	
	@Override
	public Transaction execute(TransactionRequestDTO transactionRequestDTO) {
		
		log.info("TRANSACTION BONIFICATION:::START");
		var user = this.userRepository.findById(transactionRequestDTO.user())
				.orElseThrow(() -> new IllegalArgumentException("User must be informed."));
		log.info("TRANSACTION BONIFICATION:::USER FOUND: {}", user);
		var sender = this.programRepository.findById(transactionRequestDTO.programSender())
				.orElseThrow(() -> new IllegalArgumentException("Program sender must be informed."));
		log.info("TRANSACTION BONIFICATION::: PROGRAM SENDER FOUND: {}", sender);
				
		this.programService.validateTransaction(sender.getBalance(), transactionRequestDTO.amount());
		//Once the miles are used, they are deducted from the total balance
		sender.setBalance(sender.getBalance() - transactionRequestDTO.amount());
				
		Transaction transaction = new Transaction(transactionRequestDTO);
		transaction.setProgramSender(sender);
		transaction.setUser(user);
		
		log.info("TRANSACTION BONIFICATION:::END:::TRANSACTION: {}", transaction);
		return transaction;
	}

}
