package com.beauty.api.controller;

import com.beauty.api.collection.Employee;
import com.beauty.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public String save(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable("id") String employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable("id") String employeeId, @RequestBody Employee employee) {
        return employeeService.update(employee, employeeId);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String employeeId) {
        employeeService.delete(employeeId);
        return "Deleted Successfully";
    }
}
