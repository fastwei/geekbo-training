package com.geekbo.training.leetcode.contest;

public class XORArrayOperations {

    // 方法：计算使数组 XOR 等于 k 的最小操作数
    public int minOperations(int[] nums, int k) {
        int xorSum = 0;

        // 计算数组中所有元素的 XOR
        for (int num : nums) {
            xorSum ^= num;
        }

        // 如果初始 XOR 已经等于 k，则不需要任何操作
        if (xorSum == k) {
            return 0;
        }

        // 计算与 k 的 XOR，然后统计需要翻转的位数
        int xorWithK = xorSum ^ k;
        int minOps = 0;
        while (xorWithK > 0) {
            minOps += xorWithK & 1; // 如果当前位是 1，则需要翻转
            xorWithK >>= 1; // 右移以检查下一位
        }

        // 返回最小操作数
        return minOps;
    }

    // 主方法：用于测试
    public static void main(String[] args) {
        XORArrayOperations solution = new XORArrayOperations();

        // 测试用例1
        int[] nums1 = {2, 1, 3, 4};
        int k1 = 1;
        System.out.println("Test Case 1 - Expected: 2, Actual: " + solution.minOperations(nums1, k1));

        // 测试用例2
        int[] nums2 = {2, 0, 2, 0};
        int k2 = 0;
        System.out.println("Test Case 2 - Expected: 0, Actual: " + solution.minOperations(nums2, k2));
    }
}
