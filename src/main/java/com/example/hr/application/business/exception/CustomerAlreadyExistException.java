package com.example.hr.application.business.exception;

@SuppressWarnings("serial")
public class CustomerAlreadyExistException extends RuntimeException {

	public CustomerAlreadyExistException() {
		super("Customer already exists.");
	}

}
