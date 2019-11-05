package com.wjcwleklinski.worktimemanager.repositories;

import com.wjcwleklinski.worktimemanager.entity.EmployeeDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EmployeeDetailsRepository extends CrudRepository<EmployeeDetails,Long> {
}
