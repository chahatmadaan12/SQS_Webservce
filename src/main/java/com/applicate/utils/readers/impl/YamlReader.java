package com.applicate.utils.readers.impl;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.applicate.utils.FileUtils;
import com.applicate.utils.readers.FileReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

@Component
public class YamlReader implements FileReader {

	private static ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());

	private static ObjectMapper jsonWriter = new ObjectMapper();

	@Override
	public Object get(String filePath) throws Throwable {
		String extention = FileUtils.getFileExtention(filePath);
		if (!"yaml".equalsIgnoreCase(extention)) {
			throw new Throwable("Invalid json file extention");
		}
		Object obj = yamlReader.readValue(new java.io.FileReader(filePath), Object.class);
		String yamlInString = jsonWriter.writeValueAsString(obj);
		return new JSONObject(yamlInString);
	}

}
