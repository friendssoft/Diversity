package com.wf.hackathon.diversity.controller.service;

import org.springframework.http.ResponseEntity;

public interface InstagramService {
	public ResponseEntity<String> getUserDetailsByUserName(String userName);
	public ResponseEntity<String> getMediaDetails(); 
}
