package com.applicate.utils.configuration;

import static com.applicate.utils.configurationProvider.ConfigurationConstants.ACCOUNTKEY;
import static com.applicate.utils.configurationProvider.ConfigurationConstants.ACCOUNTNAME;
import static com.applicate.utils.configurationProvider.ConfigurationConstants.BLOBURL;
import static com.applicate.utils.configurationProvider.ConfigurationConstants.CONTAINER;

import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class Azure {

	private String accountName;

	private String accountKey;

	private String blobUrl;

	private String container;

	public Azure() {
	}

	public Azure(Object azureObj) {
		if(azureObj instanceof JSONObject) {
			JSONObject json = (JSONObject)azureObj;
			this.accountName = json.getString(ACCOUNTNAME);
			this.accountKey = json.getString(ACCOUNTKEY);
			this.container = json.getString(CONTAINER);
			this.blobUrl = json.getString(BLOBURL);
		}
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountKey() {
		return accountKey;
	}

	public void setAccountKey(String accountKey) {
		this.accountKey = accountKey;
	}

	public String getBlobUrl() {
		return blobUrl;
	}

	public void setBlobUrl(String blobUrl) {
		this.blobUrl = blobUrl;
	}

	public String getContainer() {
		return container;
	}

	public void setContainer(String container) {
		this.container = container;
	}

	@Override
	public String toString() {
		return "Azure [accountName=" + accountName + ", accountKey=" + accountKey + ", blobUrl=" + blobUrl
				+ ", container=" + container + "]";
	}

}
