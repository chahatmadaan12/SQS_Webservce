package com.applicate.utils.configurationProvider;

import com.applicate.utils.configuration.DefaultServiceConfiguration;

public interface ConfigurationProvider {
	
	DefaultServiceConfiguration getConfig(String lob) throws Exception;
	
	boolean reLoad();

}
