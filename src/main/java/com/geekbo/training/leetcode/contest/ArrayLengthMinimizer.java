package com.geekbo.training.leetcode.contest;

import java.util.PriorityQueue;

//381.3
public class ArrayLengthMinimizer {
    public int minimumArrayLength(int[] nums) {
        int gcd = nums[0];
        for (int i = 1; i < nums.length; i++) {
            gcd = gcd(gcd, nums[i]);
            if (gcd == 1) {
                return 1;
            }
        }
        return gcd;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        ArrayLengthMinimizer minimizer = new ArrayLengthMinimizer();

        // 测试用例
        int[] nums1 = {1, 4, 3, 1};
        int[] nums2 = {5, 5, 5, 10, 5};
        int[] nums3 = {2, 3, 4};
        int[] nums4 = {1};

        // 输出预期结果
        System.out.println("Test case 1: " + minimizer.minimumArrayLength(nums1)); // 预期输出：1
        System.out.println("Test case 2: " + minimizer.minimumArrayLength(nums2)); // 预期输出：2
        System.out.println("Test case 3: " + minimizer.minimumArrayLength(nums3)); // 预期输出：1
        System.out.println("Test case 4: " + minimizer.minimumArrayLength(nums4)); // 预期输出：1
    }
}