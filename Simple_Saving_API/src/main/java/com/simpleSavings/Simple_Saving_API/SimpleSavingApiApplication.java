package com.simpleSavings.Simple_Saving_API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
@EntityScan("com.simpleSavings.Simple_Saving_API.model")
public class SimpleSavingApiApplication extends SpringBootServletInitializer {
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	return application.sources(SimpleSavingApiApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SimpleSavingApiApplication.class, args);
	}
	

}