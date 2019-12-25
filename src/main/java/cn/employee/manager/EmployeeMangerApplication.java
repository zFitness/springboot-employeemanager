package cn.employee.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author zfitness
 */
@SpringBootApplication
public class EmployeeMangerApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(EmployeeMangerApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(EmployeeMangerApplication.class, args);
	}

}
