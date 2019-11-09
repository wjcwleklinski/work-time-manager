package com.wjcwleklinski.worktimemanager.entity;

import javax.persistence.*;

@Entity
@Table(name = "employee_project")
public class EmployeeProject {

    @EmbeddedId
    private EmployeeProjectId employeeProjectId;

    @Column(columnDefinition = "integer default 0")
    private Integer hours;

    public EmployeeProjectId getEmployeeProjectId() {
        return employeeProjectId;
    }

    public void setEmployeeProjectId(EmployeeProjectId employeeProjectId) {
        this.employeeProjectId = employeeProjectId;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Long getProjectId() {
        return employeeProjectId.getProjectId();
    }

//    @ManyToOne
//    private Employee employee;
//
//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }
}
