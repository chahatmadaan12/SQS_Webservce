package com.applicate.service.contentgenerator;

import java.io.InputStream;

import org.json.JSONObject;
public interface ContentGenerator {
	
	InputStream generate(JSONObject payLoad);
	
	
}
