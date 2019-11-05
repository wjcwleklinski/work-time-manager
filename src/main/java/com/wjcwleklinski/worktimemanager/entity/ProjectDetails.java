package com.wjcwleklinski.worktimemanager.entity;


import javax.persistence.*;

@Entity
@Table(name = "project_details")
public class ProjectDetails {

    @Id
    @GeneratedValue
    private Long primaryId;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "hours")
    private Long hours;

    public Long getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(Long primaryId) {
        this.primaryId = primaryId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getHours() {
        return hours;
    }

    public void setHours(Long hours) {
        this.hours = hours;
    }
}
