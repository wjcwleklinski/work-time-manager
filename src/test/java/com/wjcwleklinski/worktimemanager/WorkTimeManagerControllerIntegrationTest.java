package com.wjcwleklinski.worktimemanager;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.wjcwleklinski.worktimemanager.controller.ApiController;
import com.wjcwleklinski.worktimemanager.controller.ReportController;
import com.wjcwleklinski.worktimemanager.entity.Employee;
import com.wjcwleklinski.worktimemanager.entity.Project;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class WorkTimeManagerControllerIntegrationTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private ApiController apiController;

    @Autowired
    private ReportController reportController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void whenPostBeans() throws Exception {
        Employee employee1 = new Employee("Adam", "Nowak", "111222333");
        Employee employee2 = new Employee("Jan", "Nowak", "444555666");
        Project project1 = new Project("Project 1", "Description 1");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();

        String requestJson1 = writer.writeValueAsString(employee1);
        String requestJson2 = writer.writeValueAsString(employee2);
        String requestJson3 = writer.writeValueAsString(project1);

        RequestBuilder rb1 = MockMvcRequestBuilders
                .post(Endpoints.EMPLOYEE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson1);
        mockMvc.perform(rb1).andExpect(MockMvcResultMatchers.status().isCreated());
        RequestBuilder rb2 = MockMvcRequestBuilders
                .post(Endpoints.EMPLOYEE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson2);
        mockMvc.perform(rb2).andExpect(MockMvcResultMatchers.status().isCreated());
        RequestBuilder rb3 = MockMvcRequestBuilders
                .post(Endpoints.PROJECT_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson3);
        mockMvc.perform(rb3).andExpect(MockMvcResultMatchers.status().isCreated());

        // association
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-type", "text/uri-list");
        HttpEntity<String> httpEntity = new HttpEntity<>(
                Endpoints.EMPLOYEE_ENDPOINT + "/1\n" + Endpoints.EMPLOYEE_ENDPOINT + "/2", requestHeaders);

        template.exchange(Endpoints.PROJECT_ENDPOINT + "/1/employees",
                HttpMethod.POST, httpEntity, String.class);

    }


    @Test
    public void whenControllerInjected() {
        Assert.assertNotNull(apiController);
        Assert.assertNotNull(reportController);
    }

    @Test
    public void whenGetEmployees() throws Exception {

        RequestBuilder rb = MockMvcRequestBuilders
                .get(Endpoints.EMPLOYEE_ENDPOINT + "/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON);
        ResultMatcher rm1 = MockMvcResultMatchers
                .status()
                .isOk();
        ResultMatcher rm2 = MockMvcResultMatchers
                .jsonPath("$.employeeId")
                .value("1");
        mockMvc.perform(rb).andExpect(rm1).andExpect(rm2);
    }

    @Test
    public void whenGetProjects() throws Exception {

        RequestBuilder rb = MockMvcRequestBuilders
                .get(Endpoints.PROJECT_ENDPOINT + "/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON);
        ResultMatcher rm1 = MockMvcResultMatchers
                .status()
                .isOk();
        ResultMatcher rm2 = MockMvcResultMatchers
                .jsonPath("$.projectId")
                .value("1");
        mockMvc.perform(rb).andExpect(rm1).andExpect(rm2);
    }


    @Test
    public void whenPutHoursToRecord() throws Exception{
        String jsonValue = "{\"hours\":20}";
        RequestBuilder rb = MockMvcRequestBuilders
                .put(Endpoints.RECORD_ENDPOINT + "/1_1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonValue);
        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.hours").value("20"));
    }

    @Test
    public void whenDeleteEmployeeFromProject() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders
                .delete(Endpoints.PROJECT_ENDPOINT + "/1/employees/1")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
