package com.netmaxi.mm.api.transactions.strategy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.miles.Miles;
import com.netmaxi.mm.api.miles.MilesRepository;
import com.netmaxi.mm.api.program.ProgramRepository;
import com.netmaxi.mm.api.program.service.ProgramService;
import com.netmaxi.mm.api.transactions.Transaction;
import com.netmaxi.mm.api.transactions.dto.TransactionRequestDTO;
import com.netmaxi.mm.api.user.UserRepository;

@Service
public class SellTransactionStrategy implements TransactionStrategy {

	private final ProgramRepository programRepository;
	private final UserRepository userRepository;
	private final MilesRepository milesRepository;
	private final ProgramService programService;
	
	public SellTransactionStrategy(ProgramRepository programRepository, UserRepository userRepository,
			MilesRepository milesRepository, ProgramService programService) {
		super();
		this.programRepository = programRepository;
		this.userRepository = userRepository;
		this.milesRepository = milesRepository;
		this.programService = programService;
	}



	@Override
	public Transaction execute(TransactionRequestDTO transactionRequestDTO) {
		
		var user = this.userRepository.findById(transactionRequestDTO.user())
				.orElseThrow(() -> new IllegalArgumentException("User must be informed."));
		var sender = this.programRepository.findById(transactionRequestDTO.programSender())
				.orElseThrow(() -> new IllegalArgumentException("Program sender must be informed."));
		
		this.programService.validateTransaction(sender.getBalanceAvailable(), transactionRequestDTO.amount());

		var milesList = milesRepository.findMilesBasedOnCriteria(sender.getId(), LocalDate.now());
		
		int soma = 0;
		int amount = transactionRequestDTO.amount();
		List<Miles> milesAdded = new ArrayList<>();
		for (Miles miles : milesList) {
			if(soma < amount) {
				soma += miles.getAmount();
				milesAdded.add(miles);
			}
		}
		for (Miles miles : milesAdded) {
			if(amount > miles.getAmount()) {
				int result = miles.getAmount() - amount;
				amount -= miles.getAmount();
				miles.setAmount(result < 0? 0 : result);
			}
			else {
				miles.setAmount(miles.getAmount() - amount);
			}
		}
		
		
		//In a sell the amount is deducted from the available balance, but still present in total balance. It'll be deducted from total balance when a ticket is generated
 		sender.setBalanceAvailable(sender.getBalanceAvailable() - transactionRequestDTO.amount());
		
		Transaction transaction = new Transaction(transactionRequestDTO);
		transaction.setProgramSender(sender);
		transaction.setUser(user);
		
		
		return transaction;
	}

}
