package com.lastweekchat.autocompleteservice.service;

import com.lastweekchat.autocompleteservice.model.TerminatingNode;
import com.lastweekchat.autocompleteservice.model.TrieDataNode;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static java.nio.file.Files.readAllLines;

@Service
public class AutocompleteService {


    private TrieDataNode root;
    private File file;

    /**
     * Starts a new Trie with dummy root data "-"
     */
    public AutocompleteService() throws IOException {

        root = new TrieDataNode('-');
        init();
    }

    public AutocompleteService(String filepath) throws IOException {

        root = new TrieDataNode('-');
        this.file = new File(filepath);
        Path worldListPath = file.toPath();
        List<String> wordList = Files.readAllLines(worldListPath);
        for (String word : wordList) {
            addWord(word);
        }


    }

    public void init() throws IOException {

        FileSystemResource resource = new FileSystemResource("autocomplete-service/assets/wordlist.txt");
        this.file = resource.getFile();
        Path worldListPath = file.toPath();
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

    public void checkForNewWords(String message) {

        message = message.replaceAll("/[^a-zA-Z0-9\\s]+/g", "");
        List<String> words = new ArrayList<>();
        words.addAll(Arrays.asList(message.split(" ")));
        Iterator iterator = words.iterator();
        while(iterator.hasNext()) {
            String next = (String)iterator.next();
            if (autoComplete(next).size() == 0) {

                saveWordToFile(next);

            }
        }

    }

    private void saveWordToFile(String word) {

        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
			bw.write(word + "\n");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

    }
}
