package main.java.com.example.employee_leave_system.services;

import com.example.employee_leave_system.dtos.LeaveRequestDto;
import com.example.employee_leave_system.dtos.LeaveResponseDto;
import com.example.employee_leave_system.entities.LeaveRequest;
import com.example.employee_leave_system.respositories.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaveService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    // Employee apply for leave
    public List<LeaveResponseDto> applyLeave(LeaveRequestDto leaveRequestDto) {
        // Convert DTO to entity
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setEmployeeId(leaveRequestDto.getEmployeeId());
        leaveRequest.setLeaveType(leaveRequestDto.getLeaveType());
        leaveRequest.setStartDate(leaveRequestDto.getStartDate());
        leaveRequest.setEndDate(leaveRequestDto.getEndDate());
        leaveRequest.setReason(leaveRequestDto.getReason());
        leaveRequest.setStatus("PENDING");

        // Save the leave request
        LeaveRequest savedLeaveRequest = leaveRequestRepository.save(leaveRequest);

        // Convert the saved entity to DTO
        LeaveResponseDto leaveResponseDto = new LeaveResponseDto();
        leaveResponseDto.setId(savedLeaveRequest.getId());
        leaveResponseDto.setEmployeeId(savedLeaveRequest.getEmployeeId());
        leaveResponseDto.setLeaveType(savedLeaveRequest.getLeaveType());
        leaveResponseDto.setStartDate(savedLeaveRequest.getStartDate());
        leaveResponseDto.setEndDate(savedLeaveRequest.getEndDate());
        leaveResponseDto.setReason(savedLeaveRequest.getReason());
        leaveResponseDto.setStatus(savedLeaveRequest.getStatus());

        List<LeaveResponseDto> leaveResponseDtos = new ArrayList<>();
        leaveResponseDtos.add(leaveResponseDto);

        // Return list of LeaveResponseDto
        return leaveResponseDtos;
    }

    // View my leave requests
    public List<LeaveResponseDto> viewMyLeaveRequests(Long employeeId) {
        List<LeaveRequest> leaveRequests = leaveRequestRepository.findByEmployeeId(employeeId);
        List<LeaveResponseDto> leaveResponseDtos = new ArrayList<>();

        for (LeaveRequest leaveRequest : leaveRequests) {
            LeaveResponseDto leaveResponseDto = new LeaveResponseDto();
            leaveResponseDto.setId(leaveRequest.getId());
            leaveResponseDto.setEmployeeId(leaveRequest.getEmployeeId());
            leaveResponseDto.setLeaveType(leaveRequest.getLeaveType());
            leaveResponseDto.setStartDate(leaveRequest.getStartDate());
            leaveResponseDto.setEndDate(leaveRequest.getEndDate());
            leaveResponseDto.setReason(leaveRequest.getReason());
            leaveResponseDto.setStatus(leaveRequest.getStatus());
            leaveResponseDtos.add(leaveResponseDto);
        }

        return leaveResponseDtos;
    }

    // Cancel leave request
    public String cancelLeaveRequest(Long id) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id).orElse(null);

        if (leaveRequest == null) {
            return "Leave request not found.";
        }

        if (!leaveRequest.getStatus().equals("PENDING")) {
            return "Leave request cannot be cancelled as it is not in PENDING state.";
        }

        leaveRequestRepository.deleteById(id);
        return "Leave request cancelled successfully.";
    }

    // View all leave requests (Team)
    public List<LeaveResponseDto> viewAllLeaveRequests(String status) {
        List<LeaveRequest> leaveRequests;

        if (status != null && !status.isEmpty()) {
            leaveRequests = leaveRequestRepository.findByStatus(status);
        } else {
            leaveRequests = leaveRequestRepository.findAll();
        }

        List<LeaveResponseDto> leaveResponseDtos = new ArrayList<>();

        for (LeaveRequest leaveRequest : leaveRequests) {
            LeaveResponseDto leaveResponseDto = new LeaveResponseDto();
            leaveResponseDto.setId(leaveRequest.getId());
            leaveResponseDto.setEmployeeId(leaveRequest.getEmployeeId());
            leaveResponseDto.setLeaveType(leaveRequest.getLeaveType());
            leaveResponseDto.setStartDate(leaveRequest.getStartDate());
            leaveResponseDto.setEndDate(leaveRequest.getEndDate());
            leaveResponseDto.setReason(leaveRequest.getReason());
            leaveResponseDto.setStatus(leaveRequest.getStatus());
            leaveResponseDtos.add(leaveResponseDto);
        }

        return leaveResponseDtos;
    }

    // Approve leave request
    public LeaveResponseDto approveLeaveRequest(Long id) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id).orElse(null);

        if (leaveRequest == null) {
            return null; // Or throw an exception
        }

        leaveRequest.setStatus("APPROVED");
        LeaveRequest updatedLeaveRequest = leaveRequestRepository.save(leaveRequest);

        LeaveResponseDto leaveResponseDto = new LeaveResponseDto();
        leaveResponseDto.setId(updatedLeaveRequest.getId());
        leaveResponseDto.setEmployeeId(updatedLeaveRequest.getEmployeeId());
        leaveResponseDto.setLeaveType(updatedLeaveRequest.getLeaveType());
        leaveResponseDto.setStartDate(updatedLeaveRequest.getStartDate());
        leaveResponseDto.setEndDate(updatedLeaveRequest.getEndDate());
        leaveResponseDto.setReason(updatedLeaveRequest.getReason());
        leaveResponseDto.setStatus(updatedLeaveRequest.getStatus());

        return leaveResponseDto;
    }

    // Reject leave request
    public LeaveResponseDto rejectLeaveRequest(Long id, String remarks) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id).orElse(null);

        if (leaveRequest == null) {
            return null; // Or throw an exception
        }

        leaveRequest.setStatus("REJECTED");
        leaveRequest.setManagerRemarks(remarks);
        LeaveRequest updatedLeaveRequest = leaveRequestRepository.save(leaveRequest);

        LeaveResponseDto leaveResponseDto = new LeaveResponseDto();
        leaveResponseDto.setId(updatedLeaveRequest.getId());
        leaveResponseDto.setEmployeeId(updatedLeaveRequest.getEmployeeId());
        leaveResponseDto.setLeaveType(updatedLeaveRequest.getLeaveType());
        leaveResponseDto.setStartDate(updatedLeaveRequest.getStartDate());
        leaveResponseDto.setEndDate(updatedLeaveRequest.getEndDate());
        leaveResponseDto.setReason(updatedLeaveRequest.getReason());
        leaveResponseDto.setStatus(updatedLeaveRequest.getStatus());
        leaveResponseDto.setManagerRemarks(updatedLeaveRequest.getManagerRemarks());

        return leaveResponseDto;
    }
}
