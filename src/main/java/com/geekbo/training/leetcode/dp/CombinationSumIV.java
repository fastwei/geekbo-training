package com.geekbo.training.leetcode.dp;

/**
 * 377. Combination Sum IV
 * Medium
 * Given an array of distinct integers nums and a target integer target,
 * return the number of possible combinations that add up to target.
 * <p>
 * The test cases are generated so that the answer can fit in a 32-bit integer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3], target = 4
 * Output: 7
 * Explanation:
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * Note that different sequences are counted as different combinations.
 * Example 2:
 * <p>
 * Input: nums = [9], target = 3
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 1000
 * All the elements of nums are unique.
 * 1 <= target <= 1000
 * <p>
 * <p>
 * Follow up: What if negative numbers are allowed in the given array? How does it change the problem?
 * What limitation we need to add to the question to allow negative numbers?
 */
public class CombinationSumIV {
    /**
     * 解题思路： 这是一个动态规划问题。
     * 我们定义一个一维数组dp，其中dp[i]表示和为i的组合数的个数。
     * 我们遍历数组nums，对于每个数字num，我们遍历从num到target的所有整数，并更新dp数组。
     * 具体地，对于每个数字num，我们更新dp[i]的值为dp[i]+dp[i-num]，表示和为i的组合数的个数为之前的组合数加上和为i-num的组合数。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：数组nums的长度为N，目标值为target，所以总时间复杂度为O(N*target)
     * 空间复杂度：使用了一个一维数组dp，大小为target+1，所以空间复杂度为O(target)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};
        int target1 = 4;
        System.out.println("Test Case 1 - Expected: 7, Actual: " + combinationSum4(nums1, target1));

        int[] nums2 = {9};
        int target2 = 3;
        System.out.println("Test Case 2 - Expected: 0, Actual: " + combinationSum4(nums2, target2));
    }
}
