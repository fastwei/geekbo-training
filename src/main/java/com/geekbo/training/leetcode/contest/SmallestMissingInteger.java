package com.geekbo.training.leetcode.contest;

public class SmallestMissingInteger {
    /**
     * 返回数组中缺失的最小整数，该整数大于等于最长连续前缀的和。
     *
     * @param nums 给定的整数数组
     * @return 缺失的最小整数
     */
    public int missingInteger(int[] nums) {
        // 检查是否有序且连续
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1] + 1) {
                break;
            }
            sum += nums[i];
        }

        // 寻找缺失的最小整数
        for (int i = sum; ; i++) {
            boolean found = false;
            for (int num : nums) {
                if (num == i) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return i;
            }
        }
    }

    public static void main(String[] args) {
        SmallestMissingInteger solution = new SmallestMissingInteger();

        // 测试用例1
        int[] nums1 = {1, 2, 3, 2, 5};
        System.out.println("Expected: 6, Actual: " + solution.missingInteger(nums1));

        // 测试用例2
        int[] nums2 = {3, 4, 5, 1, 12, 14, 13};
        System.out.println("Expected: 15, Actual: " + solution.missingInteger(nums2));
    }
}
