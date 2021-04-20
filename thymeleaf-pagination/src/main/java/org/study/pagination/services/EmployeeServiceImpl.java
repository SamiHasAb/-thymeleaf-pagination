package org.study.pagination.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.study.pagination.models.Employee;
import org.study.pagination.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public List<Employee> getAllEmp() {
		return (List<Employee>) empRepo.findAll();
	}

	@Override
	public void saveEmployee(Employee emp) {
		this.empRepo.save(emp);

	}

	@Override
	public Employee getEmployeeById(Long id) {
		Optional<Employee> optional = empRepo.findById(id);
		Employee employee = null;

		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException(" Empolyee not found for id : " + id);
		}

		return employee;
	}

	@Override
	public void deleteEmpById(Long id) {
		this.empRepo.deleteById(id);
	}

	@Override
	public Page<Employee> findPangination(int pageNo, int pageSize, String sortFiled, String sortDirection) {
		//check the given sort direction
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortFiled).ascending()
				: Sort.by(sortFiled).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

		return this.empRepo.findAll(pageable);

	}

}
