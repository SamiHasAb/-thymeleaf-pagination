package org.study.pagination.repositories;

import org.springframework.stereotype.Repository;
import org.study.pagination.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface  EmployeeRepository extends JpaRepository<Employee , Long>{

}
