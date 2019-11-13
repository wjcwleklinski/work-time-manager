package com.wjcwleklinski.worktimemanager;


import com.wjcwleklinski.worktimemanager.entity.Employee;
import com.wjcwleklinski.worktimemanager.repository.EmployeeProjectRepository;
import com.wjcwleklinski.worktimemanager.repository.EmployeeRepository;
import com.wjcwleklinski.worktimemanager.repository.ProjectRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;



import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkTimeManagerUnitTests {

//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @Autowired
//    private EmployeeProjectRepository employeeProjectRepository;
//
//    @Autowired
//    private ProjectRepository projectRepository;
//
//    @Autowired
//    private TestEntityManager entityManager;
//    @Test
//	public void contextLoads() throws Exception{
//
//	}

    @Test
    public void whenFindEmployeeById() {
        Assert.assertEquals("tak", "nie");
//        Employee employee = new Employee("Jan", "Kowalski", "123456789");
//        employee.setEmployeeId(1L);
//        entityManager.persist(employee);
//        entityManager.flush();
//
//        Optional<Employee> employeeFound = employeeRepository.findById(employee.getEmployeeId());
//        Assert.assertTrue(employeeFound.isPresent());
//        Assert.assertEquals(employee.getEmployeeId(), employeeFound.get().getEmployeeId());
//
//        Assert.assertEquals(employee.getFirstName(), employeeFound.get().getFirstName());
//        Assert.assertEquals(employee.getLastName(), employeeFound.get().getLastName());
//        Assert.assertEquals(employee.getPhoneNumber(), employeeFound.get().getPhoneNumber());

    }
}
