package com.example.hr.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.example.hr.application.HrApplication;
import com.example.hr.application.event.EmployeeAddEvent;

@Service
public class EmployeeAddService {
	private HrApplication hrApplication;

	public EmployeeAddService(HrApplication hrApplication) {
		this.hrApplication = hrApplication;
	}
	
	@EventListener
	public void handleEmployeeAddEvent(EmployeeAddEvent event) {
		var identity = event.getIdentity();
		var employee = hrApplication.findEmployeeByIdentity(identity);
		var fullName = employee.getFullname();
		System.err.println("Dear" + fullName + "Welcome the Company..." );
	}
}
