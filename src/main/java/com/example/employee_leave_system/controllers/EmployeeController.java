package com.example.employee_leave_system.controllers;

import com.example.employee_leave_system.dtos.EmployeeResponseDTO;
import com.example.employee_leave_system.dtos.EmployeeRequestDto;
import com.example.employee_leave_system.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private com.example.employee_leave_system.services.EmployeeService employeeService;

    //ADMIN APIs:
    // Add Employee
    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> addEmployee(@RequestBody EmployeeRequestDto employeeRequestDto) {
        EmployeeResponseDTO savedEmployee = employeeService.addEmployee(employeeRequestDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // List Employees
    @GetMapping
    public List<EmployeeResponseDTO> listEmployees() {
        return employeeService.listEmployees();
    }

    // Delete Employee
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee removed successfully.", HttpStatus.OK);
    }
}
