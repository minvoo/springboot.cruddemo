package com.minvoo.springbootcruddemo.repository;

import com.minvoo.springbootcruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDataJPARepo extends JpaRepository<Employee, Integer> {
}
