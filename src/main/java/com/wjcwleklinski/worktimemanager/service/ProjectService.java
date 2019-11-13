package com.wjcwleklinski.worktimemanager.service;

import com.wjcwleklinski.worktimemanager.entity.Project;
import com.wjcwleklinski.worktimemanager.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public List<Map<String, Object>> report() {

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        for(Project project : projectRepository.findAll()) {
            Map<String, Object> reportItem = new HashMap<>();
            reportItem.put("projectId", project.getProjectId());
            reportItem.put("name", project.getName());
            reportItem.put("description", project.getDescription());
            result.add(reportItem);
        }

        return result;
    }
}
