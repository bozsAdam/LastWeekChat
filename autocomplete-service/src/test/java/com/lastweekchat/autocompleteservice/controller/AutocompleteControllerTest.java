package com.lastweekchat.autocompleteservice.controller;

import com.lastweekchat.autocompleteservice.service.AutocompleteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AutocompleteControllerTest {

    private AutocompleteService autocompleteService;

    @BeforeEach
    public void getAutocompleteService() throws IOException {

        autocompleteService = new AutocompleteService("assets/wordlist.txt");

    }

    @Test
    public void autoCompleteReturnListOfStrings() {

        List<String> results = autocompleteService.autoComplete("spectro");
        List<String> expected = Arrays.asList("spectrogram", "spectrograph", "spectrographic",
                "spectrographically", "spectrometric", "spectrophotometer", "spectroscope",
                "spectroscopic", "spectroscopy");
        assertIterableEquals(expected, results);

    }

    @Test
    public void autoCompleteReturnsEmptyList() {

        List<String> expected = Arrays.asList();
        assertEquals(expected, autocompleteService.autoComplete("sfsdf"));

    }

}
