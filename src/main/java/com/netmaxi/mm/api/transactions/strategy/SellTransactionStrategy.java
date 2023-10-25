package com.netmaxi.mm.api.transactions.strategy;

import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.program.ProgramRepository;
import com.netmaxi.mm.api.program.service.ProgramService;
import com.netmaxi.mm.api.transactions.Transaction;
import com.netmaxi.mm.api.transactions.dto.TransactionRequestDTO;
import com.netmaxi.mm.api.user.UserRepository;

@Service
public class SellTransactionStrategy implements TransactionStrategy {

	private final ProgramRepository programRepository;
	private final UserRepository userRepository;
	private final ProgramService programService;
	
	
	public SellTransactionStrategy(ProgramRepository programRepository, UserRepository userRepository, ProgramService programService) {
		this.programRepository = programRepository;
		this.userRepository = userRepository;
		this.programService = programService;
	}

	
	@Override
	public Transaction execute(TransactionRequestDTO transactionRequestDTO) {
		
		var user = this.userRepository.findById(transactionRequestDTO.user())
				.orElseThrow(() -> new IllegalArgumentException("User must be informed."));
		var sender = this.programRepository.findById(transactionRequestDTO.programSender())
				.orElseThrow(() -> new IllegalArgumentException("Program sender must be informed."));
				
		this.programService.validateTransaction(sender.getBalanceAvailable(), transactionRequestDTO.amount());
		//In a sell the amount is deducted from the available balance, but still present in total balance. It'll be deducted from total balance when a ticket is generated
		sender.setBalanceAvailable(sender.getBalanceAvailable() - transactionRequestDTO.amount());
		
		Transaction transaction = new Transaction(transactionRequestDTO);
		transaction.setProgramSender(sender);
		transaction.setUser(user);
		
		
		return transaction;
	}

}
