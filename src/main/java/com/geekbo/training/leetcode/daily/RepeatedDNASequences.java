package com.geekbo.training.leetcode.daily;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 187. Repeated DNA Sequences
 * Medium
 * The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
 * <p>
 * For example, "ACGAATTCCG" is a DNA sequence.
 * When studying DNA, it is useful to identify repeated sequences within the DNA.
 * <p>
 * Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule. You may return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * Output: ["AAAAACCCCC","CCCCCAAAAA"]
 * Example 2:
 * <p>
 * Input: s = "AAAAAAAAAAAAA"
 * Output: ["AAAAAAAAAA"]
 */
public class RepeatedDNASequences {
    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        Set<String> repeated = new HashSet<>();

        int n = s.length();
        for (int i = 0; i <= n - 10; i++) {
            String sequence = s.substring(i, i + 10);
            if (seen.contains(sequence)) {
                repeated.add(sequence);
            } else {
                seen.add(sequence);
            }
        }

        result.addAll(repeated);
        return result;
    }

    public static void main(String[] args) {
        // Example 1
        String s1 = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        // Expected output: ["AAAAACCCCC","CCCCCAAAAA"]
        System.out.println(findRepeatedDnaSequences(s1));

        // Example 2
        String s2 = "AAAAAAAAAAAAA";
        // Expected output: ["AAAAAAAAAA"]
        System.out.println(findRepeatedDnaSequences(s2));
    }
}