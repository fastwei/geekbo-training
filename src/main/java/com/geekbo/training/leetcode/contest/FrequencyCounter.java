package com.geekbo.training.leetcode.contest;

import java.util.HashMap;
import java.util.Map;
//380.1
public class FrequencyCounter {
    
    // maxFrequencyElements 方法计算具有最大频率的元素的总频率
    public static int maxFrequencyElements(int[] nums) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int maxFreq = 0;
        int count = 0;

        // 计算每个元素的频率
        for (int num : nums) {
            int freq = frequencyMap.getOrDefault(num, 0) + 1;
            frequencyMap.put(num, freq);
            maxFreq = Math.max(maxFreq, freq);
        }

        // 计算具有最大频率的元素的总数
        for (int freq : frequencyMap.values()) {
            if (freq == maxFreq) {
                count += freq;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // 测试用例
        System.out.println(maxFrequencyElements(new int[]{1, 2, 2, 3, 1, 4})); // 预期输出：4
        System.out.println(maxFrequencyElements(new int[]{1, 2, 3, 4, 5}));   // 预期输出：5
    }
}

// 算法复杂度分析：
// 时间复杂度：O(n)，其中 n 是数组 nums 的长度，因为我们只需要遍历一次数组。
// 空间复杂度：O(n)，最坏情况下，哈希表可能需要存储数组中的所有不同元素。
