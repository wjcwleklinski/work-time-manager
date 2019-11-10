package com.wjcwleklinski.worktimemanager.repositories;

import com.wjcwleklinski.worktimemanager.entity.EmployeeProject;
import com.wjcwleklinski.worktimemanager.entity.EmployeeProjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "work-time-records")
public interface EmployeeProjectRepository extends CrudRepository<EmployeeProject, EmployeeProjectId> {
}
