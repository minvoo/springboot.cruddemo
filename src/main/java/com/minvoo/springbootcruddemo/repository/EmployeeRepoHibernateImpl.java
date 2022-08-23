package com.minvoo.springbootcruddemo.repository;

import com.minvoo.springbootcruddemo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Qualifier("hibernate_repo")
public class EmployeeRepoHibernateImpl implements EmployeeRepository {

    private EntityManager entityManager;

    @Autowired
    public EmployeeRepoHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Employee> query = currentSession.createQuery("from Employee", Employee.class);

        List<Employee> employees = query.getResultList();

        return employees;
    }

    @Override
    public Employee findById(int id) {

        Session session = entityManager.unwrap(Session.class);
        Employee employee = session.get(Employee.class, id);
        return employee;
    }

    @Override
    public void save(Employee employee) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(employee);

    }

    @Override
    public void deleteById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Employee employee = session.get(Employee.class, id);
        session.delete(employee);

    /* second way:
    Session session = entityManager.unwrap(Session.class);
    Query query = session.createQuery(
                    "delete from Employee where id=:employeeId");
    query.setParameter("employeeId", id);
    query.executeUpdate();
     */
    }
}
