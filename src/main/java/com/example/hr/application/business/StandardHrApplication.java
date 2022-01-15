package com.example.hr.application.business;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.exception.CustomerAlreadyExistException;
import com.example.hr.application.business.exception.CustomerNotFoundException;
import com.example.hr.application.event.EmployeeAddEvent;
import com.example.hr.application.event.EmployeeDeleteEvent;
import com.example.hr.document.EmployeeDocument;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.request.UpdateEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.FireEmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.dto.response.UpdateEmployeeResponse;
import com.example.hr.repository.EmployeeMongoRepository;
@Service
public class StandardHrApplication implements HrApplication {
	private EmployeeMongoRepository employeeMongoRepository;
	private ModelMapper modelMapper;
	private ApplicationEventPublisher eventPublisher;
	public StandardHrApplication(EmployeeMongoRepository employeeMongoRepository,ModelMapper modelMapper, ApplicationEventPublisher eventPublisher) {
		this.employeeMongoRepository = employeeMongoRepository;
		this.modelMapper=modelMapper;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public HireEmployeeResponse hireEmployee(HireEmployeeRequest request) {
		var identity = request.getIdentity();
		if(employeeMongoRepository.existsById(identity))
			throw new CustomerAlreadyExistException();
		var employee = modelMapper.map(request, EmployeeDocument.class);
		var employeeAddEvent = new EmployeeAddEvent(UUID.randomUUID().toString(), identity);
		employee = employeeMongoRepository.save(employee);
			eventPublisher.publishEvent(employeeAddEvent);
		return modelMapper.map(employee, HireEmployeeResponse.class);
	}

	@Override
	public UpdateEmployeeResponse updateEmployee(String identity, UpdateEmployeeRequest request) {
		var managedCustomer = employeeMongoRepository.findById(identity)
							.orElseThrow(() -> new CustomerNotFoundException());
			managedCustomer.setFullname(request.getFullname());
			var updateEmployeeResponse = modelMapper.map(managedCustomer, UpdateEmployeeResponse.class);
			updateEmployeeResponse.setPhoto((managedCustomer.getPhoto()));
		return updateEmployeeResponse;
	}

	@Override
	public FireEmployeeResponse fireEmployee(String identity) {
		var employee = employeeMongoRepository.findById(identity).orElseThrow( () -> new CustomerNotFoundException());
		
		var employeeDeletedEvent = new EmployeeDeleteEvent(employee);
		eventPublisher.publishEvent(employeeDeletedEvent);
		employeeMongoRepository.delete(employee);
		
	    return modelMapper.map(employee,FireEmployeeResponse.class);
	}

	@Override
	public List<EmployeeResponse> getEmployees(@Min(0) int page, @Max(25) int size) {
			
		return employeeMongoRepository.findAll(PageRequest.of(page, size)).getContent().stream()
				.map(emp -> modelMapper.map(emp, EmployeeResponse.class)).toList();
	
	}

	@Override
	public EmployeeResponse findEmployeeByIdentity(String identity) {
		var employee = employeeMongoRepository.findById(identity).orElseThrow(
				() -> new CustomerNotFoundException());
		var detailedEmployeeResponse = modelMapper.map(employee, EmployeeResponse.class);
		return detailedEmployeeResponse;
	}

}
