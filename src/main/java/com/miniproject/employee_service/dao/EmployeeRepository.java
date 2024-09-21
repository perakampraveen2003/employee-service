package com.miniproject.employee_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miniproject.employee_service.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
