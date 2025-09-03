package com.example.employee_leave_system.dtos;

import lombok.Data;

@Data
public class LeaveResponseDto {
    private Long id;
    private Long employeeId;
    private LeaveType leaveType;
    private String startDate;
    private String endDate;
    private String reason;
    private LeaveStatus status;
}
