package com.wjcwleklinski.worktimemanager.repositories;

import com.wjcwleklinski.worktimemanager.entity.ProjectDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProjectDetailsRepository extends CrudRepository<ProjectDetails,Long> {
}
