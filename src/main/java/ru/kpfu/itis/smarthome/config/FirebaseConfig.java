package ru.kpfu.itis.smarthome.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class FirebaseConfig {

	private final ResourceLoader resourceLoader;

	@Bean
	public FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
		return FirebaseMessaging.getInstance(firebaseApp);
	}

	@Bean
	@SneakyThrows
	public FirebaseApp firebaseApp(FcmSettings settings) {
		Resource resource = resourceLoader.getResource(settings.getServiceAccountFile());
		try (InputStream serviceAccount = resource.getInputStream()) {
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();

			return FirebaseApp.initializeApp(options);
		}
	}
}
