package com.applicate.utils.readers;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.applicate.utils.FileUtils;

@Component
public class JsonReader implements FileReader{
	
	private static JSONParser parser = new JSONParser();
	
	@Override
	public JSONObject get(String filePath) throws Throwable {
		String extention = FileUtils.getFileExtention(filePath);
		if(!"json".equalsIgnoreCase(extention)) {
		   throw new Throwable("Invalid json file extention");
		}
		return new JSONObject(parser.parse(new java.io.FileReader(filePath)).toString());
	}

}
