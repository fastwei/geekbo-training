package com.geekbo.training.leetcode.daily;

import java.util.HashSet;

class ContainsDuplicate {
    /**
     * 判断数组中是否存在重复元素
     * @param nums 整数数组
     * @return 存在重复元素返回true，否则返回false
     */
    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

    public static void main(String[] args) {
        // 测试用例1
        int[] nums1 = {1, 2, 3, 1};
        System.out.println("测试用例1: " + containsDuplicate(nums1)); // 预期输出: true

        // 测试用例2
        int[] nums2 = {1, 2, 3, 4};
        System.out.println("测试用例2: " + containsDuplicate(nums2)); // 预期输出: false

        // 测试用例3
        int[] nums3 = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
        System.out.println("测试用例3: " + containsDuplicate(nums3)); // 预期输出: true
    }
}