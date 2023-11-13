package com.geekbo.training.leetcode.daily;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 2785. Sort Vowels in a String
 * Medium
 * Given a 0-indexed string s, permute s to get a new string t such that:
 * <p>
 * All consonants remain in their original places. More formally, if there is an index i with 0 <= i < s.length such that s[i] is a consonant, then t[i] = s[i].
 * The vowels must be sorted in the nondecreasing order of their ASCII values. More formally, for pairs of indices i, j with 0 <= i < j < s.length such that s[i] and s[j] are vowels, then t[i] must not have a higher ASCII value than t[j].
 * Return the resulting string.
 * <p>
 * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in lowercase or uppercase. Consonants comprise all letters that are not vowels.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "lEetcOde"
 * Output: "lEOtcede"
 * Explanation: 'E', 'O', and 'e' are the vowels in s; 'l', 't', 'c', and 'd' are all consonants. The vowels are sorted according to their ASCII values, and the consonants remain in the same places.
 * Example 2:
 * <p>
 * Input: s = "lYmpH"
 * Output: "lYmpH"
 * Explanation: There are no vowels in s (all characters in s are consonants), so we return "lYmpH".
 */
public class SortVowelsInString {

    public static String sortVowels(String inputString) {
        int length = inputString.length();
        ArrayList<Character> vowelsList = new ArrayList<>();
        ArrayList<Integer> vowelPositions = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            char currentChar = inputString.charAt(i);
            if (currentChar == 'a' || currentChar == 'e' || currentChar == 'i' ||
                    currentChar == 'o' || currentChar == 'u' || currentChar == 'A' ||
                    currentChar == 'E' || currentChar == 'I' || currentChar == 'O' ||
                    currentChar == 'U') {
                vowelsList.add(currentChar);
                vowelPositions.add(i);
            }
        }

        Collections.sort(vowelsList);

        char[] resultArray = inputString.toCharArray();
        for (int i = 0; i < vowelPositions.size(); i++) {
            resultArray[vowelPositions.get(i)] = vowelsList.get(i);
        }

        return new String(resultArray);
    }

    public static void main(String[] args) {
        // Test case 1
        String s1 = "lEetcOde";
        // 'E', 'O', and 'e' are the vowels in s1; 'l', 't', 'c', and 'd' are all consonants.
        // The vowels are sorted according to their ASCII values, and the consonants remain in the same places.
        String expected1 = "lEOtcede";
        String result1 = sortVowels(s1);
        System.out.println(result1.equals(expected1)); // Output: true

        // Test case 2
        String s2 = "lYmpH";
        // There are no vowels in s2 (all characters in s2 are consonants),
        // so the original string is returned.
        String expected2 = "lYmpH";
        String result2 = sortVowels(s2);
        System.out.println(result2.equals(expected2)); // Output: true
    }
}
