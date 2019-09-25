package com.applicate.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

//@Qualifier("cpi")
//@Configuration
@Component
public class ConfigurationProviderImpl  implements DefaultConfigurationProvider {
   JSONObject allConfig=null;
   ConfigurationProviderImpl(){
	   System.out.println("Inside Constructor");
   }
	//@Override
	public JSONObject getConfiguration() {
		 JSONParser parser = new JSONParser();
		 try {
			JSONObject obj = (JSONObject) parser.parse(new FileReader("c:\\file.json"));
			allConfig=(JSONObject) obj;
			System.out.println("inside get configuration");
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return allConfig;
	}

}
