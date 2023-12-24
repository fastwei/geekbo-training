package com.geekbo.training.leetcode.daily;

/**
 * 1712. Ways to Split Array Into Three Subarrays
 * Medium
 * A split of an integer array is good if:
 * <p>
 * The array is split into three non-empty contiguous subarrays - named left, mid, right respectively from left to right.
 * The sum of the elements in left is less than or equal to the sum of the elements in mid, and the sum of the elements in mid is less than or equal to the sum of the elements in right.
 * Given nums, an array of non-negative integers, return the number of good ways to split nums. As the number may be too large, return it modulo 109 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1]
 * Output: 1
 * Explanation: The only good way to split nums is [1] [1] [1].
 * Example 2:
 * <p>
 * Input: nums = [1,2,2,2,5,0]
 * Output: 3
 * Explanation: There are three good ways of splitting nums:
 * [1] [2] [2,2,5,0]
 * [1] [2,2] [2,5,0]
 * [1,2] [2,2] [5,0]
 * Example 3:
 * <p>
 * Input: nums = [3,2,1]
 * Output: 0
 * Explanation: There is no good way to split nums.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 3 <= nums.length <= 105
 * 0 <= nums[i] <= 104
 * <p>
 */

public class SplitArray {
    /**
     * 解题思路：
     * <p>
     * 首先，对输入的数组进行预处理，计算前缀和。通过遍历数组，每个元素累加前面的元素，得到一个新的数组。
     * 然后，使用三个指针 i、j、k 来表示左子数组、中间子数组和右子数组的边界。
     * 初始化 i、j、k 为 0，并遍历数组：
     * 对于每个 i，通过比较 3 * nums[i] 和 nums[nums.length - 1]，判断是否存在有效的分割方案。
     * 初始化 j 为 i + 1，并在 j 小于数组长度减一且 nums[j] - nums[i] 小于 nums[i] 的情况下，将 j 向右移动。
     * 初始化 k 为 j，并在 k 小于数组长度减一且 nums[k] - nums[i] 小于等于 nums[nums.length - 1] - nums[k] 的情况下，将 k 向右移动。
     * 将有效方案的数量 ways 更新为 (ways + k - j) % 1000000007。
     * 最后返回有效方案的数量 ways。
     * 算法复杂度：
     * <p>
     * 时间复杂度：该算法需要遍历数组一次，时间复杂度为 O(n)，其中 n 为数组长度。
     * 空间复杂度：除了输入数组外，算法只使用了常数级的额外空间，所以空间复杂度为 O(1)。
     *
     * @param nums
     * @return
     */
    public static int waysToSplit(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        int ways = 0;
        for (int i = 0, j = 0, k = 0; i < nums.length - 2 && 3 * nums[i] <= nums[nums.length - 1]; i++) {
            j = Math.max(j, i + 1);
            while (j < nums.length - 1 && nums[j] - nums[i] < nums[i])
                j++;
            k = Math.max(k, j);
            while (k < nums.length - 1 && nums[k] - nums[i] <= nums[nums.length - 1] - nums[k])
                k++;
            ways = (ways + k - j) % 1000000007;
        }
        return ways;
    }

    public static void main(String[] args) {
        // Test case 1
        int[] nums1 = {1, 1, 1};
        int expected1 = 1;
        int result1 = waysToSplit(nums1);
        System.out.println(result1 == expected1 ? "Test case 1 passed" : "Test case 1 failed");

        // Test case 2
        int[] nums2 = {1, 2, 2, 2, 5, 0};
        int expected2 = 3;
        int result2 = waysToSplit(nums2);
        System.out.println(result2 == expected2 ? "Test case 2 passed" : "Test case 2 failed");

        // Test case 3
        int[] nums3 = {3, 2, 1};
        int expected3 = 0;
        int result3 = waysToSplit(nums3);
        System.out.println(result3 == expected3 ? "Test case 3 passed" : "Test case 3 failed");
    }
}