package com.beauty.api.service;

import com.beauty.api.collection.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    String save(Employee employee);

    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeeById(String employeeId);

    void delete(String employeeId);

    Employee update(Employee employee, String employeeId);
}
