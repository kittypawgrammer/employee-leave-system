package main.java.com.example.employee_leave_system.dtos;

import com.example.employee_leave_system.entities.Role;
import lombok.Data;

@Data
public class EmployeeResponseDTO {

    private Long id;
    private String name;
    private String email;
    private Role role;

}


