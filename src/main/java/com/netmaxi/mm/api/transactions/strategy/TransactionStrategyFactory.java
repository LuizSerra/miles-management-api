package com.netmaxi.mm.api.transactions.strategy;

import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.transactions.TransactionType;

@Service
public class TransactionStrategyFactory {
	
	private TransferenceTransactionStrategy transferenceTransactionStrategy;
	
	public TransactionStrategyFactory(TransferenceTransactionStrategy transferenceTransactionStrategy) {
		this.transferenceTransactionStrategy = transferenceTransactionStrategy;
	}



	public TransactionStrategy getStrategy(TransactionType type) {
        if (type.equals(TransactionType.TRANSFERENCE)) {
            return this.transferenceTransactionStrategy;
        } 
   
        return null; // Estratégia não suportada
    }

}
