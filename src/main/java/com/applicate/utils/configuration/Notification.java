package com.applicate.utils.configuration;

import static com.applicate.utils.configurationProvider.ConfigurationConstants.FCM;

import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
@Scope(value="prototype")
public class Notification {
	
	private String fcm;
	
	public Notification() {
	}
	
	public Notification(Object notiObj) {
		if(notiObj instanceof JSONObject) {
			JSONObject json = (JSONObject)notiObj;
			this.fcm = json.getString(FCM);
		}
	}

	public String getFcm() {
		return fcm;
	}

	public void setFcm(String fcm) {
		this.fcm = fcm;
	}

	@Override
	public String toString() {
		return "Notification [fcm=" + fcm + "]";
	}

}
