package com.example.employee_leave_system.dtos;


import com.example.employee_leave_system.entities.Role;
import lombok.Data;

@Data
public class EmployeeRequestDto {

    private String name;
    private String email;
    private Role role;

}


