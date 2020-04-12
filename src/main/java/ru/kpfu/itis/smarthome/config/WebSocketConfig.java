package ru.kpfu.itis.smarthome.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import ru.kpfu.itis.smarthome.component.ClientWSHandler;
import ru.kpfu.itis.smarthome.component.DeviceWSHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Autowired
	private ClientWSHandler clientWSHandler;
	@Autowired
	private DeviceWSHandler deviceWSHandler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(clientWSHandler, "/clients/signal")
				.addHandler(deviceWSHandler, "/devices/signal");
	}
}
