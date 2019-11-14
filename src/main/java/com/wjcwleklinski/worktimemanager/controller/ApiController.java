package com.wjcwleklinski.worktimemanager.controller;

import com.wjcwleklinski.worktimemanager.entity.Employee;
import com.wjcwleklinski.worktimemanager.entity.EmployeeProject;
import com.wjcwleklinski.worktimemanager.entity.EmployeeProjectId;
import com.wjcwleklinski.worktimemanager.entity.Project;
import com.wjcwleklinski.worktimemanager.exception.EmployeeNotFoundException;
import com.wjcwleklinski.worktimemanager.exception.EmployeeProjectNotFoundException;
import com.wjcwleklinski.worktimemanager.exception.WrongFieldNameException;
import com.wjcwleklinski.worktimemanager.repository.EmployeeProjectRepository;
import com.wjcwleklinski.worktimemanager.repository.EmployeeRepository;
import com.wjcwleklinski.worktimemanager.service.EmployeeProjectService;
import com.wjcwleklinski.worktimemanager.service.EmployeeService;
import com.wjcwleklinski.worktimemanager.service.ProjectService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RepositoryRestController
public class ApiController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeProjectService employeeProjectService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private Logger logger;


    @RequestMapping(path = "work-time-records/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH},
            produces="application/hal+json")
    public ResponseEntity<EmployeeProject> putEmployeeProject(@PathVariable("id") EmployeeProjectId id,
                                                              @RequestBody Map<String, Object> update) {

        Integer hoursToUpdate;
        // check if request contains hours
        if (update.containsKey("hours")) {
            hoursToUpdate = (Integer) update.get("hours");
        } else {
            throw new WrongFieldNameException();
        }

        // get previous value of hours in project
        Optional<EmployeeProject> employeeProjectOptional = employeeProjectService.findById(id);
        Optional<Employee> optionalEmployee = employeeService.findById(id.getEmployeeId());

        if ( !(employeeProjectOptional.isPresent() && optionalEmployee.isPresent()) ) {
            throw new EmployeeProjectNotFoundException(id);
        }
        EmployeeProject prevRecord = employeeProjectOptional.get();
        Integer prevHours = prevRecord.getHours();
        prevRecord.setHours(hoursToUpdate);

        Employee employee = optionalEmployee.get();
        employee.updateTotalHours(prevHours, hoursToUpdate);
        employeeService.save(employee);
        employeeProjectService.save(prevRecord);

        return new ResponseEntity<>(prevRecord, HttpStatus.OK);
    }

    @RequestMapping(path = "projects/{projectId}/employees/{employeeId}", method = RequestMethod.DELETE,
            produces="application/hal+json")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployeeFromProject(@PathVariable("projectId") Long projectId,
                                                              @PathVariable("employeeId") Long employeeId){

        Optional<Employee> optEmployee = employeeService.findById(employeeId);
        Optional<Project> optProject = projectService.findById(projectId);
        Optional<EmployeeProject> optRecord = employeeProjectService.findByIds(employeeId, projectId);
        if ( !(optEmployee.isPresent() && optProject.isPresent() && optRecord.isPresent())) {
            throw new EmployeeProjectNotFoundException(new EmployeeProjectId(employeeId, projectId));
        }
        Integer hoursToSubtract = optRecord.get().getHours();
        Employee employee = optEmployee.get();
        employee.updateTotalHours(hoursToSubtract, 0);

        Project project = optProject.get();
        project.removeEmployee(employee);

        employeeService.save(employee);
        projectService.save(project);

    }




    @ExceptionHandler(EmployeeProjectNotFoundException.class)
    public ResponseEntity<Error> recordNotFound(EmployeeProjectNotFoundException e) {

        EmployeeProjectId recordId = e.getId();
        Error error = new Error( "Record: " + recordId.toString() + " not found.");

        return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Error> employeeNotFound(EmployeeNotFoundException e) {

        Long employeeId = e.getEmployeeId();
        Error error = new Error( "Employee: " + employeeId + " not found.");

        return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WrongFieldNameException.class)
    public ResponseEntity<Error> wrongFieldName(WrongFieldNameException e) {

        Error error = new Error("Requested field does not exist.");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}
