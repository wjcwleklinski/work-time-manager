package com.wjcwleklinski.worktimemanager.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long projectId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "employee_project",
    joinColumns = @JoinColumn(name = "project_id"),
    inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }


    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", employees=" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(projectId, project.projectId) &&
                Objects.equals(name, project.name) &&
                Objects.equals(description, project.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, name, description);
    }
}
