package com.applicate.utils.configuration;

import static com.applicate.utils.configurationProvider.ConfigurationConstants.APIKEY;

import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="prototype")
public class WhatsApp {

	private String apiKey;
	
	public WhatsApp() {
	}

	public WhatsApp(Object whatsAppObj) {
		if(whatsAppObj instanceof JSONObject) {
			JSONObject json = (JSONObject)whatsAppObj;
			this.apiKey = json.getString(APIKEY);
		}
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public String toString() {
		return "WhatsApp [apiKey=" + apiKey + "]";
	}

}
