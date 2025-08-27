package com.example.employee_leave_system.entities;

@Entity
public class employee {

    @Id
    @GeneratedValue 
    @Column(name = "employee_id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.String)
    @Column(name = "role")
    private Role role; 
}

enum Role {
    EMPLOYEE,
    MANAGER,
    ADMIN
}