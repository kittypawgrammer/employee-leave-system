package com.example.employee_leave_system.dtos;

import lombok.Data;

@Data
public class LeaveRequestDto {
    private LeaveType leaveType;
    private String startDate;
    private String endDate;
    private String reason;
}