package com.spellcorrector.model;

/**
 * The SpellCorrector class implements the ISpellCorrector interface and provides methods for spelling correction.
 * <p>
 * It uses a Trie data structure as a dictionary to hold the words.
 * <p>
 * The addDictionary method takes a file path as a parameter and loads the file. A Scanner object scans the
 * entire file, splitting each line into words based on spaces or commas, and then adds each word to the Trie.
 * If any error occurs during file reading, it will throw an IOException.
 * <p>
 * The suggestSimilarWords function takes a word as a parameter. First, it checks for null or empty conditions
 * and searches the dictionary for the word. If found, it returns the word as a list. If not found, it retrieves
 * all words from the Trie as a List of Strings, calculates the edit distance for each, and returns a list of
 * words based on the minimum edit distance.
 * <p>
 * The getMinEditDistance method takes two words as parameters and uses the editDistance method as a helper
 * to calculate the minimum edit distance by replacing, inserting, or deleting a character.
 */


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SpellCorrector implements ISpellCorrector {
    ITrie dictionary;

    public SpellCorrector() {
        this.dictionary = new Trie();
    }

    @Override
    public void addDictionary(String dictionaryPath) throws IOException {
        File dictionaryFile = new File(dictionaryPath);

        try (Scanner sc = new Scanner(dictionaryFile)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (!line.contains(" ")) {
                    dictionary.insert(line);
                } else {
                    String[] words = line.split("[ ,]+");
                    for (String word : words) {
                        dictionary.insert(word);
                    }
                }
            }

        } catch (IOException e) {
            throw new IOException("Failed to read from the file at: " + dictionaryPath);
        }

    }

    @Override
    public List<String> suggestSimilarWords(String word) {
        List<String> similarWords = new ArrayList<>();

        if (word == null || word.isEmpty()) return similarWords;

        word = word.toLowerCase();

        if (dictionary.search(word)) {
            similarWords.add(word);
            return similarWords;
        }

        List<String> wordList = dictionary.getAllWords();
        int minEditDist = Integer.MAX_VALUE;
        for (String wordInList : wordList) {

            int editDist = getMinEditDistance(word, wordInList);

            if (editDist < minEditDist) {
                similarWords.clear();
                similarWords.add(wordInList);
                minEditDist = editDist;

            } else if (editDist == minEditDist) {

                similarWords.add(wordInList);
            }
        }

        return similarWords;
    }

    private int getMinEditDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()][word2.length()];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return editDistance(word1, word2, 0, 0, dp);
    }

    private int editDistance(String word1, String word2, int idx1, int idx2, int[][] dp) {

        if (word1.isEmpty()) return word2.length();

        if (word2.isEmpty()) return word1.length();

        if (idx1 == word1.length()) return word2.length() - idx2;

        if (idx2 == word2.length()) return word1.length() - idx1;

        if (idx1 >= word1.length() || idx2 >= word2.length()) return Integer.MAX_VALUE - 90000;

        if (dp[idx1][idx2] != -1) return dp[idx1][idx2];

        char c1 = word1.charAt(idx1);
        char c2 = word2.charAt(idx2);

        if (idx1 == word1.length() - 1 && idx2 == word2.length() - 1) {
            if (c1 == c2) return 0;
            return 1;
        }

        if (c1 == c2) return editDistance(word1, word2, idx1 + 1, idx2 + 1, dp);

        int insert = editDistance(word1, word2, idx1, idx2 + 1, dp) + 1;
        int delete = editDistance(word1, word2, idx1 + 1, idx2, dp) + 1;
        int update = editDistance(word1, word2, idx1 + 1, idx2 + 1, dp) + 1;

        return Math.min(insert, Math.min(delete, update));

    }
}
