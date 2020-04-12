package ru.kpfu.itis.smarthome.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class ClientWSHandler extends AbstractWSHandler {

	@Autowired
	private DeviceWSHandler deviceWSHandler;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		super.afterConnectionEstablished(session);
		boolean state = deviceWSHandler.getDeviceState();
		send(state, session);
	}

	@Override
	public void handleMessage(boolean state) {
		deviceWSHandler.sendToAll(state);
	}
}
