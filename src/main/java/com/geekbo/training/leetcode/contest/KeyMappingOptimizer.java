package com.geekbo.training.leetcode.contest;

import java.util.*;

public class KeyMappingOptimizer {

    public int minimumPushes(String word) {
        // The total number of pushes required.
        int totalPushes = 0;

        // There are 8 keys (2-9) that can be mapped to letters.
        int keyCount = 8;

        // Calculate the number of pushes for each letter.
        for (int i = 0; i < word.length(); i++) {
            // Determine the number of pushes for the current letter.
            // The first letter assigned to a key requires 1 push, the second letter 2 pushes, and so on.
            int pushesForLetter = (i / keyCount) + 1;

            // Add to the total number of pushes.
            totalPushes += pushesForLetter;
        }

        return totalPushes;
    }

    public static void main(String[] args) {
        KeyMappingOptimizer optimizer = new KeyMappingOptimizer();

        // 测试用例和预期
        String word1 = "abcde";
        System.out.println("Minimum pushes for 'abcde': " + optimizer.minimumPushes(word1)); // 预期 5

        String word2 = "xycdefghij";
        System.out.println("Minimum pushes for 'xycdefghij': " + optimizer.minimumPushes(word2)); // 预期 12
    }
}
