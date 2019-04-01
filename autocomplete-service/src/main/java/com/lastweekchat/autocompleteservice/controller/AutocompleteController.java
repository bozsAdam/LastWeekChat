package com.lastweekchat.autocompleteservice.controller;

import com.lastweekchat.autocompleteservice.service.AutocompleteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class AutocompleteController {

    @Autowired
    AutocompleteService autocompleteService;

    @PostMapping("/autocomplete")
    public List<String> autcompleteWord(@RequestBody String word) throws IOException {

        autocompleteService.addWord("danale");
        List<String> wordsToReturn = autocompleteService.autoComplete(word);
        return wordsToReturn;

    }

}
