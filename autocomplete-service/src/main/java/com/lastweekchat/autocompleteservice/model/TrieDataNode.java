package com.lastweekchat.autocompleteservice.model;

import java.util.ArrayList;
import java.util.List;

public class TrieDataNode {

    private char data;
    private List<TrieDataNode> nextNodes = new ArrayList<>();

    /**
     * Initializes a TrieDataNode with its data
     * @param data The data in this node
     */
    public TrieDataNode(char data) {
        this.data = data;
    }

    public char getData() {
        return data;
    }

    @Override
    public String toString() {
        return Character.toString(data);
    }


    public List<TrieDataNode> getNextNodes() {
        return nextNodes;
    }

    public void addNode(TrieDataNode node) {
        nextNodes.add(node);
    }

}
