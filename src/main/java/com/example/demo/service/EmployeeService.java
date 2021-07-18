package com.example.demo.service;

import com.example.demo.dto.EmployeeReqDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.jpa.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService extends Throwable {
    @Autowired
    private EmployeeRepository employeeRepository;
    private Object Employee;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(EmployeeReqDTO employeeReqDTO) {
        Employee employee = new Employee();
        employee.setFirstName(employeeReqDTO.getFirstName());
        employee.setLastName(employeeReqDTO.getLastName());
        employee.setYearlyIncome(employeeReqDTO.getYearlyIncome());

        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(long id, EmployeeReqDTO employeeReqDTO) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found with id: " + id)
        );
        employee.setFirstName(employeeReqDTO.getFirstName());
        employee.setLastName(employeeReqDTO.getLastName());
        employee.setYearlyIncome(employeeReqDTO.getYearlyIncome());

        employeeRepository.save(employee);

        return employee;
    }
    public Employee findById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found with id: " + employeeId)
        );
        return employee;
    }
    public Employee deleteEmployee(long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found with id: " + id)
        );
            employeeRepository.delete(employee);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
        return employee;
    }


    public HttpResponse<Employee> ok(Employee updatedEmployee) {
        return null;
    }
}
