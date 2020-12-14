package com.app.module.master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Employee;

/**
 * @author Piyush.Thakre
 *
 */
public interface IEmployeeDao extends JpaRepository<Employee, Long> {

	@Query("select employee from Employee employee where active=true")
	List<Employee> getAllActiveEmployees();
	
	@Query("select employee from Employee employee where employee.employeeId=?1 ")
	List<Employee> getEmployeeByEmployeeId(Long employeeId);
}

