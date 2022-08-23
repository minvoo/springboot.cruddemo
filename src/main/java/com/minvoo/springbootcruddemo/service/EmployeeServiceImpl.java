package com.minvoo.springbootcruddemo.service;

import com.minvoo.springbootcruddemo.entity.Employee;
import com.minvoo.springbootcruddemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository repo;

    @Autowired
    public EmployeeServiceImpl(@Qualifier("jpa_repo") EmployeeRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional // handles transaction managemenet. we don't have to manually start and commit transaction
    public List<Employee> findAll() {
        return repo.findAll();

    }

    @Override
    @Transactional // handles transaction managemenet. we don't have to manually start and commit transaction
    public Employee findById(int id) {
        Employee employee = repo.findById(id);
        if (employee == null) {
            throw new RuntimeException("Employee id not found - " + id);
        }
        return employee;
    }

    @Override
    @Transactional // handles transaction managemenet. we don't have to manually start and commit transaction
    public void save(Employee employee) {
        repo.save(employee);
    }

    @Override
    @Transactional // handles transaction managemenet. we don't have to manually start and commit transaction
    public void deleteById(int id) {
        repo.deleteById(id);
    }
}
