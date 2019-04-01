package com.lastweekchat.chatservice.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Message {

    private String message;
    private LocalDate timeStamp;
    private String userName;

    public Message(){
        this.timeStamp = LocalDate.now();
    }

    public Message(String message, String userName) {
        this.message = message;
        this.userName = userName;
        this.timeStamp = LocalDate.now();
    }
}
