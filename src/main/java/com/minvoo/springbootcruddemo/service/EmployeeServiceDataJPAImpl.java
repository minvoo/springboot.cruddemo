package com.minvoo.springbootcruddemo.service;

import com.minvoo.springbootcruddemo.entity.Employee;
import com.minvoo.springbootcruddemo.repository.EmployeeDataJPARepo;
import com.minvoo.springbootcruddemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceDataJPAImpl implements EmployeeService {

    private EmployeeDataJPARepo repo;

    @Autowired
    public EmployeeServiceDataJPAImpl(EmployeeDataJPARepo repo) {
        this.repo = repo;
    }

    @Override
    // no need to add @Transactional because JpaRepository provides this functionality out of the box.
    public List<Employee> findAll() {
        return repo.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> result = repo.findById(id);
        Employee emp = null;
        if (result.isPresent()) {
            emp = result.get();
        }
        return emp;
    }

    @Override
    public void save(Employee employee) {
        repo.saveAndFlush(employee);
    }

    @Override
    public void deleteById(int id) {
        repo.deleteById(id);
    }
}
