package com.beauty.api.service;

import com.beauty.api.collection.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee save(Employee employee);

    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeeById(String employeeId);

    boolean delete(String employeeId);

    Optional<Employee> update(Employee employee, String employeeId);

    Optional<Employee> getEmployeeByEmail(String email);
}
