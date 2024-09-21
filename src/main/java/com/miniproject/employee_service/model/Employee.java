package com.miniproject.employee_service.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long empid;
	@NotNull(message = "employee name can not be null")
	@Column(name = "name")
	private String empName;
	@Min(value = 0, message = "salary can not be negative")
	@Column(name = "salary")
	private double empSalary;
	@PastOrPresent(message = "employee joining date can not be future")
	@Column(name = "joiningDate")
	private Date empJoiningDate;
	@Email(message = "email is in incorrect format")
	@Column(name = "emailId")
	private String empEmailId;
}
