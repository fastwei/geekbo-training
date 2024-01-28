package com.geekbo.training.leetcode.daily;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * 1704. Determine if String Halves Are Alike
 * Solved
 * Easy
 * You are given a string s of even length. Split this string into two halves of equal lengths, and let a be the first half and b be the second half.
 *
 * Two strings are alike if they have the same number of vowels ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'). Notice that s contains uppercase and lowercase letters.
 *
 * Return true if a and b are alike. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "book"
 * Output: true
 * Explanation: a = "bo" and b = "ok". a has 1 vowel and b has 1 vowel. Therefore, they are alike.
 * Example 2:
 *
 * Input: s = "textbook"
 * Output: false
 * Explanation: a = "text" and b = "book". a has 1 vowel whereas b has 2. Therefore, they are not alike.
 * Notice that the vowel o is counted twice.
 *
 *
 * Constraints:
 *
 * 2 <= s.length <= 1000
 * s.length is even.
 * s consists of uppercase and lowercase letters.
 *
 */
public class HalvesAreAlike {
    public static boolean halvesAreAlike(String s) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

        int length = s.length();
        int midPoint = length / 2;

        String firstHalf = s.substring(0, midPoint);
        String secondHalf = s.substring(midPoint);

        int countFirstHalf = countVowels(firstHalf, vowels);
        int countSecondHalf = countVowels(secondHalf, vowels);

        return countFirstHalf == countSecondHalf;
    }

    private static int countVowels(String str, Set<Character> vowels) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (vowels.contains(c)) {
                count++;
            }
        }
        return count;
    }
}