package com.netmaxi.mm.api.error;

public class EntityAlreadyCreatedException extends RuntimeException{

	 private static final long serialVersionUID = 4233983967471665071L;

	public EntityAlreadyCreatedException(String msg){
	        super(msg);
	    }

	    public EntityAlreadyCreatedException(String msg, Throwable cause){
	        super(msg, cause);
	    }
	
}
