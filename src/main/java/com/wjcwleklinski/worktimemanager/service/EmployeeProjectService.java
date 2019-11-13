package com.wjcwleklinski.worktimemanager.service;

import com.wjcwleklinski.worktimemanager.entity.EmployeeProject;
import com.wjcwleklinski.worktimemanager.repository.EmployeeProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeProjectService {

    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;

    public List<Map<String, Object>> report() {

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        for(EmployeeProject ep : employeeProjectRepository.findAll()) {
            Map<String, Object> reportItem = new HashMap<>();
            reportItem.put("projectId", ep.getProjectId());
            reportItem.put("hours", ep.getHours());
            result.add(reportItem);
        }

        return result;
    }
}
