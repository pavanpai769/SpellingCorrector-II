package com.spellcorrector.model;

public interface ITrieNode {
    TrieNode[] getChildren();

    boolean isWord();

    void setIsWord(boolean isWord);
}
