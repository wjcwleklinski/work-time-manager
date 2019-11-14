package com.wjcwleklinski.worktimemanager.service;

import com.wjcwleklinski.worktimemanager.entity.EmployeeProject;
import com.wjcwleklinski.worktimemanager.entity.Project;
import com.wjcwleklinski.worktimemanager.repository.EmployeeProjectRepository;
import com.wjcwleklinski.worktimemanager.repository.ProjectRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {


    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;


    public List<Map<String, Object>> report() {

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        for (Project project : projectRepository.findAll()) {
            Map<String, Object> reportItem = new HashMap<>();
            reportItem.put("projectName", project.getName());
            Long projectId = project.getProjectId();
            Integer totalProjectHours = 0;
            for (EmployeeProject ep : employeeProjectRepository.findAll()) {
                if (ep.getProjectId().equals(projectId)) {
                    totalProjectHours += ep.getHours();
                }
            }
            reportItem.put("hours", totalProjectHours);
            result.add(reportItem);
        }
        return result;
    }
}
