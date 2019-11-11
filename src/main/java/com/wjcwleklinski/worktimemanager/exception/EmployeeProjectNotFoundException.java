package com.wjcwleklinski.worktimemanager.exception;

import com.wjcwleklinski.worktimemanager.entity.EmployeeProjectId;

public class EmployeeProjectNotFoundException extends RuntimeException {

    private EmployeeProjectId id;

    public EmployeeProjectNotFoundException(EmployeeProjectId id) {
        this.id = id;
    }

    public EmployeeProjectId getId() {
        return id;
    }

}
