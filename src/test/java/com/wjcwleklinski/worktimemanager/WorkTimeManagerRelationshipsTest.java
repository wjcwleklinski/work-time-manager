package com.wjcwleklinski.worktimemanager;


import com.wjcwleklinski.worktimemanager.entity.Employee;
import com.wjcwleklinski.worktimemanager.entity.Project;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class WorkTimeManagerRelationshipsTest {



    @Autowired
    private TestRestTemplate template;

    @Autowired
    private Logger logger;


    @Before
    public void setup(){
        Employee employee1 = new Employee("Adam", "Nowak", "111222333");
        Employee employee2 = new Employee("Jan", "Nowak", "444555666");
        template.postForEntity(Endpoints.EMPLOYEE_ENDPOINT, employee1, Employee.class);
        template.postForEntity(Endpoints.EMPLOYEE_ENDPOINT, employee2, Employee.class);


        Project project1 = new Project("Project 1", "Description 1");
        template.postForEntity(Endpoints.PROJECT_ENDPOINT, project1, Project.class);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-type", "text/uri-list");

        // add two employees to project 1
        HttpEntity<String> httpEntity = new HttpEntity<>(
                Endpoints.EMPLOYEE_ENDPOINT + "/1\n" + Endpoints.EMPLOYEE_ENDPOINT + "/2", requestHeaders);

        template.exchange(Endpoints.PROJECT_ENDPOINT + "/1/employees",
                HttpMethod.POST, httpEntity, String.class);

        logger.info("SETUP");
    }

    @Test
    public void whenSaveManyToManyProject() throws Exception {

        String response = template.getForObject(Endpoints.PROJECT_ENDPOINT + "/1/employees", String.class);

        JSONObject jsonObject = new JSONObject(response).getJSONObject("_embedded");
        JSONArray jsonArray = jsonObject.getJSONArray("employees");

        Assert.assertEquals(jsonArray.getJSONObject(0).getString("lastName"), "Nowak");
        Assert.assertEquals(jsonArray.length(), 2);
        Assert.assertNotNull(jsonArray.getJSONObject(1));

    }

    @Test
    public void whenSaveManyToManyJoinTable() throws Exception{
        String responseJoin = template.getForObject(Endpoints.RECORD_ENDPOINT , String.class);
        JSONObject jsonObjectJoin = new JSONObject(responseJoin).getJSONObject("_embedded");
        JSONArray jsonArrayJoin = jsonObjectJoin.getJSONArray("employeeProjects");

        Assert.assertEquals(jsonArrayJoin.getJSONObject(0).getLong("projectId"), 1L);
    }
}
