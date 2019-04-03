package com.lastweekchat.chatservice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class Message {

    @GeneratedValue
    @Id
    Long id;

    private String message;
    private LocalDate timeStamp;
    private String userName;
    private String gif;

    public Message(){
        this.timeStamp = LocalDate.now();
    }

    public Message(String message, String userName,String gif) {
        this.message = message;
        this.userName = userName;
        this.timeStamp = LocalDate.now();
        this.gif = gif;
    }
}
