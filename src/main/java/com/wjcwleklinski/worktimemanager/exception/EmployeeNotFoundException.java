package com.wjcwleklinski.worktimemanager.exception;

public class EmployeeNotFoundException extends RuntimeException {

    private Long employeeId;

    public EmployeeNotFoundException(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

}
