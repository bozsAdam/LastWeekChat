package com.lastweekchat.gifservice.controller;

import com.lastweekchat.gifservice.service.GifService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
public class GifController {

    @Autowired
    GifService gifService;

    @PostMapping("/gif-list")
    public List<String> listGifs(@RequestBody HashMap<String, String> body) throws IOException {
        System.out.println(body.get("keyword"));
        List<String> gifsToReturn = gifService.getGifList(body.get("keyword"));
        System.out.println(gifsToReturn);
        return gifsToReturn;

    }

}