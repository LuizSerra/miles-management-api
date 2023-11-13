package com.netmaxi.mm.api.transactions.strategy;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.miles.Miles;
import com.netmaxi.mm.api.miles.MilesRepository;
import com.netmaxi.mm.api.program.ProgramRepository;
import com.netmaxi.mm.api.transactions.Transaction;
import com.netmaxi.mm.api.transactions.dto.TransactionRequestDTO;
import com.netmaxi.mm.api.user.UserRepository;

@Service
public class BonificationTransactionStrategy implements TransactionStrategy {

	private final ProgramRepository programRepository;
	private final UserRepository userRepository;
	private final MilesRepository milesRepository;
	
	public BonificationTransactionStrategy(ProgramRepository programRepository, UserRepository userRepository,
			MilesRepository milesRepository) {
		this.programRepository = programRepository;
		this.userRepository = userRepository;
		this.milesRepository = milesRepository;
	}


	
	@Override
	public Transaction execute(TransactionRequestDTO transactionRequestDTO) {
		
		var user = this.userRepository.findById(transactionRequestDTO.user())
				.orElseThrow(() -> new IllegalArgumentException("User must be informed."));
		var sender = this.programRepository.findById(transactionRequestDTO.programSender())
				.orElseThrow(() -> new IllegalArgumentException("Program sender must be informed."));
				
		Miles miles = new Miles(transactionRequestDTO.amount(), transactionRequestDTO.price(), transactionRequestDTO.expiration(), sender);
		
		sender.setBalance(sender.getBalance() + transactionRequestDTO.amount());
		sender.setBalanceAvailable(sender.getBalanceAvailable() + transactionRequestDTO.amount());
		
		miles.setProgram(sender);
		miles = milesRepository.save(miles);
		
		Transaction transaction = new Transaction(transactionRequestDTO);
		transaction.setMiles(miles);
		transaction.setProgramSender(sender);
		transaction.setUser(user);
		
		return transaction;
	}

}
