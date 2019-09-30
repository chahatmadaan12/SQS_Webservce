package com.applicate.utils.configuration;


import static com.applicate.utils.configurationProvider.ConfigurationConstants.*;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="prototype")
public class Email {
	
	private String userName;
	
	private String password;
	
	public Email() {
	}
    
	public Email(Object emailObj) {
		if(emailObj instanceof JSONObject) {
			JSONObject json = (JSONObject)emailObj;
			this.userName = json.getString(USERNAME);
			this.password = json.getString(PASSWORD);
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Email [userName=" + userName + ", password=" + password + "]";
	}

}
