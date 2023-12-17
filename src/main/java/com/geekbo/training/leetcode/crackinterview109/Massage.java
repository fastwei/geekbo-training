package com.geekbo.training.leetcode.crackinterview109;// 题目要求按摩师选择预约的最优集合，使得总预约时间最长。

/**
 * 一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。
 * 在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。
 * 给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
 * <p>
 * 注意：本题相对原题稍作改动
 * 示例 1：
 * <p>
 * 输入： [1,2,3,1]
 * 输出： 4
 * 解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
 * 示例 2：
 * <p>
 * 输入： [2,7,9,3,1]
 * 输出： 12
 * 解释： 选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。
 * 示例 3：
 * <p>
 * 输入： [2,1,4,5,3,1,1,3]
 * 输出： 12
 * 解释： 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。
 */
// 由于按摩师不能接受相邻的预约，我们可以使用动态规划来解决这个问题。
// 定义一个数组dp，dp[i]表示前i个预约的最优总预约时间。
// 递推公式为：dp[i] = max(dp[i-1], dp[i-2]+nums[i])，即选择第i个预约或不选择第i个预约。
// 最终结果为dp[n-1]，其中n为预约序列的长度。
class Massage {
    /**
     * 解题思路：
     *
     * 使用动态规划求解，定义一个数组dp，dp[i]表示前i个预约的最优总预约时间。
     * 初始化dp[0]为第一个预约的时间，dp[1]为第一个和第二个预约中较长的时间。
     * 从第三个预约开始，使用递推公式dp[i] = max(dp[i-1], dp[i-2]+nums[i])求解dp[i]。
     * 最终结果为dp[n-1]，其中n为预约序列的长度。
     * 算法复杂度：
     *
     * 时间复杂度：O(n)，其中n为预约序列的长度，需要遍历一次预约序列求解dp数组。
     * 空间复杂度：O(n)，需要使用一个大小为n的dp数组来保存中间结果。
     *
     * @param nums
     * @return
     */
    public int massage(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        Massage solution = new Massage();

        // 测试用例 1
        int[] nums1 = {1, 2, 3, 1};
        // 预期输出：4
        System.out.println(solution.massage(nums1));

        // 测试用例 2
        int[] nums2 = {2, 7, 9, 3, 1};
        // 预期输出：12
        System.out.println(solution.massage(nums2));

        // 测试用例 3
        int[] nums3 = {2, 1, 4, 5, 3, 1, 1, 3};
        // 预期输出：12
        System.out.println(solution.massage(nums3));
    }
}