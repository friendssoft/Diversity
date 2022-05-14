package com.wf.hackathon.diversity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wf.hackathon.diversity.controller.service.InstagramService;

@RestController
@RequestMapping("/instagram")
public class InstagramController {
	
	@Autowired
	InstagramService instagramService;

	@GetMapping("/user")
	public ResponseEntity<String> getUserDetails(@RequestParam("userName")String userName) {
		return instagramService.getUserDetailsByUserName(userName);
	}
	
	@GetMapping("/media")
	public ResponseEntity<String> getMediaDetails() {
		return instagramService.getMediaDetails();
	}
}
