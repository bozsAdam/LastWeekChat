package com.lastweekchat.chatservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.lastweekchat.chatservice.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
