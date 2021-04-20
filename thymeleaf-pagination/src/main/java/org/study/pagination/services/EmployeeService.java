package org.study.pagination.services;

import java.util.List;

import org.study.pagination.models.Employee;

import org.springframework.data.domain.Page;


public interface EmployeeService {
	
	
	List<Employee> getAllEmp();
	void saveEmployee(Employee emp);
	Employee getEmployeeById(Long id);
	void deleteEmpById(Long id);
	Page<Employee> findPangination(int pageNo, int pageSize, String sortFiled, String sortDirection);
}