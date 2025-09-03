package com.example.employee_leave_system.dtos;

import lombok.Data;

@Data
public class UpdateLeaveStatusResponseDto {
    private Long id;
    private String status;
    private String managerRemarks;
}
