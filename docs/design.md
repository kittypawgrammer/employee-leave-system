
# 📄 Database Document – Employee Leave Tracker

---

## **1. Database**

* **DBMS**: Oracle
* **Schema Name**: `EMP_LEAVE_TRACKER`

---

## **2. Tables**

### **Employee**

| Column      | Type          | Constraints                                 |
| ----------- | ------------- | ------------------------------------------- |
| ID          | NUMBER        | Primary Key, Auto Increment                 |
| NAME        | VARCHAR2(100) | Not Null                                    |
| EMAIL       | VARCHAR2(100) | Unique, Not Null                            |
| ROLE        | VARCHAR2(20)  | Not Null (Values: EMPLOYEE, MANAGER, ADMIN) |

---

### **Leave\_Request**

| Column           | Type          | Constraints                                            |
| ---------------- | ------------- | ------------------------------------------------------ |
| ID               | NUMBER        | Primary Key, Auto Increment                            |
| EMPLOYEE\_ID     | NUMBER        | Foreign Key → Employee(ID)                             |
| LEAVE\_TYPE      | VARCHAR2(20)  | Values: SICK, CASUAL, VACATION, UNPAID                 |
| START\_DATE      | DATE          | Not Null                                               |
| END\_DATE        | DATE          | Not Null                                               |
| REASON           | VARCHAR2(255) | Optional                                               |
| STATUS           | VARCHAR2(20)  | Default: PENDING (Values: PENDING, APPROVED, REJECTED) |
| MANAGER\_REMARKS | VARCHAR2(255) | Optional                                               |

---

## **3. Relationships**

* **Employee → Leave\_Request**

  * One employee can have many leave requests.
  * `EMPLOYEE.ID = LEAVE_REQUEST.EMPLOYEE_ID`

---

## **4. Example Data**

**Employee**

| ID | NAME     | EMAIL                                               | ROLE     |
| -- | -------- | --------------------------------------------------- | -------- |
| 1  | John Doe | [john.doe@company.com](mailto:john.doe@company.com) | EMPLOYEE |
| 2  | Sarah Li | [sarah.li@company.com](mailto:sarah.li@company.com) | MANAGER  |

**Leave\_Request**

| ID | EMPLOYEE\_ID | LEAVE\_TYPE | START\_DATE | END\_DATE  | STATUS   | MANAGER\_REMARKS     |
| -- | ------------ | ----------- | ----------- | ---------- | -------- | -------------------- |
| 1  | 1            | SICK        | 2025-08-22  | 2025-08-23 | PENDING  | NULL                 |
| 2  | 1            | VACATION    | 2025-09-01  | 2025-09-05 | APPROVED | Enjoy your vacation! |

---

