package com.wjcwleklinski.worktimemanager.controller;

import com.wjcwleklinski.worktimemanager.entity.Employee;
import com.wjcwleklinski.worktimemanager.entity.Project;
import com.wjcwleklinski.worktimemanager.repositories.EmployeeRepository;
import com.wjcwleklinski.worktimemanager.repositories.ProjectRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
public class ApiController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    Logger logger;


//    @RequestMapping(path = "/projects/{id}/assignedProjects", method = RequestMethod.PUT)
//    public ResponseEntity<Project> attach(@PathVariable("id") Long id, @RequestBody Employee employee) {
//
//        return new ResponseEntity<Project>(project, HttpStatus.OK);
//    }
}
