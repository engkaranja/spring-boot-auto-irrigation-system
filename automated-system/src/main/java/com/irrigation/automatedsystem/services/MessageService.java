package com.irrigation.automatedsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irrigation.automatedsystem.models.Message;
import com.irrigation.automatedsystem.repositories.MessageRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	public Message saveMessage(Message message) {
		return messageRepository.save(message);
	}

}
