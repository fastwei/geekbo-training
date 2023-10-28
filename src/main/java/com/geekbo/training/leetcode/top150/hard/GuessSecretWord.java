package com.geekbo.training.leetcode.top150.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Master {
    private String secret;

    public Master(String secret) {
        this.secret = secret;
    }

    public int guess(String word) {
        int matches = 0;
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == word.charAt(i)) {
                matches++;
            }
        }
        return matches;
    }
}

/**
 *
 * 还有问题，以后再修改
 *
 */
public class GuessSecretWord {
    public static void findSecretWord(String[] words, Master master) {
        List<String> possibleWords = new ArrayList<>(Arrays.asList(words));

        for (int i = 0; i < 10; i++) {
            String guessWord = getCandidateWord(possibleWords);
            int matches = master.guess(guessWord);

            List<String> nextPossibleWords = new ArrayList<>();
            for (String word : possibleWords) {
                if (countMatches(word, guessWord) == matches) {
                    nextPossibleWords.add(word);
                }
            }

            possibleWords = nextPossibleWords;

            if (matches == 6) {
                System.out.println("You guessed the secret word correctly.");
                return;
            }
        }

        System.out.println("Either you took too many guesses, or you did not find the secret word.");
    }

    private static String getCandidateWord(List<String> words) {
        int[] count = new int[26];
        int maxCount = 0;
        String candidate = "";

        for (String word : words) {
            for (char ch : word.toCharArray()) {
                count[ch - 'a']++;
            }
        }

        for (String word : words) {
            int wordScore = 0;
            for (char ch : word.toCharArray()) {
                wordScore += count[ch - 'a'];
            }

            if (wordScore > maxCount) {
                maxCount = wordScore;
                candidate = word;
            }
        }

        return candidate;
    }

    private static int countMatches(String word1, String word2) {
        int matches = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) == word2.charAt(i)) {
                matches++;
            }
        }
        return matches;
    }

    public static void main(String[] args) {
        String secret = "acckzz";
        String[] words = {"acckzz","ccbazz","eiowzz","abcczz"};
        int allowedGuesses = 10;

        Master master = new Master(secret);
        findSecretWord(words, master);
    }
}