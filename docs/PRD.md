# 📄 Product Requirements Document (PRD)

## Project: Employee Leave Tracker

---

### 1. **Introduction**

The **Employee Leave Tracker** is a web-based application built with **Spring Boot** to manage and track employee leave requests. The system provides employees with a simple interface to apply for leave, while managers/HR can review, approve, or reject requests.

---

### 2. **Objectives**

* Automate leave request and approval process.
* Provide transparency between employees and managers.
* Keep track of leave history for employees.
* Generate simple reports (e.g., leaves taken, pending requests).

---

### 3. **Key Features**

#### 🧑‍💼 Employee Module

* Apply for leave (start date, end date, leave type, reason).
* View leave history (status: pending/approved/rejected).
* Cancel leave request (if pending).

#### 👨‍💼 Manager/HR Module

* View all leave requests from team members.
* Approve/Reject leave requests.
* Add remarks when rejecting a request.

#### 📊 Admin/Reporting

* View summary reports (e.g., total leaves by employee).
* Manage employee records (basic CRUD).

---

### 4. **User Roles & Permissions**

* **Employee**: Create/View/Cancel leave requests.
* **Manager/HR**: Approve/Reject leave requests, view reports.
* **Admin**: Manage employees, configure leave policies.

---

### 5. **Entities (Data Model)**

1. **Employee**

   * `id` (int, PK)
   * `name` (string)
   * `email` (string)
   * `role` (enum: EMPLOYEE, MANAGER, ADMIN)

2. **LeaveRequest**

   * `id` (int, PK)
   * `employeeId` (FK → Employee)
   * `leaveType` (enum: SICK, CASUAL, VACATION, UNPAID)
   * `startDate` (date)
   * `endDate` (date)
   * `reason` (string)
   * `status` (enum: PENDING, APPROVED, REJECTED)
   * `managerRemarks` (string, optional)

---

### 6. **APIs (Sample Endpoints)**

#### Employee APIs

* `POST /api/leaves` → Apply for leave
* `GET /api/leaves/my` → View own leave requests
* `DELETE /api/leaves/{id}` → Cancel leave (if pending)

#### Manager/HR APIs

* `GET /api/leaves` → View all leave requests
* `PUT /api/leaves/{id}/approve` → Approve leave
* `PUT /api/leaves/{id}/reject` → Reject leave (with remarks)

#### Admin APIs

* `POST /api/employees` → Add new employee
* `GET /api/employees` → List employees
* `DELETE /api/employees/{id}` → Remove employee

---

### 7. **Tech Stack**

* **Backend**: Java 8+/Spring Boot
* **Database**: H2 (dev) / MySQL or Oracle (prod)
* **Security**: Spring Security (Role-based access)
* **API Docs**: Swagger/OpenAPI
* **Testing**: JUnit, Mockito

---

### 8. **Future Enhancements**

* Email/SMS notifications on leave approval/rejection.
* Leave balance calculation & accrual system.
* Integration with payroll system.
* Frontend UI (React/Angular) for better user experience.
