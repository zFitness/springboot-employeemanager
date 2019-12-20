package cn.employee.manager;

import cn.employee.manager.entity.Employee;
import cn.employee.manager.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeMangerApplicationTests {
	@Autowired
	private EmployeeMapper employeeMapper;

	@Test
	public void contextLoads() {
		List<Employee> employees = employeeMapper.selectList(null);
		employees.forEach(System.out::println);
	}

}
