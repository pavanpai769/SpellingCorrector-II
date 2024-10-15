package com.spellcorrector.model;


/**
 * The Trie class implements the ITrie interface and utilizes the basic concepts of trie data structures.
 * It provides methods to insert words, search for words, and retrieve all the words stored in the Trie.
 * <p>
 * The insert method takes a word as a parameter that needs to be inserted. It first checks for null or
 * empty conditions, then calculates the index for each character. If the child node at the index is null,
 * it initializes a new node and moves to that index. Finally, it marks the last node as the end of the word.
 * <p>
 * The search method takes a word as a parameter and calculates the index for each character. If the
 * index of the child node is null, it returns false; otherwise, it moves to the next index. At the end,
 * it returns a boolean value indicating whether the node is marked as the end of a word.
 * <p>
 * The getAllWords method uses a recursive approach to traverse all the nodes. It generates words based
 * on the indices and adds each word to the list if the node is found to be the end of a word.
 */


import java.util.ArrayList;
import java.util.List;

public class Trie implements ITrie {

    private final ITrieNode root;

    public Trie() {
        root = new TrieNode();
    }


    @Override
    public void insert(String word) {
        ITrieNode temp = root;
        if (word == null || word.isEmpty()) {
            return;
        }
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (temp.getChildren()[idx] == null) {
                temp.getChildren()[idx] = new TrieNode();
            }
            temp = temp.getChildren()[idx];
        }
        temp.setIsWord(true);
    }

    @Override
    public boolean search(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }
        ITrieNode temp = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (temp.getChildren()[idx] == null) return false;
            temp = temp.getChildren()[idx];
        }

        return temp.isWord();
    }

    @Override
    public List<String> getAllWords() {
        List<String> wordList = new ArrayList<>();
        getAllWords(root, "", wordList);
        return wordList;
    }


    private void getAllWords(ITrieNode node, String word, List<String> wordList) {
        if (node == null) {
            return;
        }
        if (node.isWord()) {
            wordList.add(word);
        }

        for (int i = 0; i < 26; i++) {
            TrieNode childNode = node.getChildren()[i];
            if (childNode != null) {
                getAllWords(childNode, word + (char) ('a' + i), wordList);
            }
        }
    }

}
