package com.applicate.utils.readers;

import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import com.applicate.utils.FileUtils;

import net.minidev.json.JSONObject;
@Component
public class JSONReader implements FileReader<JSONObject>{
	
	private static JSONParser parser = new JSONParser();
	
	@Override
	public JSONObject get(String filePath) throws Throwable {
		String extention = FileUtils.getFileExtention(filePath);
		if(!"json".equalsIgnoreCase(extention)) {
		   throw new Throwable("Invalid json file extention");
		}
		return (JSONObject) parser.parse(filePath);
	}

}
