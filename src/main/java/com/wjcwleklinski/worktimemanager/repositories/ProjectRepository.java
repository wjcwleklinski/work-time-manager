package com.wjcwleklinski.worktimemanager.repositories;

import com.wjcwleklinski.worktimemanager.entity.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProjectRepository extends CrudRepository<Project,Long> {
}
