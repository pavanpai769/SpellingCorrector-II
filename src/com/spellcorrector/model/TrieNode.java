package com.spellcorrector.model;

/**
 * The TrieNode class implements the ITrieNode interface. It contains two fields:
 * an array of TrieNodes of size 26, where each index represents a character from 'a' to 'z',
 * and a boolean field that indicates whether the node marks the end of a word.
 * It provides getter and setter methods to access and modify the fields.
 */


public class TrieNode implements ITrieNode {
    private final TrieNode[] children;
    private boolean isWord;

    public TrieNode() {
        this.children = new TrieNode[26];
        this.isWord = false;
    }

    @Override
    public TrieNode[] getChildren() {
        return this.children;
    }

    @Override
    public boolean isWord() {
        return this.isWord;
    }

    @Override
    public void setIsWord(boolean isWord) {
        this.isWord = isWord;
    }
}
