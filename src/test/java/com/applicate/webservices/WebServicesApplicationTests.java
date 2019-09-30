package com.applicate.webservices;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.applicate.utils.configurationProvider.DefaultConfigurationProvider;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebServicesApplicationTests {
	
	@Autowired
	ApplicationContext context;
	
	@Test
	public void contextLoads() {
		System.out.println(context.getBean(DefaultConfigurationProvider.class).toString());
	}
    
}
