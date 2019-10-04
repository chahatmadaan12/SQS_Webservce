package com.applicate.utils.configurationProvider;

import static com.applicate.utils.configurationProvider.ConfigurationConstants.*;
import static com.applicate.utils.configurationProvider.ConfigurationConstants.EMAIL;
import static com.applicate.utils.configurationProvider.ConfigurationConstants.FCM;
import static com.applicate.utils.configurationProvider.ConfigurationConstants.NOTIFICATION;
import static com.applicate.utils.configurationProvider.ConfigurationConstants.PASSWORD;
import static com.applicate.utils.configurationProvider.ConfigurationConstants.USERNAME;
import static com.applicate.utils.configurationProvider.ConfigurationConstants.WHATSAPP;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.applicate.utils.configuration.Azure;
import com.applicate.utils.configuration.DefaultServiceConfiguration;
import com.applicate.utils.configuration.Email;
import com.applicate.utils.configuration.Notification;
import com.applicate.utils.configuration.ServiceConfigurationImpl;
import com.applicate.utils.configuration.WhatsApp;
import com.applicate.utils.readers.FileReader;

@Component
public class DefaultConfigurationProvider implements ConfigurationProvider {

	public static HashMap<String, DefaultServiceConfiguration> provider = new HashMap();

	@Autowired
//	@Qualifier(value = "${filereader}")
	@Qualifier("yamlReader")
	private FileReader fileReader;

	@Autowired
	private DefaultServiceConfiguration defConfig;

	@Autowired
	private ApplicationContext ctx;

	@Value("${configuration}")
	private String filePath;

	@PostConstruct
	private void load() {
		if (CollectionUtils.isEmpty(provider)) {
			try {
				Object config = fileReader.get(filePath);
				if (config instanceof JSONObject) {
					JSONObject json = (JSONObject) config;
					for (String lob : JSONObject.getNames(json)) {
						provider.put(lob, getDefaultServiceConfiguration(json.getJSONObject(lob)));
					}
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public DefaultServiceConfiguration getConfig(String lob) throws Exception {
		if (!provider.containsKey(lob)) {
			throw new Exception(lob + " not found");
		}
		return provider.get(lob);
	}

	private DefaultServiceConfiguration getDefaultServiceConfiguration(JSONObject configs) {
		if (defConfig instanceof ServiceConfigurationImpl) {
			return setServiceConfigurationImplVariables((ServiceConfigurationImpl) defConfig, configs);
		}
		return null;
	}

	private DefaultServiceConfiguration setServiceConfigurationImplVariables(ServiceConfigurationImpl bean,
			JSONObject configs) {
		if (configs != null)
			for (String injectedObjName : JSONObject.getNames(configs)) {
				switch (injectedObjName) {
				case NOTIFICATION:
					bean.setNotification(ctx.getBean(Notification.class, configs.getJSONObject(injectedObjName)));
					break;
				case EMAIL:
					bean.setEmail(ctx.getBean(Email.class, configs.getJSONObject(injectedObjName)));
					break;
				case WHATSAPP:
					bean.setWhatsapp(ctx.getBean(WhatsApp.class, configs.getJSONObject(injectedObjName)));
					break;
				case AZURE:
					bean.setAzure(ctx.getBean(Azure.class, configs.getJSONObject(injectedObjName)));
					break;
				default:
					break;
				}
			}
		return bean;
	}

	@Override
	public boolean reLoad() {
		provider = null;
		load();
		return provider != null;
	}

	@Override
	public String toString() {
		return "DefaultConfigurationProvider [fileReader=" + fileReader + ", filePath=" + filePath + "]" + provider;
	}

}
