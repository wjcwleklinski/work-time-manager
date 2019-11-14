package com.wjcwleklinski.worktimemanager.service;

import com.wjcwleklinski.worktimemanager.entity.Employee;
import com.wjcwleklinski.worktimemanager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Map<String, Object>> report() {

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        for(Employee employee : employeeRepository.findAll()) {
            Map<String, Object> reportItem = new HashMap<>();
            reportItem.put("employeeId", employee.getEmployeeId());
            reportItem.put("firstName", employee.getFirstName());
            reportItem.put("lastName", employee.getLastName());
            reportItem.put("phoneNumber", employee.getPhoneNumber());
            reportItem.put("totalHours", employee.getTotalHours());
            reportItem.put("assignedProjects", employee.getAssignedProjects());
            result.add(reportItem);
        }

        return result;
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }
}
