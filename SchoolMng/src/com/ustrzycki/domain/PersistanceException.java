package com.ustrzycki.domain;

public class PersistanceException extends Exception {
	
	public PersistanceException(String message, Throwable exception){
		super(message, exception );
	} 

}
