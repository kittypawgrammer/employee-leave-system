package com.example.employee_leave_system.services;

import com.example.employee_leave_system.dtos.EmployeeResponseDto;
import com.example.employee_leave_system.dtos.EmployeeRequestDto;
import com.example.employee_leave_system.entities.Employee;
import com.example.employee_leave_system.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.example.employee_leave_system.dtos.EmployeeRequestDto;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Add employee
    public EmployeeResponseDto addEmployee(EmployeeRequestDto EmployeeRequestDto) {
        //dto to entity
        Employee employee = new Employee();
        employee.setName(EmployeeRequestDto.getName());
        employee.setEmail(EmployeeRequestDto.getEmail());
        employee.setRole(EmployeeRequestDto.getRole());

        Employee savedEmployee = employeeRepository.save(employee);

        //entity to dto
        EmployeeResponseDto EmployeeResponseDto = new EmployeeResponseDto();
        EmployeeResponseDto.setId(savedEmployee.getId());
        EmployeeResponseDto.setName(savedEmployee.getName());
        EmployeeResponseDto.setEmail(savedEmployee.getEmail());
        EmployeeResponseDto.setRole(savedEmployee.getRole());

        return EmployeeResponseDto;
    }

    // List employees
    public List<EmployeeResponseDto> listEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        //entity to dto
        List<EmployeeResponseDto> EmployeeResponseDtos = new ArrayList<>();

        for (Employee employee : employees) {
            EmployeeResponseDto EmployeeResponseDto = new EmployeeResponseDto();
            EmployeeResponseDto.setId(employee.getId());
            EmployeeResponseDto.setName(employee.getName());
            EmployeeResponseDto.setEmail(employee.getEmail());
            EmployeeResponseDto.setRole(employee.getRole());
            EmployeeResponseDtos.add(EmployeeResponseDto);
        }

        return EmployeeResponseDtos;
    }

    // Delete employee
    public String deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        return "Employee removed successfully.";
    }
}
