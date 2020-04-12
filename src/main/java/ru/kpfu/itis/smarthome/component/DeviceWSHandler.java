package ru.kpfu.itis.smarthome.component;

import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.kpfu.itis.smarthome.service.NotificationService;

@Component
public class DeviceWSHandler extends AbstractWSHandler {

	private AtomicBoolean deviceState = new AtomicBoolean(false);

	@Autowired
	private ClientWSHandler clientWSHandler;

	@Autowired
	private NotificationService notificationService;

	@Override
	public void handleMessage(boolean state) {
		deviceState.set(state);
		clientWSHandler.sendToAll(state);
		notificationService.sendNotifications(state);
	}

	public boolean getDeviceState() {
		return deviceState.get();
	}
}
