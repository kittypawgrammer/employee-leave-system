# 📄 API Specification Document

## Project: Employee Leave Tracker

---

### 1. **Base URL**

```
/api
```

---

### 2. **Authentication & Security**

* **Method**: JWT-based authentication (planned)
* **Roles**: `EMPLOYEE`, `MANAGER`, `ADMIN`
* All endpoints require authentication.
* Role-based access control is enforced.

---

### 3. **API Endpoints**

---

#### 🧑‍💼 Employee APIs

##### 1. Apply for Leave

* **Endpoint**: `POST /leaves`
* **Role**: Employee
* **Request Body**:

```json
{
  "leaveType": "SICK",
  "startDate": "2025-08-25",
  "endDate": "2025-08-27",
  "reason": "Medical checkup"
}
```

* **Response (201 Created)**:

```json
{
  "id": 101,
  "employeeId": 1,
  "leaveType": "SICK",
  "startDate": "2025-08-25",
  "endDate": "2025-08-27",
  "reason": "Medical checkup",
  "status": "PENDING"
}
```

---

##### 2. View My Leave Requests

* **Endpoint**: `GET /leaves/my`
* **Role**: Employee
* **Response (200 OK)**:

```json
[
  {
    "id": 101,
    "leaveType": "SICK",
    "startDate": "2025-08-25",
    "endDate": "2025-08-27",
    "status": "PENDING"
  },
  {
    "id": 95,
    "leaveType": "VACATION",
    "startDate": "2025-07-10",
    "endDate": "2025-07-15",
    "status": "APPROVED"
  }
]
```

---

##### 3. Cancel Leave Request

* **Endpoint**: `DELETE /leaves/{id}`
* **Role**: Employee
* **Condition**: Only cancellable if status = `PENDING`
* **Response (200 OK)**:

```json
{
  "message": "Leave request cancelled successfully."
}
```

---

#### 👨‍💼 Manager/HR APIs

##### 4. View All Leave Requests (Team)

* **Endpoint**: `GET /leaves`
* **Role**: Manager/HR
* **Query Params**: `status` (optional: PENDING/APPROVED/REJECTED)
* **Response (200 OK)**:

```json
[
  {
    "id": 101,
    "employeeName": "Alice",
    "leaveType": "SICK",
    "startDate": "2025-08-25",
    "endDate": "2025-08-27",
    "status": "PENDING"
  },
  {
    "id": 95,
    "employeeName": "Bob",
    "leaveType": "VACATION",
    "startDate": "2025-07-10",
    "endDate": "2025-07-15",
    "status": "APPROVED"
  }
]
```

---

##### 5. Approve Leave Request

* **Endpoint**: `PUT /leaves/{id}/approve`
* **Role**: Manager/HR
* **Response (200 OK)**:

```json
{
  "id": 101,
  "status": "APPROVED",
  "managerRemarks": "Approved. Enjoy your leave!"
}
```

---

##### 6. Reject Leave Request

* **Endpoint**: `PUT /leaves/{id}/reject`
* **Role**: Manager/HR
* **Request Body**:

```json
{
  "remarks": "Project deadline, leave not possible."
}
```

* **Response (200 OK)**:

```json
{
  "id": 101,
  "status": "REJECTED",
  "managerRemarks": "Project deadline, leave not possible."
}
```

---

#### 🛠️ Admin APIs

##### 7. Add Employee

* **Endpoint**: `POST /employees`
* **Role**: Admin
* **Request Body**:

```json
{
  "name": "Charlie",
  "email": "charlie@example.com",
  "role": "EMPLOYEE"
}
```

* **Response (201 Created)**:

```json
{
  "id": 5,
  "name": "Charlie",
  "email": "charlie@example.com",
  "role": "EMPLOYEE"
}
```

---

##### 8. List Employees

* **Endpoint**: `GET /employees`
* **Role**: Admin
* **Response (200 OK)**:

```json
[
  {
    "id": 1,
    "name": "Alice",
    "email": "alice@example.com",
    "role": "EMPLOYEE"
  },
  {
    "id": 2,
    "name": "Bob",
    "email": "bob@example.com",
    "role": "MANAGER"
  }
]
```

---

##### 9. Delete Employee

* **Endpoint**: `DELETE /employees/{id}`
* **Role**: Admin
* **Response (200 OK)**:

```json
{
  "message": "Employee removed successfully."
}
```

---

### 4. **Error Handling (Common Format)**

```json
{
  "timestamp": "2025-08-21T14:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "End date cannot be before start date",
  "path": "/api/leaves"
}
```
