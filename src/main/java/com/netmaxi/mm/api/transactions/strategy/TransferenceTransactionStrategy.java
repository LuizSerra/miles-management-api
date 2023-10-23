package com.netmaxi.mm.api.transactions.strategy;

import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.program.ProgramRepository;
import com.netmaxi.mm.api.program.service.ProgramService;
import com.netmaxi.mm.api.transactions.Transaction;
import com.netmaxi.mm.api.transactions.dto.TransactionRequestDTO;
import com.netmaxi.mm.api.user.UserRepository;

@Service
public class TransferenceTransactionStrategy implements TransactionStrategy {

	private final ProgramRepository programRepository;
	private final UserRepository userRepository;
	private final ProgramService programService;
	
	
	public TransferenceTransactionStrategy(ProgramRepository programRepository, UserRepository userRepository, ProgramService programService) {
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
		var receiver = this.programRepository.findById(transactionRequestDTO.programReceiver())
				.orElseThrow(() -> new IllegalArgumentException("Program Receiver must be informed."));
		
		this.programService.validateTransaction(sender, transactionRequestDTO.amount());
		
		sender.setBalance(sender.getBalance() - transactionRequestDTO.amount());
		sender.setBalanceAvailable(sender.getBalanceAvailable() - transactionRequestDTO.amount());
		
		receiver.setBalance(receiver.getBalance() + transactionRequestDTO.amount());
		receiver.setBalanceAvailable(receiver.getBalanceAvailable() + transactionRequestDTO.amount());
		
		Transaction transaction = new Transaction(transactionRequestDTO);
		transaction.setProgramSender(sender);
		transaction.setProgramReceiver(receiver);
		transaction.setUser(user);
		
		
		return transaction;
	}

}
