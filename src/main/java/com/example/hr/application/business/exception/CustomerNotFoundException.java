package com.example.hr.application.business.exception;

@SuppressWarnings("serial")
public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException() {
		super("Cannot find the customer");
	}



}
