package com.irrigation.automatedsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irrigation.automatedsystem.models.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

}
