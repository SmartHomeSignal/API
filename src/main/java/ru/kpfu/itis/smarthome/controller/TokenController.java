package ru.kpfu.itis.smarthome.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.kpfu.itis.smarthome.service.TokenService;

@RestController
@RequestMapping("/api/tokens")
@RequiredArgsConstructor
public class TokenController {

	private final TokenService tokenService;

	@PostMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveToken(@PathVariable String id) {
		tokenService.save(id);
	}
}
