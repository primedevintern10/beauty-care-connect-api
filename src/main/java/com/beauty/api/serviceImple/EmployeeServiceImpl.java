package com.beauty.api.serviceImple;

import com.beauty.api.collection.Employee;
import com.beauty.api.repository.EmployeeRepository;
import com.beauty.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public String save(Employee employee) {
        return employeeRepository.save(employee).getName(); // Modify as needed
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(String employeeId) {
        return employeeRepository.findById(employeeId);
    }

    @Override
    public void delete(String employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public Employee update(Employee employee, String employeeId) {
        Employee existingEmployeeData = employeeRepository.findById(employeeId).orElse(null);

        if (existingEmployeeData != null) {
            existingEmployeeData.setName(employee.getName());
            existingEmployeeData.setNickName(employee.getNickName());
            existingEmployeeData.setEmail(employee.getEmail());
            existingEmployeeData.setContactNo(employee.getContactNo());
            existingEmployeeData.setIsEnabled(employee.getIsEnabled());
            existingEmployeeData.setType(employee.getType());

            return employeeRepository.save(existingEmployeeData);
        } else {
            return null;
        }
    }
}
