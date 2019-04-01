package com.lastweekchat.serviceregistry.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
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
