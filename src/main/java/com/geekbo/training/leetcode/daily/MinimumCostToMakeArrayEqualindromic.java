package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

public class MinimumCostToMakeArrayEqualindromic {

    /**
     * 解题思路：
     * <p>
     * 首先，对数组 nums 进行排序。
     * 找到排序后数组的中间元素 middle。
     * 初始化两个变量 inc 和 dec，分别表示从 middle 开始向右和向左递增，直到找到回文数为止。
     * 计算使用 inc 和 dec 作为回文数时的代价，返回较小的代价。
     * 算法复杂度分析：
     * <p>
     * 对数组进行排序的时间复杂度为 O(nlogn)。
     * 判断一个数是否是回文数的时间复杂度为 O(logn)。
     * 计算代价的时间复杂度为 O(n)。
     * 因此，总的时间复杂度为 O(nlogn)。
     *
     * @param nums
     * @return
     */
    public static long minimumCost(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }

        Arrays.sort(nums);
        int middle = nums[n / 2];
        long result = Long.MAX_VALUE;
        int inc = middle;
        int dec = middle;

        while (!isPal(inc)) {
            inc++;
        }
        while (!isPal(dec)) {
            dec--;
        }

        return Math.min(calculateCost(nums, inc), calculateCost(nums, dec));
    }

    public static boolean isPal(int n) {
        int r, sum = 0, temp;
        temp = n;
        while (n > 0) {
            r = n % 10;
            sum = (sum * 10) + r;
            n = n / 10;
        }
        return temp == sum;
    }

    public static long calculateCost(int[] nums, int r) {
        long cost = 0;
        for (int n : nums) {
            cost += Math.abs(n - r);
        }
        return cost;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4, 5};
        System.out.println("Input: nums = " + Arrays.toString(nums1));
        System.out.println("Output: " + minimumCost(nums1));

        int[] nums2 = {10, 12, 13, 14, 15};
        System.out.println("Input: nums = " + Arrays.toString(nums2));
        System.out.println("Output: " + minimumCost(nums2));

        int[] nums3 = {22, 33, 22, 33, 22};
        System.out.println("Input: nums = " + Arrays.toString(nums3));
        System.out.println("Output: " + minimumCost(nums3));
    }
}

