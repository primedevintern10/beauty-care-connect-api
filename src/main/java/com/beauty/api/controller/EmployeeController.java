package com.beauty.api.controller;

import com.beauty.api.collection.Employee;
import com.beauty.api.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Employee> save(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @Operation(summary = "Get All Employees")
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "Get Employee by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") String employeeId) {
        Optional<Employee> employee = employeeService.getEmployeeById(employeeId);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get Employee by Email")
    @GetMapping("/by-email/{email}")
    public ResponseEntity<Employee> getEmployeeByEmail(@PathVariable("email") String email) {
        Optional<Employee> employee = employeeService.getEmployeeByEmail(email);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an Employee")
    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable("id") String employeeId, @RequestBody Employee employee) {
        Optional<Employee> updatedEmployee = employeeService.update(employee, employeeId);
        return updatedEmployee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Remove an Employee")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String employeeId) {
        boolean isDeleted = employeeService.delete(employeeId);
        return isDeleted ? ResponseEntity.ok("Employee deleted successfully") : ResponseEntity.notFound().build();
    }
}
