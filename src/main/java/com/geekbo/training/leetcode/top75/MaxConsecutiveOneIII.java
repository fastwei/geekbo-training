package com.geekbo.training.leetcode.top75;

/**
 *
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output: 6
 * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 * Example 2:
 *
 * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 * Output: 10
 * Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 *
 * 解题思路：
 * <p>
 * 我们使用滑动窗口来处理这个问题。定义两个指针，left 和 right，它们分别表示窗口的左边界和右边界。
 * <p>
 * 遍历数组，如果遇到0，将 zeroCount 增加。我们需要保持窗口内的0的个数不超过 k。
 * <p>
 * 如果 zeroCount 超过了 k，需要缩小窗口的左边界，同时减少 zeroCount 直到不再超过 k。
 * <p>
 * 在遍历的过程中，我们不断更新最大连续1的长度 maxLen，它等于当前窗口的大小。
 * <p>
 * 最终返回 maxLen 即为答案。
 * <p>
 * 算法复杂度：
 * <p>
 * 时间复杂度：O(N)，其中 N 是数组的长度。
 * 空间复杂度：O(1)。
 */
class MaxConsecutiveOneIII {
    /**
     * 计算在最多翻转k个0的情况下，数组中连续1的最大长度。
     *
     * @param nums 二进制数组
     * @param k    最多翻转的0的个数
     * @return 最大连续1的长度
     */
    public int longestOnes(int[] nums, int k) {
        int left = 0;        // 滑动窗口的左边界
        int zeroCount = 0;   // 记录窗口内0的个数
        int maxLen = 0;      // 最大连续1的长度

        // 遍历数组
        for (int right = 0; right < nums.length; right++) {
            // 如果当前元素是0，增加zeroCount
            if (nums[right] == 0) {
                zeroCount++;
            }

            // 如果zeroCount超过了k，需要缩小窗口左边界
            while (zeroCount > k) {
                // 如果左边界元素是0，减少zeroCount
                if (nums[left] == 0) {
                    zeroCount--;
                }
                // 缩小窗口左边界
                left++;
            }

            // 计算当前窗口内的最大长度
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        MaxConsecutiveOneIII solution = new MaxConsecutiveOneIII();

        int[] nums1 = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k1 = 2;
        System.out.println(solution.longestOnes(nums1, k1)); // 输出：6

        int[] nums2 = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        int k2 = 3;
        System.out.println(solution.longestOnes(nums2, k2)); // 输出：10
    }

}
