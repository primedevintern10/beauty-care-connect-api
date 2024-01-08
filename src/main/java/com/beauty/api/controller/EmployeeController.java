package com.beauty.api.controller;

import com.beauty.api.collection.Employee;
import com.beauty.api.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
@Tag(name = "Employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Create an Employee")
    @PostMapping
    public String save(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @Operation(summary = "Get All Employees")
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @Operation(summary = "Get Employee by ID")
    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable("id") String employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @Operation(summary = "Update an Employee")
    @PutMapping("/{id}")
    public Employee update(@PathVariable("id") String employeeId, @RequestBody Employee employee) {
        return employeeService.update(employee, employeeId);
    }

    @Operation(summary = "Remove an Employee")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String employeeId) {
        employeeService.delete(employeeId);
        return "Deleted Successfully";
    }
}
