package com.geekbo.training.leetcode.top150;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a string s.
 * We want to partition the string into as many parts as possible so that each letter appears in at most one part.
 * <p>
 * Note that the partition is done so that after concatenating all the parts in order,
 * the resultant string should be s.
 * <p>
 * Return a list of integers representing the size of these parts.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
 */
public class PartitionLabels {
    /**
     * 解题思路：
     * <p>
     * 首先，我们需要找到每个字符在字符串中最后一次出现的位置。
     * 然后，我们遍历字符串，使用两个指针来表示当前分区的起始位置和结束位置。
     * 在遍历过程中，我们更新当前分区的结束位置，
     * 即如果当前字符的最后一次出现位置大于当前分区的结束位置，则更新结束位置。
     * 如果当前位置等于当前分区的结束位置，说明当前分区已经结束，
     * 我们将当前分区的长度添加到结果中，并更新下一个分区的起始位置。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：O(n)，其中n是字符串的长度。
     * 空间复杂度：O(1)，因为最多只有26个字母。
     *
     * @param s
     * @return
     */
    public List<Integer> partitionLabels(String s) {
        // 创建一个数组来存储每个字符在字符串中最后一次出现的位置
        int[] lastOccurrence = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastOccurrence[s.charAt(i) - 'a'] = i;
        }

        List<Integer> partitions = new ArrayList<>();
        int start = 0; // 当前分区的起始位置
        int end = 0; // 当前分区的结束位置

        // 遍历字符串
        for (int i = 0; i < s.length(); i++) {
            // 更新当前分区的结束位置
            end = Math.max(end, lastOccurrence[s.charAt(i) - 'a']);

            // 如果当前位置等于当前分区的结束位置，说明当前分区已经结束
            if (i == end) {
                // 将当前分区的长度添加到结果中
                partitions.add(end - start + 1);
                // 更新下一个分区的起始位置
                start = end + 1;
            }
        }

        return partitions;
    }

    public static void main(String[] args) {
        PartitionLabels partitionLabels = new PartitionLabels();

        // Test Case 1
        String s1 = "ababcbacadefegdehijhklij";
        List<Integer> result1 = partitionLabels.partitionLabels(s1);
        System.out.println("Test Case 1:");
        System.out.println("Input: " + s1);
        System.out.println("Output: " + result1);

        // Test Case 2
        String s2 = "abcdefg";
        List<Integer> result2 = partitionLabels.partitionLabels(s2);
        System.out.println("\nTest Case 2:");
        System.out.println("Input: " + s2);
        System.out.println("Output: " + result2);
    }
}

