package ru.kpfu.itis.smarthome.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.stereotype.Service;

@Service
public class TokenService {

	private Set<String> tokenRepository = new CopyOnWriteArraySet<>();

	public void save(String id) {
		tokenRepository.add(id);
	}

	public List<String> getAll() {
		return new ArrayList<>(tokenRepository);
	}
}
