package com.linguo.sharecenter.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BookCleanUpService {
	@Value("${book.cleanup.before.days}")
	private String refreshToken;

	public void cleanup() {

	}
}
