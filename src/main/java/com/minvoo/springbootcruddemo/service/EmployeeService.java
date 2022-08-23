package com.minvoo.springbootcruddemo.service;

import com.minvoo.springbootcruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> findAll();

    public Employee findById(int id);

    public void save(Employee employee);

    public void deleteById(int id);

}
