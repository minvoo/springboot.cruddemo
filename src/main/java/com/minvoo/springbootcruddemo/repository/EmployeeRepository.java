package com.minvoo.springbootcruddemo.repository;

import com.minvoo.springbootcruddemo.entity.Employee;

import java.util.List;

public interface EmployeeRepository {
    //NOTE: Repository == now   <=== DAO in the past

    public List<Employee> findAll();

    public Employee findById(int id);

    public void save(Employee employee);

    public void deleteById(int id);
}
