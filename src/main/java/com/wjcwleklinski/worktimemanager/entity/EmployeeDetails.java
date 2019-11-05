package com.wjcwleklinski.worktimemanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "employee_details")
public class EmployeeDetails {

    @Column(name = "primary_id")
    private Long primaryId;

    @Column(name = "employee_id")
    private Long employeeId;

    public Long getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(Long primaryId) {
        this.primaryId = primaryId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
