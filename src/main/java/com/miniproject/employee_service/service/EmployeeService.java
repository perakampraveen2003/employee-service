package com.miniproject.employee_service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.employee_service.dao.EmployeeRepository;
import com.miniproject.employee_service.model.Employee;

import jakarta.validation.Valid;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRespository;

	@Autowired
	private PublishService publishService;

	public Employee createEmployee(@Valid Employee emp) {
		return empRespository.save(emp);
	}

	public String publishEmployee(long empId) {
		Optional<Employee> empOptional = empRespository.findById(empId);
		if (empOptional.isPresent()) {
			publishService.publishToSns(empOptional.get());
			return "Published Successfully";
		}
		return "No employee details found with id :" + empId;
	}

}
