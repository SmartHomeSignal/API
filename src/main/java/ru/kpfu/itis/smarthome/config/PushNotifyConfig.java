package ru.kpfu.itis.smarthome.config;

import lombok.Data;

@Data
public class PushNotifyConfig {
	private String title;
	private String body;
	private String icon;
	private String click_action;
	private String ttlInSeconds;
}
