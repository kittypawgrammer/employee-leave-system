package com.example.employee_leave_system.entities;

import java.lang.annotation.Inherited;

@Entity
public class leaveRequest {
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @Column(name = "employee_id")
    private Employee employee;

    @Enumerated(EnumType.String)
    @Column(name = "leave_type")
    private LeaveType leaveType;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Enumerated(EnumType.String)
    @Column(name = "status")
    private LeaveStatus status;

    @Column(name = "manager_remarks")
    private String managerRemarks;

}

enum LeaveStatus {
    PENDING,
    APPROVED,
    REJECTED
}
enum LeaveType {
    SICK,
    VACATION,
    UNPAID
}