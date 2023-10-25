package com.netmaxi.mm.api.transactions.strategy;

import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.error.TransactionBusinessException;
import com.netmaxi.mm.api.transactions.TransactionType;

@Service
public class TransactionStrategyFactory {
	
	private TransferenceTransactionStrategy transferenceTransactionStrategy;
	private SellTransactionStrategy sellTransactionStrategy;
	private PurchaseTransactionStrategy purchaseTransactionStrategy;
	private UseTransactionStrategy useTransactionStrategy;
	private DevolutionTransactionStrategy devolutionTransactionStrategy;
	private SubscriptionTransactionStrategy subscriptionTransactionStrategy;
	private BonificationTransactionStrategy bonificationTransactionStrategy;
	
	public TransactionStrategyFactory(TransferenceTransactionStrategy transferenceTransactionStrategy,
			SellTransactionStrategy sellTransactionStrategy, PurchaseTransactionStrategy purchaseTransactionStrategy,
			UseTransactionStrategy useTransactionStrategy, DevolutionTransactionStrategy devolutionTransactionStrategy,
			SubscriptionTransactionStrategy subscriptionTransactionStrategy,
			BonificationTransactionStrategy bonificationTransactionStrategy) {
		this.transferenceTransactionStrategy = transferenceTransactionStrategy;
		this.sellTransactionStrategy = sellTransactionStrategy;
		this.purchaseTransactionStrategy = purchaseTransactionStrategy;
		this.useTransactionStrategy = useTransactionStrategy;
		this.devolutionTransactionStrategy = devolutionTransactionStrategy;
		this.subscriptionTransactionStrategy = subscriptionTransactionStrategy;
		this.bonificationTransactionStrategy = bonificationTransactionStrategy;
	}

	public TransactionStrategy getStrategy(TransactionType type) {
		return switch (type) {
			case TRANSFERENCE -> this.transferenceTransactionStrategy;
			case SELL -> this.sellTransactionStrategy;
			case PURCHASE -> this.purchaseTransactionStrategy;
			case USE -> this.useTransactionStrategy;
			case DEVOLUTION -> this.devolutionTransactionStrategy;
			case SUBSCRIPTION -> this.subscriptionTransactionStrategy;
			case BONIFICATION -> this.bonificationTransactionStrategy;
			default -> throw new TransactionBusinessException("Transaction type not found");
		};
		
       
    }

}
