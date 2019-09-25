package com.applicate.webservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.applicate.utils.ServiceConfigurationLoader;
@ComponentScan("com.applicate")
@SpringBootApplication
public class WebServicesApplication {

	public static void main(String[] args) {
		/* ConfigurableApplicationContext context= */
		ConfigurableApplicationContext ctx=SpringApplication.run(WebServicesApplication.class, args);
		  ServiceConfigurationLoader scl=ctx.getBean(ServiceConfigurationLoader.class);
		  String lob=null;
		  scl.getDetailsOfLOB(lob);
		 
		/*
		 * ServiceConfigurationLoader
		 * scl=context.getBean(ServiceConfigurationLoader.class); scl.getDetailsOfLOB();
		 */
		
	}

}
