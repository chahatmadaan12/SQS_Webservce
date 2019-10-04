package com.applicate.utils;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.applicate.utils.readers.FileReader;

@Component
public class VelocityManager {

	@Value("${resourcePath}")
	private String resourcePath;
	
	@Autowired
	@Qualifier("yamlReader")
	private FileReader fileReader;
	
	public String getBody(JSONObject json,String templateInfoKey,String lob){
		Velocity velocity = new Velocity();
		VelocityContext context = new VelocityContext();
		context.put("mapData", json);
		JSONObject templateInfo = getTemplateInfo(lob,templateInfoKey);
		velocity.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, templateInfo.getString("templatePath"));
		Template template = velocity.getTemplate(templateInfo.getString("templateName"));
		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		return writer.toString();
	}

	public JSONObject getTemplateInfo(String lob, String templateInfoKey) {
		try {
			JSONObject json = (JSONObject)fileReader.get(resourcePath+lob+"/constant.yaml");
			return json.getJSONObject(templateInfoKey);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

}
