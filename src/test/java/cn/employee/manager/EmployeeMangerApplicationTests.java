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

	@Test
	public void testInsert() {
		Employee employee = new Employee();
		employee.setDepartment(1);
		employee.setName("zzz");
		employee.setName("zzz");
		employee.setEmail("zz@qq.com");
		employee.setTel("1222");
		employee.setPassword("1212");
		employee.setState("T");
		employee.setAuthority(0);
		int i = employeeMapper.insert(employee);
		System.out.println(employee.getId());
	}

}
