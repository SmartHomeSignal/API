package ru.kpfu.itis.smarthome.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

	private final TokenService tokenService;
	private final FirebaseMessaging firebaseMessaging;

	@SneakyThrows
	public void sendNotifications(boolean state) {
		List<String> registrationTokens = tokenService.getAll();

		if (registrationTokens.isEmpty()) {
			log.info("No one token exist");
			return;
		}

		MulticastMessage message = MulticastMessage.builder()
				.setNotification(createNotification(state))
				.addAllTokens(registrationTokens)
				.build();
		firebaseMessaging.sendMulticast(message);
	}

	private Notification createNotification(boolean state) {
		return Notification.builder()
				.setTitle("Умный дом")
				.setBody("Сигнализация " + (state ? "включена" : "выключена"))
				.build();
	}
}
