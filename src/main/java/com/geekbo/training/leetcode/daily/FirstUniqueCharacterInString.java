package com.geekbo.training.leetcode.daily;

/**
 * 387. First Unique Character in a String
 * Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "leetcode"
 * Output: 0
 * Example 2:
 * <p>
 * Input: s = "loveleetcode"
 * Output: 2
 * Example 3:
 * <p>
 * Input: s = "aabb"
 * Output: -1
 */
public class FirstUniqueCharacterInString {
    public int firstUniqChar(String s) {
        int[] count = new int[26]; // Array to store the count of each character
        // Count the occurrence of each character
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        // Find the index of the first non-repeating character
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1; // No non-repeating character found
    }

    public static void main(String[] args) {
        FirstUniqueCharacterInString solution = new FirstUniqueCharacterInString();

        // Test case 1
        String s1 = "leetcode";
        int result1 = solution.firstUniqChar(s1);
        System.out.println("Test Case 1:");
        System.out.println("Expected: 0");
        System.out.println("Actual: " + result1);

        // Test case 2
        String s2 = "loveleetcode";
        int result2 = solution.firstUniqChar(s2);
        System.out.println("Test Case 2:");
        System.out.println("Expected: 2");
        System.out.println("Actual: " + result2);

        // Test case 3
        String s3 = "aabb";
        int result3 = solution.firstUniqChar(s3);
        System.out.println("Test Case 3:");
        System.out.println("Expected: -1");
        System.out.println("Actual: " + result3);
    }
}