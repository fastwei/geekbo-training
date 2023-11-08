package com.geekbo.training.leetcode.daily;

/**
 * 165. Compare Version Numbers
 * Medium
 * Topics
 * Companies
 * Given two version numbers, version1 and version2, compare them.
 * <p>
 * Version numbers consist of one or more revisions joined by a dot '.'.
 * Each revision consists of digits and may contain leading zeros.
 * Every revision contains at least one character. Revisions are 0-indexed from left to right,
 * with the leftmost revision being revision 0, the next revision being revision 1, and so on.
 * For example 2.5.33 and 0.1 are valid version numbers.
 * <p>
 * To compare version numbers, compare their revisions in left-to-right order.
 * Revisions are compared using their integer value ignoring any leading zeros.
 * This means that revisions 1 and 001 are considered equal. If a version number
 * does not specify a revision at an index, then treat the revision as 0. For example,
 * version 1.0 is less than version 1.1 because their revision 0s are the same,
 * but their revision 1s are 0 and 1 respectively, and 0 < 1.
 * <p>
 * Return the following:
 * <p>
 * If version1 < version2, return -1.
 * If version1 > version2, return 1.
 * Otherwise, return 0.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: version1 = "1.01", version2 = "1.001"
 * Output: 0
 * Explanation: Ignoring leading zeroes, both "01" and "001" represent the same integer "1".
 * Example 2:
 * <p>
 * Input: version1 = "1.0", version2 = "1.0.0"
 * Output: 0
 * Explanation: version1 does not specify revision 2, which means it is treated as "0".
 * Example 3:
 * <p>
 * Input: version1 = "0.1", version2 = "1.1"
 * Output: -1
 * Explanation: version1's revision 0 is "0", while version2's revision 0 is "1". 0 < 1,
 * so version1 < version2.
 */
public class CompareVersionNumbers {
    /**
     * 解题思路：
     * <p>
     * 首先，我们将版本号字符串按照点号'.'进行分割，得到两个字符串数组v1和v2。
     * 然后，我们比较两个版本号的每个修订号（即数组中的每个元素）。
     * 在比较修订号时，我们将其转换为整数，并忽略前导零。
     * 如果某个修订号在其中一个版本号中不存在，则将其视为0。
     * 逐个比较修订号，如果某个修订号在version1中小于version2，则返回-1；
     * 如果某个修订号在version1中大于version2，则返回1；否则，比较下一个修订号。
     * 如果所有修订号都相等，则返回0。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：假设version1和version2的长度分别为m和n，分割版本号字符串的时间复杂度为O(m+n)，
     * 比较修订号的时间复杂度为O(max(m,n))，所以总的时间复杂度为O(m+n+max(m,n))。
     * 空间复杂度：分割版本号字符串所需要的额外空间为O(m+n)，所以总的空间复杂度为O(m+n)。
     *
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2) {
        // Split the version strings by dot '.'
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        // Compare the revisions in left-to-right order
        int i = 0;
        while (i < v1.length || i < v2.length) {
            int num1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
            int num2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;

            if (num1 < num2) {
                return -1;
            } else if (num1 > num2) {
                return 1;
            }

            i++;
        }

        // All revisions are equal
        return 0;
    }

    public static void main(String[] args) {
        // Test cases
        String version1 = "1.01";
        String version2 = "1.001";
        System.out.println(compareVersion(version1, version2));  // Expected output: 0

        version1 = "1.0";
        version2 = "1.0.0";
        System.out.println(compareVersion(version1, version2));  // Expected output: 0

        version1 = "0.1";
        version2 = "1.1";
        System.out.println(compareVersion(version1, version2));  // Expected output: -1
    }
}

