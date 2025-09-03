package com.example.employee_leave_system.services;

import com.example.employee_leave_system.dtos.EmployeeResponseDTO;
import com.example.employee_leave_system.dtos.EmployeeRequestDto;
import com.example.employee_leave_system.entities.Employee;
import com.example.employee_leave_system.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Add employee
    public EmployeeResponseDTO addEmployee(EmployeeRequestDto employeeRequestDto) {
        //dto to entity
        Employee employee = new Employee();
        employee.setName(employeeRequestDto.getName());
        employee.setEmail(employeeRequestDto.getEmail());
        employee.setRole(employeeRequestDto.getRole());

        Employee savedEmployee = employeeRepository.save(employee);

        //entity to dto
        EmployeeResponseDTO employeeResponseDto = new EmployeeResponseDTO();
        employeeResponseDto.setId(savedEmployee.getId());
        employeeResponseDto.setName(savedEmployee.getName());
        employeeResponseDto.setEmail(savedEmployee.getEmail());
        employeeResponseDto.setRole(savedEmployee.getRole());

        return employeeResponseDto;
    }

    // List employees
    public List<EmployeeResponseDTO> listEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        //entity to dto
        List<EmployeeResponseDTO> employeeResponseDtos = new ArrayList<>();

        for (Employee employee : employees) {
            EmployeeResponseDTO employeeResponseDto = new EmployeeResponseDTO();
            employeeResponseDto.setId(employee.getId());
            employeeResponseDto.setName(employee.getName());
            employeeResponseDto.setEmail(employee.getEmail());
            employeeResponseDto.setRole(employee.getRole());
            employeeResponseDtos.add(employeeResponseDto);
        }

        return employeeResponseDtos;
    }

    // Delete employee
    public String deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        return "Employee removed successfully.";
    }
}
