package com.applicate.webservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.applicate.service.contentuploader.impl.AzureUploader;
import com.applicate.utils.configuration.Azure;
import com.applicate.utils.configuration.ServiceConfigurationImpl;
import com.applicate.utils.configurationProvider.DefaultConfigurationProvider;
@ComponentScan("com.applicate")
@SpringBootApplication
public class WebServicesApplication {

	public static ConfigurableApplicationContext applicationContext;
	public static void main(String[] args) {
		applicationContext = SpringApplication.run(WebServicesApplication.class, args);	
		/*System.out.println(*/applicationContext.getBean(AzureUploader.class,"hi");//.inti("his");//);
		/*System.out.println(*/applicationContext.getBean(AzureUploader.class,"hi1");//.inti("byses");//);
		
		/*
		 * DefaultConfigurationProvider deProvider =
		 * applicationContext.getBean(DefaultConfigurationProvider.class);
		 * System.out.println(deProvider.toString()); for (String lob :
		 * "ITCR,Vistaar,Perfetti_Rural".split(",")) { try { ServiceConfigurationImpl
		 * config = (ServiceConfigurationImpl)deProvider.getConfig(lob);
		 * System.out.println(config.toString());
		 * System.out.println(config.getEmail().toString());
		 * System.out.println(config.getNotification().toString());
		 * System.out.println(config.getWhatsapp().toString()); } catch (Exception e) {
		 * e.printStackTrace(); }
		 * 
		 * }
		 */
	}

}
