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

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class TransferenceTransactionStrategy implements TransactionStrategy {

	private final ProgramRepository programRepository;
	private final UserRepository userRepository;
	private final MilesRepository milesRepository;
	private final ProgramService programService;
	
	public TransferenceTransactionStrategy(ProgramRepository programRepository, UserRepository userRepository,
			MilesRepository milesRepository, ProgramService programService) {
		super();
		this.programRepository = programRepository;
		this.userRepository = userRepository;
		this.milesRepository = milesRepository;
		this.programService = programService;
	}
	
	@Override
	public Transaction execute(TransactionRequestDTO transactionRequestDTO) {
		
		log.info("TRANSACTION TRANSFERENCE:::START");
		var user = this.userRepository.findById(transactionRequestDTO.user())
				.orElseThrow(() -> new IllegalArgumentException("User must be informed."));
		log.info("TRANSACTION TRANSFERENCE:::USER FOUND: {}", user);
		var sender = this.programRepository.findById(transactionRequestDTO.programSender())
				.orElseThrow(() -> new IllegalArgumentException("Program sender must be informed."));
		log.info("TRANSACTION TRANSFERENCE::: PROGRAM SENDER FOUND: {}", sender);
		var receiver = this.programRepository.findById(transactionRequestDTO.programReceiver())
				.orElseThrow(() -> new IllegalArgumentException("Program Receiver must be informed."));
		log.info("TRANSACTION TRANSFERENCE::: PROGRAM RECEIVER FOUND: {}", receiver);
		
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
		log.info("TRANSACTION TRANSFERENCE:::MILES ADDED LIST SIZE: {}", milesAdded.size());
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
		
		log.info("TRANSACTION TRANSFERENCE:::SUBTRACTING AMOUNT FROM SENDER");
		sender.setBalance(sender.getBalance() - transactionRequestDTO.amount());
		sender.setBalanceAvailable(sender.getBalanceAvailable() - transactionRequestDTO.amount());
		log.info("TRANSACTION TRANSFERENCE:::ADDING AMOUNT TO RECEIVER");
		receiver.setBalance(receiver.getBalance() + transactionRequestDTO.amount());
		receiver.setBalanceAvailable(receiver.getBalanceAvailable() + transactionRequestDTO.amount());
						
		log.info("TRANSACTION TRANSFERENCE:::CREATING TRANSACTION AND SETTING PROGRAMS AND MILES");
		Transaction transaction = new Transaction(transactionRequestDTO);
		transaction.setProgramSender(sender);
		receiver.getMiles().add(milesRepository.save(new Miles(transactionRequestDTO.amount(), transactionRequestDTO.price(), transactionRequestDTO.expiration(), receiver)));
		programRepository.save(receiver);
		transaction.setProgramReceiver(receiver);
		transaction.setUser(user);
		
		log.info("TRANSACTION TRANSFERENCE:::END:::TRANSACTION: {}", transaction);
		return transaction;
	}

}
