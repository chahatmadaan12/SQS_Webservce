package com.applicate.utils.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="prototype")
public class ServiceConfigurationImpl implements DefaultServiceConfiguration {
    @Autowired
	Notification notification;
	@Autowired
    Email email;
	@Autowired
	WhatsApp whatsapp;

	public Notification getNotf() {
		return notification;
	}

	public void setNotf(Notification notification) {
		this.notification = notification;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public WhatsApp getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(WhatsApp whatsapp) {
		this.whatsapp = whatsapp;
	}

}
