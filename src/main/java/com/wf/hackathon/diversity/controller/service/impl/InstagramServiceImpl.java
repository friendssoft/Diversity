package com.wf.hackathon.diversity.controller.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.wf.hackathon.diversity.controller.service.InstagramService;

@Component
public class InstagramServiceImpl implements InstagramService{
	
	public static final String MEDIAL_URL = "https://graph.instagram.com/me/media";
	public static final String TOKEN = "IGQVJYYTFSWUtXR2JJcXJzdHQ0TTVaOUdpUnhtWnMyYzlWeTI5am9zWnNPbW5QRDRnVVB3VDdManRqSHJzLWp3eUtMSlhQX2RUSTJFczdzX0tiLXNhQmtQWUZAPUW5MR0FDdXhLZAkZAOWHA0OEgzTkp1VwZDZD";
	

	@Autowired
	RestTemplate restTemplate;
	
	public ResponseEntity<String> getUserDetailsByUserName(String userName) {
		String url = "https://www.instagram.com/"+userName+"/?__a=1";
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
		return responseEntity;
	}
	
	public ResponseEntity<String> getMediaDetails() {
		String url = MEDIAL_URL+"?fields=id,media_type,media_url,caption&access_token="+TOKEN;
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
		return responseEntity;
	}
}
