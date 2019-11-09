package com.wjcwleklinski.worktimemanager.entity;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

public class EmployeeProjectId implements Serializable {


    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "employee_id")
    private Long employeeId;


    public EmployeeProjectId(Long projectId, Long employeeId) {
        this.projectId = projectId;
        this.employeeId = employeeId;
    }

    public EmployeeProjectId() {}


    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        EmployeeProjectId that = (EmployeeProjectId) o;
        return Objects.equals(projectId, that.projectId) &&
                Objects.equals(employeeId, that.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId,projectId);
    }
}

