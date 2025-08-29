package main.java.com.example.employee_leave_system.controllers;

import com.example.employee_leave_system.dtos.LeaveRequestDto;
import com.example.employee_leave_system.dtos.LeaveResponseDto;
import com.example.employee_leave_system.services.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/leaves")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    // EMPLOYEE APIs
    // Employee apply for leave
    @PostMapping
    public ResponseEntity<List<LeaveResponseDto>> applyLeave(@RequestBody LeaveRequestDto leave) {
        List<LeaveResponseDto> leaveResponse = leaveService.applyLeave(leave);
        return new ResponseEntity<>(leaveResponse, HttpStatus.CREATED);
    }

    // View My Leave Requests
    @GetMapping("/my")
    public List<LeaveResponseDto> viewMyLeaves(@PathVariable Long employeeId) {
        return leaveService.viewMyLeaveRequests(employeeId);
    }

    // Cancel Leave Request
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDto> cancelLeave(@PathVariable Long id) {
        String message = leaveService.cancelLeaveRequest(id);
        MessageResponseDto response = new MessageResponseDto();
        response.setMessage(message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // View All Leave Requests (Team)
    @GetMapping
    public List<LeaveResponseDto> viewAllLeaveRequests(@RequestParam(required = false) String status) {
        return leaveService.viewAllLeaveRequests(status);
    }

    // Approve Leave Request
    @PutMapping("/{id}/approve")
    public LeaveResponseDto approveLeaveRequest(@PathVariable Long id) {
        return leaveService.approveLeaveRequest(id);
    }

    // Reject Leave Request
    @PutMapping("/{id}/reject")
    public LeaveResponseDto rejectLeaveRequest(@PathVariable Long id, @RequestBody LeaveRejectionDto leaveRejectionDto) {
        return leaveService.rejectLeaveRequest(id, leaveRejectionDto.getRemarks());
    }
}
