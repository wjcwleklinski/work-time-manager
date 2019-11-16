package com.wjcwleklinski.worktimemanager;

import com.wjcwleklinski.worktimemanager.entity.Employee;
import com.wjcwleklinski.worktimemanager.entity.Project;
import com.wjcwleklinski.worktimemanager.service.EmployeeService;
import com.wjcwleklinski.worktimemanager.service.ProjectService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class WorkTimeManagerServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;


    @Before
    public void setup() {
        Employee employee = new Employee("Jan", "Kowalski", "111222333");
        Project project = new Project("Project 1", "Description 1");

        Employee employeeSaved = employeeService.save(employee);
        Project projectSaved = projectService.save(project);

        Assert.assertEquals(employee, employeeSaved);
        Assert.assertEquals(project, projectSaved);
    }

    @Test
    public void whenReportCalled() {
        List<Map<String, Object>> employeeResult = employeeService.report();
        List<Map<String, Object>> projectResult = projectService.report();

        Assert.assertEquals("Jan", employeeResult.get(0).get("firstName"));
        Assert.assertEquals("Project 1", projectResult.get(0).get("name"));
    }
}
