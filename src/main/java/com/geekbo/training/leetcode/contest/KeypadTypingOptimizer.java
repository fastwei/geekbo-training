package com.geekbo.training.leetcode.contest;

import java.util.*;
//381.3
public class KeypadTypingOptimizer {
    // 计算打字所需的最少按键次数
    public int minimumPushes(String word) {
        // 统计每个字符的频率
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char ch : word.toCharArray()) {
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
        }

        // 将字符按频率降序排列
        List<Map.Entry<Character, Integer>> frequencyList = new ArrayList<>(frequencyMap.entrySet());
        frequencyList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // 按键状态数组，每个元素表示该按键上的字符数量
        int[] keys = new int[9];

        // 分配字符到按键
        int totalPushes = 0;
        for (Map.Entry<Character, Integer> entry : frequencyList) {
            int minIndex = -1;
            int minPresses = Integer.MAX_VALUE;

            // 查找可用按键
            for (int i = 0; i < 9; i++) {
                int presses = (keys[i] + 1) * (keys[i] + 1) - keys[i] * keys[i];
                if (presses < minPresses) {
                    minPresses = presses;
                    minIndex = i;
                }
            }

            // 计算并累加按键次数
            totalPushes += minPresses * entry.getValue();
            keys[minIndex]++;
        }

        return totalPushes;
    }


    // 主方法，用于测试
    public static void main(String[] args) {
        KeypadTypingOptimizer optimizer = new KeypadTypingOptimizer();
        
        // 测试用例
        System.out.println(optimizer.minimumPushes("abcde")); // 预期输出: 5
        System.out.println(optimizer.minimumPushes("xyzxyzxyzxyz")); // 预期输出: 12
        System.out.println(optimizer.minimumPushes("aabbccddeeffgghhiiiiii")); // 预期输出: 24
    }
}
