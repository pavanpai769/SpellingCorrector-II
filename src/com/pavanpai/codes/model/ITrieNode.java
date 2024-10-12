package com.pavanpai.codes.model;

public interface ITrieNode {
    TrieNode[] getChildren();
    boolean isWord();
    void setIsWord(boolean isWord);
}
