package com.netmaxi.mm.api.transactions.strategy;

import com.netmaxi.mm.api.transactions.Transaction;
import com.netmaxi.mm.api.transactions.dto.TransactionRequestDTO;

public interface TransactionStrategy {
	
	Transaction execute(TransactionRequestDTO transactionRequestDTO);

}
