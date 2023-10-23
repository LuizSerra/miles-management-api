package com.netmaxi.mm.api.error;

public class TransactionBusinessException extends RuntimeException {

	private static final long serialVersionUID = -7343183767903701051L;

	public TransactionBusinessException(String msg){
        super(msg);
    }

    public TransactionBusinessException(String msg, Throwable cause){
        super(msg, cause);
    }

	
}
