package com.geekbo.training.leetcode.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 93. Restore IP Addresses
 * A valid IP address consists of exactly four integers separated by single dots.
 * Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.
 * <p>
 * For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses,
 * but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 * Given a string s containing only digits, return all possible valid IP addresses
 * that can be formed by inserting dots into s.
 * You are not allowed to reorder or remove any digits in s.
 * You may return the valid IP addresses in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "25525511135"
 * Output: ["255.255.11.135","255.255.111.35"]
 * Example 2:
 * <p>
 * Input: s = "0000"
 * Output: ["0.0.0.0"]
 * Example 3:
 * <p>
 * Input: s = "101023"
 * Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 20
 * s consists of digits only.
 */
public class RestoreIPAddresses {
    /**
     * To solve this problem, we can use a backtracking algorithm.
     * We start by selecting the first dot position,
     * then recursively select the next dot positions until we have four dot positions.
     * At each step, we check if the substring between the current and previous dot positions is a valid IP segment.
     * If it is, we continue with the next dot position.
     * If not, we backtrack and try a different dot position.
     *
     * @param s
     * @return
     */
    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s, 0, 0, new StringBuilder(), result);
        return result;
    }

    private static void backtrack(String s, int start, int dotCount, StringBuilder sb, List<String> result) {
        if (start >= s.length() || dotCount >= 4) {
            if (start == s.length() && dotCount == 4) {
                result.add(sb.toString());
            }
            return;
        }

        int maxLength = (s.charAt(start) == '0') ? 1 : Math.min(3, s.length() - start);
        for (int len = 1; len <= maxLength; len++) {
            String segment = s.substring(start, start + len);
            if (Integer.parseInt(segment) <= 255) {
                int sbLen = sb.length();
                if (sbLen != 0) {
                    sb.append(".");
                }
                sb.append(segment);
                backtrack(s, start + len, dotCount + 1, sb, result);
                sb.setLength(sbLen);
            }
        }
    }

    public static List<String> restoreIpAddresses2(String s) {
        List<String> result = new ArrayList<>();

        // Iterate through all possible combinations of dot positions
        for (int i = 1; i < 4 && i < s.length() - 2; i++) {
            for (int j = i + 1; j < i + 4 && j < s.length() - 1; j++) {
                for (int k = j + 1; k < j + 4 && k < s.length(); k++) {
                    String segment1 = s.substring(0, i);
                    String segment2 = s.substring(i, j);
                    String segment3 = s.substring(j, k);
                    String segment4 = s.substring(k);

                    // Check if each segment is a valid IP segment
                    if (isValidSegment(segment1) && isValidSegment(segment2) &&
                            isValidSegment(segment3) && isValidSegment(segment4)) {
                        result.add(segment1 + "." + segment2 + "." + segment3 + "." + segment4);
                    }
                }
            }
        }

        return result;
    }

    private static boolean isValidSegment(String segment) {
        if (segment.length() > 3 || segment.isEmpty() || (segment.charAt(0) == '0' && segment.length() > 1) ||
                Integer.parseInt(segment) > 255) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // Test Case 1
        String s1 = "25525511135";
        // Expected output: ["255.255.11.135","255.255.111.35"]
        System.out.println(restoreIpAddresses(s1));

        // Test Case 2
        String s2 = "0000";
        // Expected output: ["0.0.0.0"]
        System.out.println(restoreIpAddresses(s2));

        // Test Case 3
        String s3 = "101023";
        // Expected output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
        System.out.println(restoreIpAddresses(s3));
    }
}