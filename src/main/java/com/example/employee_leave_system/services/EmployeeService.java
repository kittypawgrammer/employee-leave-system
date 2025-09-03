package main.java.com.example.employee_leave_system.services;

import com.example.employee_leave_system.dtos.EmployeeRequestDto;
import com.example.employee_leave_system.dtos.EmployeeResponseDTO;
import com.example.employee_leave_system.entities.Employee;
import com.example.employee_leave_system.respositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Add employee
    public EmployeeResponseDTO addEmployee(EmployeeRequestDto employeeRequestDto) {
        Employee employee = new Employee();
        employee.setName(employeeRequestDto.getName());
        employee.setEmail(employeeRequestDto.getEmail());
        employee.setRole(employeeRequestDto.getRole());

        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
        employeeResponseDTO.setId(savedEmployee.getId());
        employeeResponseDTO.setName(savedEmployee.getName());
        employeeResponseDTO.setEmail(savedEmployee.getEmail());
        employeeResponseDTO.setRole(savedEmployee.getRole());

        return employeeResponseDTO;
    }

    // List employees
    public List<EmployeeResponseDTO> listEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponseDTO> employeeResponseDTOs = new java.util.ArrayList<>();

        for (Employee employee : employees) {
            EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
            employeeResponseDTO.setId(employee.getId());
            employeeResponseDTO.setName(employee.getName());
            employeeResponseDTO.setEmail(employee.getEmail());
            employeeResponseDTO.setRole(employee.getRole());
            employeeResponseDTOs.add(employeeResponseDTO);
        }

        return employeeResponseDTOs;
    }

    // Delete employee
    public String deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        return "Employee removed successfully.";
    }
}
