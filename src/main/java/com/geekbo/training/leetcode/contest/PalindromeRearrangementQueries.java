package com.geekbo.training.leetcode.contest;

/**
 *
 * 378-4
 *
 */
public class PalindromeRearrangementQueries {

    public boolean[] canMakePalindromeQueries(String s, int[][] queries) {
        int n = s.length();
        int[][] charCounts = new int[n + 1][26]; // Prefix sums of character counts

        // Populate the charCounts array with prefix sums
        for (int i = 0; i < n; i++) {
            charCounts[i + 1] = charCounts[i].clone();
            charCounts[i + 1][s.charAt(i) - 'a']++;
        }

        boolean[] answers = new boolean[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int[] freq = new int[26]; // Frequency differences for each character
            int[] query = queries[i];
            int ai = query[0], bi = query[1], ci = query[2], di = query[3];

            // Calculate frequency differences
            for (int j = 0; j < 26; j++) {
                int leftFreq = charCounts[bi + 1][j] - charCounts[ai][j];
                int rightFreq = charCounts[di + 1][j] - charCounts[ci][j];
                freq[j] = (charCounts[n / 2][j] - charCounts[0][j] - leftFreq)
                        - (charCounts[n][j] - charCounts[n / 2][j] - rightFreq);
            }

            // Check if these differences allow for a palindrome
            answers[i] = canFormPalindrome(freq);
        }

        return answers;
    }

    private boolean canFormPalindrome(int[] freq) {
        int oddCount = 0;
        for (int f : freq) {
            if (Math.abs(f) % 2 == 1) {
                oddCount++;
            }
        }
        return oddCount <= 2;
    }

    // public static void main(String[] args) {
    //     Solution solution = new Solution();
    //     System.out.println(Arrays.toString(solution.canMakePalindromeQueries("abcabc", new int[][]{{1, 1, 3, 5}, {0, 2, 5, 5}}))); // Output: [true, true]
    //     System.out.println(Arrays.toString(solution.canMakePalindromeQueries("abbcdecbba", new int[][]{{0, 2, 7, 9}})));           // Output: [false]
    //     System.out.println(Arrays.toString(solution.canMakePalindromeQueries("acbcab", new int[][]{{1, 2, 4, 5}})));               // Output: [true]
    // }
}
