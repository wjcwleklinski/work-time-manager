package com.wjcwleklinski.worktimemanager.entity;

import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeProjectId.employeeId")
    private List<EmployeeProject> assignedProjects;

    @Column(name = "total_hours")
    private Integer totalHours = 0;

    public void updateTotalHours(Integer prevHours, Integer hoursToUpdate){
        //this.totalHours = this.totalHours - prevHours + hoursToUpdate;
        this.totalHours = 125;
    }

    public Employee() {}

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<EmployeeProject> getAssignedProjects() {
        return assignedProjects;
    }

    public void setAssignedProjects(List<EmployeeProject> assignedProjects) {
        this.assignedProjects = assignedProjects;
    }

    public Integer getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Integer totalHours) {
        this.totalHours = totalHours;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", totalHours=" + totalHours +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(employeeId, employee.employeeId) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(phoneNumber, employee.phoneNumber) &&
                Objects.equals(totalHours, employee.totalHours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, firstName, lastName, phoneNumber, totalHours);
    }
}
