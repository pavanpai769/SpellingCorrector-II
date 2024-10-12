package com.pavanpai.codes.model;

import java.util.ArrayList;
import java.util.List;

public class Trie implements ITrie {

    private final ITrieNode root;

    public Trie(){
        root = new TrieNode();
    }


    @Override
    public void insert(String word){
        ITrieNode temp = root;
        if(word==null || word.isEmpty()){
            return;
        }
        for(char c: word.toCharArray()){
            int idx = c-'a';
            if(temp.getChildren()[idx]==null){
                temp.getChildren()[idx]=new TrieNode();
            }
            temp=temp.getChildren()[idx];
        }
        temp.setIsWord(true);
    }

    @Override
    public boolean search(String word){
        if(word==null || word.isEmpty()){
            return false;
        }
        ITrieNode temp = root;
        for(char c: word.toCharArray()) {
            int idx = c - 'a';
            if (temp.getChildren()[idx] == null) return false;
            temp=temp.getChildren()[idx];
        }

        return temp.isWord();
    }

    @Override
    public List<String> getAllWords() {
        List<String> wordList = new ArrayList<>();
        getAllWords(root,"",wordList);
        return wordList;
    }


    private void getAllWords(ITrieNode node, String word, List<String> wordList) {
        if(node==null){
            return;
        }
        if(node.isWord()){
            wordList.add(word);
        }

        for(int i=0;i<26;i++){
            TrieNode childNode = node.getChildren()[i];
            if(childNode!=null){
                getAllWords(childNode,word+(char)('a'+i),wordList);
            }
        }
    }

}
