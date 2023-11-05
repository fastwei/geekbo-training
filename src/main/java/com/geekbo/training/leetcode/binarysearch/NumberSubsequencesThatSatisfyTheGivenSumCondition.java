package com.geekbo.training.leetcode.binarysearch;

import java.util.Arrays;

class NumberSubsequencesThatSatisfyTheGivenSumCondition {
    /**
     *
     * 使用二分查找的方式
     *
     * @param nums
     * @param target
     * @return
     */

    public int numSubseq2(int[] nums, int target) {
        int n = nums.length;
        int[] pow = new int[n];
        int MOD = (int) 1e9 + 7;

        // 计算2的幂次方
        pow[0] = 1;
        for (int i = 1; i < n; i++) {
            pow[i] = pow[i - 1] * 2 % MOD;
        }

        Arrays.sort(nums);

        int count = 0;
        int left = 0;
        int right = n - 1;

        // 使用两分查找，统计满足条件的子序列数量
        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                count = (count + pow[right - left]) % MOD;
                left++;
            } else {
                right--;
            }
        }

        return count;
    }


    /**
     * 解题思路：
     *
     * 首先对数组 nums 进行排序，以便更方便地找到最小值和最大值。
     * 使用双指针 left 和 right 分别指向数组的开头和结尾。
     * 如果 nums[left] + nums[right] <= target，则以 nums[left] 为最小值，
     * nums[right] 为最大值的子序列都满足条件，个数为 $2^{(right - left)}$。
     * 如果 nums[left] + nums[right] > target，则将 right 向前移动一位。
     * 统计满足条件的子序列数量，并取模 MOD = 1e9 + 7。
     * 返回统计的结果。
     *
     * 算法复杂度分析:
     *
     * 时间复杂度: 排序数组的时间复杂度为 O(nlogn)，双指针遍历的时间复杂度为 O(n)，因此总的时间复杂度为 O(nlogn)。
     * 空间复杂度: 需要一个数组 pow 来存储幂次方，其长度为 n，因此空间复杂度为 O(n)。
     *
     * @param nums
     * @param target
     * @return
     */
    public int numSubseq(int[] nums, int target) {
        int n = nums.length;
        int[] pow = new int[n];
        int MOD = (int) 1e9 + 7;

        // 计算2的幂次方
        pow[0] = 1;
        for (int i = 1; i < n; i++) {
            pow[i] = pow[i - 1] * 2 % MOD;
        }

        Arrays.sort(nums);

        int count = 0;
        int left = 0;
        int right = n - 1;

        // 使用双指针，统计满足条件的子序列数量
        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                count = (count + pow[right - left]) % MOD;
                left++;
            } else {
                right--;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        NumberSubsequencesThatSatisfyTheGivenSumCondition numberSubsequencesThatSatisfyTheGivenSumCondition = new NumberSubsequencesThatSatisfyTheGivenSumCondition();

        // 测试用例
        int[] nums1 = {3, 5, 6, 7};
        int target1 = 9;
        // 预期输出: 4
        System.out.println(numberSubsequencesThatSatisfyTheGivenSumCondition.numSubseq(nums1, target1));

        int[] nums2 = {3, 3, 6, 8};
        int target2 = 10;
        // 预期输出: 6
        System.out.println(numberSubsequencesThatSatisfyTheGivenSumCondition.numSubseq(nums2, target2));

        int[] nums3 = {2, 3, 3, 4, 6, 7};
        int target3 = 12;
        // 预期输出: 61
        System.out.println(numberSubsequencesThatSatisfyTheGivenSumCondition.numSubseq(nums3, target3));
    }
}
