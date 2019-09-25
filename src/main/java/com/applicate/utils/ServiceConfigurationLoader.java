package com.applicate.utils;



import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;



@Component
@Scope(value="prototype")
public class ServiceConfigurationLoader {
	JSONObject notification;
	JSONObject email;
	JSONObject messages;
	JSONObject whatsapp;
	
	  @Autowired 
	  private ConfigurationProviderImpl configurationProviderImpl;
	  
	  //@Autowired ConfigurationProviderImpl cpi;
	 
	public Object getDetailsOfLOB(String lob) {
		JSONObject data=configurationProviderImpl.getConfiguration();
		JSONObject returnLobData=data.optJSONObject(lob);
		return returnLobData;
	}

	/*
	 * public void setterVari(JSONObject obj) {
	 * setNotification(obj.opt("notification"));;
	 * 
	 * }
	 */
	
	
	
    
}
