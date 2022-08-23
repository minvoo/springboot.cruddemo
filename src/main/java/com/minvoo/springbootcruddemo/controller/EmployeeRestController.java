package com.minvoo.springbootcruddemo.controller;

import com.minvoo.springbootcruddemo.entity.Employee;
import com.minvoo.springbootcruddemo.repository.EmployeeRepository;
import com.minvoo.springbootcruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {


    private EmployeeService service;

    @Autowired
    public EmployeeRestController(@Qualifier("employeeServiceDataJPAImpl") EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return service.findAll();
    }

    @GetMapping("employees/{id}")
    public Employee findById(@PathVariable("id") int id) {
        return service.findById(id);
    }

    @PostMapping("/employees")
    public Employee save(@RequestBody Employee employee) {
        employee.setId(0); // just in case if someone pass id parameter in JSON body..
        service.save(employee);
        return employee;

    }

    @PutMapping("/employees")
    public Employee update(@RequestBody Employee employee) {
        service.save(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String delete(@PathVariable("id") int id) {
        Employee employee = service.findById(id);
        if (employee == null) {
            throw new RuntimeException("Employee not found");
        }
        service.deleteById(id);
        return "Successfully deleted employee with given id: " + id;
    }

}
