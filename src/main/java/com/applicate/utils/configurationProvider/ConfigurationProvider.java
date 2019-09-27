package com.applicate.utils.configurationProvider;

import java.util.HashMap;

import org.json.JSONObject;

import com.applicate.utils.configuration.DefaultServiceConfiguration;

public interface ConfigurationProvider {
	HashMap<String, DefaultServiceConfiguration> provider=new HashMap();
	DefaultServiceConfiguration getValue(String lob);

}
