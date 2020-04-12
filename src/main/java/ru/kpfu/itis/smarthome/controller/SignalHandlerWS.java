package ru.kpfu.itis.smarthome.controller;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import ru.kpfu.itis.smarthome.service.NotificationService;

@Component
@RequiredArgsConstructor
public class SignalHandlerWS extends TextWebSocketHandler {

	private final NotificationService notificationService;
	private AtomicBoolean atomicState = new AtomicBoolean(false);

	@MessageMapping("/raspberry/signal")
	@SendTo("/devices/signal")
	public boolean sendSignalStateToDevices(boolean state) {
		atomicState.set(state);
		notificationService.sendNotifications(state);
		return state;
	}

	@MessageMapping("/devices/signal")
	@SendTo("/raspberry/signal")
	public boolean sendSignalStateToRaspberry(boolean state) {
		atomicState.set(state);
		return state;
	}

}
