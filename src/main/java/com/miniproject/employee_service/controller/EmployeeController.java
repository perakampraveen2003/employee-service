package com.miniproject.employee_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject.employee_service.model.Employee;
import com.miniproject.employee_service.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	@PostMapping("/create")
	private ResponseEntity<Object> createEmployee(@Valid @RequestBody Employee emp) {
		Employee employee = empService.createEmployee(emp);
		return new ResponseEntity<>(employee, HttpStatus.CREATED);
	}

	@PostMapping("/publish/{id}")
	private ResponseEntity<String> publishEmployee(@PathVariable("id") long empId) {
		String msg = empService.publishEmployee(empId);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
}
