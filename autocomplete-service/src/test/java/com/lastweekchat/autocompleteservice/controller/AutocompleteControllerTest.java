package com.lastweekchat.autocompleteservice.controller;

import com.lastweekchat.autocompleteservice.service.AutocompleteService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AutocompleteControllerTest {



    @Test
    public void autoCompleteReturnListOfStrings() throws IOException {

        AutocompleteService autocompleteService = new AutocompleteService();
        List<String> results = autocompleteService.autoComplete("spectro");
        List<String> expected = Arrays.asList("spectrogram", "spectrograph", "spectrographic",
                "spectrographically", "spectrometric", "spectrophotometer", "spectroscope",
                "spectroscopic", "spectroscopy");
        assertIterableEquals(expected, results);

    }

    @Test
    public void autoCompleteReturnsEmptyList() throws IOException {
        AutocompleteService autocompleteService = new AutocompleteService();
        List<String> expected = Arrays.asList();
        assertEquals(expected, autocompleteService.autoComplete("sfsdf"));
    }


}
