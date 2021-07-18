package com.example.demo.controller;

import com.example.demo.dto.EmployeeReqDTO;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class EmployeeController<employeeService> {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        Employee employee = employeeService.findById(employeeId);
        return employee;
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody EmployeeReqDTO employeeReqDTO) {
        return employeeService.saveEmployee(employeeReqDTO);
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable(value = "id") Long employeeId, @RequestBody EmployeeReqDTO employeeDetails) {
        return employeeService.updateEmployee(employeeId, employeeDetails);
    }

    @DeleteMapping("/employees/{id}")
    public Employee deleteEmployee(@PathVariable(value = "id") Long employeeId) {
        return employeeService.deleteEmployee(employeeId);
    }
}
