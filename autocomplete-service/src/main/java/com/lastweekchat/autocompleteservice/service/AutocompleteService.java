package com.lastweekchat.autocompleteservice.service;

import com.lastweekchat.autocompleteservice.model.TerminatingNode;
import com.lastweekchat.autocompleteservice.model.TrieDataNode;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readAllLines;

@Service
public class AutocompleteService {


    private TrieDataNode root;

    /**
     * Starts a new Trie with dummy root data "-"
     */
    public AutocompleteService() {
        root = new TrieDataNode('-');
    }

    public void init() throws IOException {
        Path worldListPath = new File("assets/wordlist.txt").toPath();
        List<String> wordList = readAllLines(worldListPath);
        for (String word : wordList) {
            addWord(word);
        }
    }

    /**
     * Adds a word to the Trie.
     */
    public void addWord(String wordToAdd) {
        char[] characters = wordToAdd.toCharArray();
        TrieDataNode currentNode = root;
        outerloop:
        for (int i = 0; i < characters.length; i++) {
            for (TrieDataNode node: currentNode.getNextNodes()) {
                if (Character.toLowerCase(characters[i]) == Character.toLowerCase(node.getData())) {
                    currentNode = node;
                    continue outerloop;
                }
            }
            TrieDataNode newNode = new TrieDataNode(characters[i]);
            currentNode.addNode(newNode);
            currentNode = newNode;
        }
        currentNode.addNode(new TerminatingNode());
    }

    /**
     * Returns the possible completions of baseChars String from the Trie.
     * @param baseChars The String to autocomplete.
     * @return possible completions. An empty list if there are none.
     */
    public List<String> autoComplete(String baseChars) {
        List<String> words = new ArrayList<>();
        TrieDataNode currentNode = root;
        char[] characters = baseChars.toCharArray();
        StringBuilder baseWord = new StringBuilder();
        outerloop:
        for (int i = 0; i < characters.length; i++) {
            for (TrieDataNode node: currentNode.getNextNodes()) {
                if (Character.toLowerCase(characters[i]) == Character.toLowerCase(node.getData())) {
                    baseWord.append(node.getData());
                    currentNode = node;
                    continue outerloop;
                }
            }
            return words;
        }
        for (TrieDataNode node : currentNode.getNextNodes()) {
            appendChar(words, node, baseWord.toString());
        }
        return words;
    }


    public void appendChar(List<String> words, TrieDataNode currentNode, String baseChars) {
            StringBuilder sb = new StringBuilder();
            sb.append(baseChars);
        if (!(currentNode instanceof TerminatingNode)) {
            sb.append(currentNode.getData());
            for (TrieDataNode node: currentNode.getNextNodes()) {
                appendChar(words, node, sb.toString());
            }
        } else {
            words.add(sb.toString());
        }


    }
}
