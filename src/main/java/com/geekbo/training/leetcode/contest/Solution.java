package com.geekbo.training.leetcode.contest;

import java.util.HashMap;
import java.util.Map;
//382.2
public class Solution {

    // 方法：计算满足条件的最大子集长度
    public static int maximumLength(int[] nums) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        int maxLength = 0;
        for (int num : frequencyMap.keySet()) {
            if (frequencyMap.containsKey(num)) {
                int length = 0;
                int freq = frequencyMap.get(num);
                int factor = 1;
                while (frequencyMap.containsKey(num * factor)) {
                    length += freq;
                    factor *= 2;
                    freq = frequencyMap.getOrDefault(num * factor, 0);
                }
                maxLength = Math.max(maxLength, length);
            }
        }
        return maxLength;
    }

    // 主函数：用于测试
    public static void main(String[] args) {
        System.out.println(maximumLength(new int[]{5, 4, 1, 2, 2})); // 预期输出：3
        System.out.println(maximumLength(new int[]{1, 3, 2, 4}));   // 预期输出：1
    }
}
