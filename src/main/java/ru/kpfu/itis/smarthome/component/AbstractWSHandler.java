package ru.kpfu.itis.smarthome.component;

import lombok.SneakyThrows;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public abstract class AbstractWSHandler extends TextWebSocketHandler {
	private Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		sessions.add(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) {
		boolean state = Boolean.parseBoolean(message.getPayload());
		handleMessage(state);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		sessions.remove(session);
	}

	public abstract void handleMessage(boolean state);

	@SneakyThrows
	public void sendToAll(boolean state) {
		sessions.forEach(session -> send(state, session));
	}

	@SneakyThrows
	public void send(boolean state, WebSocketSession session) {
		session.sendMessage(new TextMessage(String.valueOf(state)));
	}
}
