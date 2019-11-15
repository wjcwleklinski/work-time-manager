package com.wjcwleklinski.worktimemanager.service;

import com.wjcwleklinski.worktimemanager.entity.EmployeeProject;
import com.wjcwleklinski.worktimemanager.entity.EmployeeProjectId;
import com.wjcwleklinski.worktimemanager.repository.EmployeeProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeProjectService {

    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;

//    public List<Map<String, Object>> report() {
//
//        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
//
//        for(EmployeeProject ep : employeeProjectRepository.findAll()) {
//            Map<String, Object> reportItem = new HashMap<>();
//            reportItem.put("projectId", ep.getProjectId());
//            reportItem.put("hours", ep.getHours());
//            result.add(reportItem);
//        }
//
//        return result;
//    }

    public Optional<EmployeeProject> findById(EmployeeProjectId id) {
        return employeeProjectRepository.findById(id);
    }

    public EmployeeProject save(EmployeeProject employeeProject) {
        return employeeProjectRepository.save(employeeProject);
    }

    public Optional<EmployeeProject> findByIds(Long employeeId, Long projectId) {
        EmployeeProjectId id = new EmployeeProjectId(employeeId, projectId);
        return employeeProjectRepository.findById(id);
    }

}
