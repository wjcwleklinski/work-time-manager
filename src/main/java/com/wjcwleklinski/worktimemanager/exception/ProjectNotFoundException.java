package com.wjcwleklinski.worktimemanager.exception;

public class ProjectNotFoundException extends RuntimeException {

    private Long projectId;

    public ProjectNotFoundException(Long projectId) {
        this.projectId = projectId;
    }

    public Long getProjectId() {
        return projectId;
    }

}
