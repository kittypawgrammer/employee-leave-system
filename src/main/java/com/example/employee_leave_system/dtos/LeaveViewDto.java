package com.example.employee_leave_system.dtos;

import lombok.Data;

@Data
public class ManagerLeaveViewDto {
    private Long id;
    private String employeeName;
    private LeaveType leaveType;
    private String startDate;
    private String endDate;
    private LeaveStatus status;
}
