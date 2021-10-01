package com.smart.assignment;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@SpringBootApplication
//@ComponentScan("com.smart.assignment.**")
//@EntityScan("com.smart.assignment.entity")
//@EnableJpaRepositories(basePackages="com.smart.assignment.repository")
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AssignmentApplication.class);
	}
}
