package com.lastweekchat.chatservice.controller;

import com.lastweekchat.chatservice.model.Message;
import com.lastweekchat.chatservice.repository.MessageRepository;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private MessageRepository messageRepository;


    @PostMapping("/message")
    public void message(@RequestBody Message message){
        log.info("saving the message: " + message );
        messageRepository.save(message);
        log.info("sending the message to the socket");
        simpMessagingTemplate.convertAndSend("/chat",message);
    }



}
