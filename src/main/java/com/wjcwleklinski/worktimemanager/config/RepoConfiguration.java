package com.wjcwleklinski.worktimemanager.config;

import com.wjcwleklinski.worktimemanager.entity.Employee;
import com.wjcwleklinski.worktimemanager.entity.EmployeeProject;
import com.wjcwleklinski.worktimemanager.entity.Project;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class RepoConfiguration implements RepositoryRestConfigurer {


    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Employee.class, Project.class, EmployeeProject.class);
    }
}
