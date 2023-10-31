package com.netmaxi.mm.api.error;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = -7343183767903701051L;

	public ValidationException(String msg){
        super(msg);
    }

    public ValidationException(String msg, Throwable cause){
        super(msg, cause);
    }

	
}
