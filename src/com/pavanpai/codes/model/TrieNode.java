package com.pavanpai.codes.model;

public class TrieNode implements ITrieNode{
    private final TrieNode[] children;
    private boolean isWord;

    public TrieNode(){
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
