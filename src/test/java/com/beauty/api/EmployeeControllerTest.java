package com.beauty.api;

import com.beauty.api.collection.Employee;
import com.beauty.api.controller.EmployeeController;
import com.beauty.api.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllEmployees() {
        Employee employee1 = Employee.builder()._id("65915d83a9d7434601cd28b2").name("John Doe").build();
        Employee employee2 = Employee.builder()._id("65c1146883e5c150c1480e6a").name("Jane Doe").build();
        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeService.getAllEmployees()).thenReturn(employees);

        ResponseEntity<List<Employee>> response = employeeController.getAllEmployees();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employees, response.getBody());
    }

    @Test
    void testGetEmployeeById() {
        String employeeId = "65915d83a9d7434601cd28b2";
        Employee employee = Employee.builder()._id(employeeId).name("John Doe").build();

        when(employeeService.getEmployeeById(employeeId)).thenReturn(Optional.of(employee));

        ResponseEntity<Employee> response = employeeController.getEmployeeById(employeeId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employee, response.getBody());
    }

}
