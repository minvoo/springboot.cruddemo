package com.minvoo.springbootcruddemo.repository;

import com.minvoo.springbootcruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Qualifier("jpa_repo")
public class EmployeeRepoJPAImpl implements EmployeeRepository {

    private EntityManager entityManager;

    @Autowired
    public EmployeeRepoJPAImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);
        List<Employee> employees = query.getResultList();
        return employees;
    }

    @Override
    public Employee findById(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        if (employee == null) {
            throw new RuntimeException("Employee not found");
        }
        return employee;
    }

    @Override
    public void save(Employee employee) {
        Employee dbEmployee = entityManager.merge(employee);
        employee.setId(dbEmployee.getId());
    }

    @Override
    public void deleteById(int theId) {
        Query query = entityManager.createQuery(
                "delete from Employee where id=:empId"
        );
        query.setParameter("empId", theId);
        query.executeUpdate();
    }
}
