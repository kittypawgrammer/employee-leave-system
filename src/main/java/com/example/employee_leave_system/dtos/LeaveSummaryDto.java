package com.example.employee_leave_system.dtos;

import lombok.Data;

@Data
public class LeaveSummaryDto {
    private Long id;
    private String leaveType;
    private String startDate;
    private String endDate;
    private LeaveStatus status;
}
