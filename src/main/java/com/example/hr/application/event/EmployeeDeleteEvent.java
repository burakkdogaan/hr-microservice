package com.example.hr.application.event;

import com.example.hr.document.EmployeeDocument;

public class EmployeeDeleteEvent {
	
	private EmployeeDocument employee;

	public EmployeeDeleteEvent(EmployeeDocument employee) {
		this.employee = employee;
	}

	public EmployeeDocument getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDocument employee) {
		this.employee = employee;
	}
	
}
