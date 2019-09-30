package com.applicate.utils.readers.impl;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.applicate.utils.FileUtils;
import com.applicate.utils.readers.FileReader;
@Component
public class PropertyReader implements FileReader<Properties>{

	@Override
	public Properties get(String filePath) throws Throwable {
		String extention = FileUtils.getFileExtention(filePath);
		if(!"properties".equalsIgnoreCase(extention)) {
		   throw new Throwable("Invalid properties file extention");
		}
		Properties properties = new Properties();
		properties.load(new java.io.FileReader(filePath));
		return properties;
	}

}
