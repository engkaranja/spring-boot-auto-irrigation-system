package com.irrigation.automatedsystem.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irrigation.automatedsystem.models.Plot;

@RestController
@RequestMapping("/sensor")
public class SensorController {
	
	@PostMapping("/notify")
	public String notifySensor(@RequestBody Plot plot) {
		//dummy implementation of sensor endpoint
		
		return "Sensor Notified ok!";
	}

}
