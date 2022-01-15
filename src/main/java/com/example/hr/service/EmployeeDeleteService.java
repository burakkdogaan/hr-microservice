package com.example.hr.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.example.hr.application.event.EmployeeDeleteEvent;
@Service
public class EmployeeDeleteService {
	
	
	@EventListener
	public void handleEmployeeDeleteEvent(EmployeeDeleteEvent event) {
		var fullName = event.getEmployee().getFullname();
		System.err.println("Dear" + fullName
				+ "sorry for leaving your job");
	}

}
