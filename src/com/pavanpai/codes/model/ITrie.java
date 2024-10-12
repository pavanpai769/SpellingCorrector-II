package com.pavanpai.codes.model;

import java.util.List;

public interface ITrie {
    void insert(String word);
    boolean search(String word);
    List<String> getAllWords();
}
