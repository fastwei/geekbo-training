package com.geekbo.training.leetcode.contest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//379.4
public class StringPartitioner {
    /**
     * Calculates the maximum number of partitions after performing the operations.
     *
     * @param s The input string.
     * @param k The maximum number of distinct characters in a partition.
     * @return The maximum number of partitions possible.
     */
    public static int maxPartitionsAfterOperations(String s, int k) {
        int n = s.length();
        int maxPartitions = 0;
        for (int changeIndex = -1; changeIndex < n; changeIndex++) {
            int partitions = 0, distinct = 0, start = 0;
            int[] count = new int[26];

            for (int i = 0; i < n; i++) {
                if (i == changeIndex) {
                    distinct++; // Assume the changed character is distinct
                } else {
                    if (count[s.charAt(i) - 'a'] == 0) distinct++;
                    count[s.charAt(i) - 'a']++;
                }

                if (distinct > k) {
                    partitions++; // Split the partition here
                    Arrays.fill(count, 0); // Reset count for the new partition
                    if (i == changeIndex) {
                        distinct = 1; // Start with the changed character
                    } else {
                        distinct = 1;
                        count[s.charAt(i) - 'a'] = 1;
                    }
                }
            }
            partitions++;
            maxPartitions = Math.max(maxPartitions, partitions);
        }
        return maxPartitions;
    }

    /**
     * 计算在最多改变一个字符的情况下，能获得的最大分区数。
     *
     * @param s 字符串s
     * @param k 最多包含k个不同字符的前缀
     * @return 返回执行操作后的最大分区数
     */
    public static int maxPartitionsAfterOperations2(String s, int k) {
        if (k >= 26) {
            return 1; // 如果 k 大于等于26，整个字符串可以作为一个分区
        }

        int maxPartitions = 1;
        for (int i = 0; i < s.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                StringBuilder sb = new StringBuilder(s);
                sb.setCharAt(i, ch); // 改变一个字符
                maxPartitions = Math.max(maxPartitions, calculatePartitions2(sb.toString(), k));
            }
        }
        return maxPartitions;
    }

    private static int calculatePartitions2(String s, int k) {
        int partitions = 0;
        int start = 0;
        while (start < s.length()) {
            int distinct = 0;
            Map<Character, Integer> map = new HashMap<>();
            int end = start;
            while (end < s.length() && (distinct < k || map.containsKey(s.charAt(end)))) {
                map.put(s.charAt(end), map.getOrDefault(s.charAt(end), 0) + 1);
                if (map.get(s.charAt(end)) == 1) {
                    distinct++;
                }
                end++;
            }
            partitions++;
            start = end;
        }
        return partitions;
    }




    // 主方法，用于测试功能
    public static void main(String[] args) {
        String s1 = "accca";
        int k1 = 2;
        // 预期输出：3
        System.out.println("Test Case 1: " + maxPartitionsAfterOperations(s1, k1));

        String s2 = "aabaab";
        int k2 = 3;
        // 预期输出：1
        System.out.println("Test Case 2: " + maxPartitionsAfterOperations(s2, k2));

        String s3 = "xxyz";
        int k3 = 1;
        // 预期输出：4
        System.out.println("Test Case 3: " + maxPartitionsAfterOperations(s3, k3));
    }
}
