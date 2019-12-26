package cn.employee.manager;

import cn.employee.manager.dto.JobPersonNumbers;
import cn.employee.manager.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={EmployeeMangerApplication.class})
public class MyTest {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void test1() {
        List<JobPersonNumbers> jobPersonsNumbers = employeeMapper.getJobPersonsNumbers();
        jobPersonsNumbers.forEach(System.out::println);
    }
}
