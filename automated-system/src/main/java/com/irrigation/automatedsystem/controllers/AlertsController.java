package com.irrigation.automatedsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irrigation.automatedsystem.models.Message;
import com.irrigation.automatedsystem.services.MessageService;

@RestController
@RequestMapping("/alerts")
public class AlertsController {
	
	@Autowired
	private MessageService messageService;
	
	@PostMapping("/send")
	public String sendAlert(@RequestBody Message message) {
		messageService.saveMessage(message);
		
		return "Alert Sent ok";
	}

}
