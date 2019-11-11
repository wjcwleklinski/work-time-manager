package com.wjcwleklinski.worktimemanager.controller;

import com.wjcwleklinski.worktimemanager.entity.Employee;
import com.wjcwleklinski.worktimemanager.entity.EmployeeProject;
import com.wjcwleklinski.worktimemanager.entity.EmployeeProjectId;
import com.wjcwleklinski.worktimemanager.exception.EmployeeNotFoundException;
import com.wjcwleklinski.worktimemanager.exception.EmployeeProjectNotFoundException;
import com.wjcwleklinski.worktimemanager.exception.WrongFieldNameException;
import com.wjcwleklinski.worktimemanager.repository.EmployeeProjectRepository;
import com.wjcwleklinski.worktimemanager.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;
import java.util.Optional;

@BasePathAwareController
public class ApiController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeProjectRepository employeeProjectRepository;

    @Autowired
    Logger logger;


    @RequestMapping(path = "work-time-records/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH}, produces="application/hal+json")
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
        Optional<EmployeeProject> employeeProjectOptional = employeeProjectRepository.findById(id);
        Optional<Employee> optionalEmployee = employeeRepository.findById(id.getEmployeeId());

        if ( !(employeeProjectOptional.isPresent() && optionalEmployee.isPresent()) ) {
            throw new EmployeeProjectNotFoundException(id);
        }
        EmployeeProject prevRecord = employeeProjectOptional.get();
        Integer prevHours = prevRecord.getHours();
        prevRecord.setHours(hoursToUpdate);

        Employee employee = optionalEmployee.get();
        employee.updateTotalHours(prevHours, hoursToUpdate);
        employeeRepository.save(employee);
        employeeProjectRepository.save(prevRecord);

        return new ResponseEntity<>(prevRecord, HttpStatus.OK);


    }

    @ExceptionHandler(EmployeeProjectNotFoundException.class)
    public ResponseEntity<Error> recordNotFound(EmployeeProjectNotFoundException e) {

        EmployeeProjectId recordId = e.getId();
        Error error = new Error( "Record: " + recordId.toString() + " not found.");

        return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(EmployeeNotFoundException.class)
//    public ResponseEntity<Error> employeeNotFound(EmployeeNotFoundException e) {
//
//        Long employeeId = e.getEmployeeId();
//        Error error = new Error( "Employee: " + employeeId + " not found.");
//
//        return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(WrongFieldNameException.class)
    public ResponseEntity<Error> wrongFieldName(WrongFieldNameException e) {

        Error error = new Error("Requested field does not exist.");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}
