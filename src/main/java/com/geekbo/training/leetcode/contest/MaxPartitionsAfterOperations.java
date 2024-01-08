package com.geekbo.training.leetcode.contest;

import java.util.Arrays;

//370.4
public class MaxPartitionsAfterOperations {
    // 计算在最优选择情形下改变至多一处字符后，得到的最大分割数量
    public static int maxPartitionsAfterOperations(String s, int k) {
        int n = s.length();
        if (k >= n) return 1; // 如果 k 大于等于字符串长度，一次删除即可

        int result = 0;
        for (int i = 0; i < n; i++) { // 尝试在每个位置修改字符
            int count = 0, diff = 0;
            int[] charCount = new int[26];

            for (int j = 0, start = 0; j < n; j++) {
                if (charCount[s.charAt(j) - 'a']++ == 0) diff++;

                // 当遇到不同字符数量超过 k 时
                while (diff > k) {
                    if (--charCount[s.charAt(start) - 'a'] == 0) diff--;
                    start++;
                }

                // 检查是否可以通过修改当前字符来增加分割数量
                if (j == i) count++;
                if (j == n - 1 || s.charAt(j) != s.charAt(j + 1)) count++;
            }

            result = Math.max(result, count);
        }

        return result;
    }

    public static void main(String[] args) {
        MaxPartitionsAfterOperations solution = new MaxPartitionsAfterOperations();

        // 测试用例
        System.out.println(solution.maxPartitionsAfterOperations("accca", 2)); // 预期输出: 3
        System.out.println(solution.maxPartitionsAfterOperations("aabaab", 3)); // 预期输出: 1
        System.out.println(solution.maxPartitionsAfterOperations("xxyz", 1)); // 预期输出: 4
    }
}