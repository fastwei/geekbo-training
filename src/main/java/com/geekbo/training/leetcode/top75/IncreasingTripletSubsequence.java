package com.geekbo.training.leetcode.top75;

/**
 * Array / String
 *
 * 题目描述：
 * 给定一个整数数组nums，判断是否存在三个不同的下标(i, j, k)满足条件i < j < k且nums[i] < nums[j] < nums[k]。如果存在则返回true，否则返回false。
 *
 * 示例：
 * 输入: nums = [1,2,3,4,5]
 * 输出: true
 * 解释: 任何满足i < j < k的三元组都是有效的。
 *
 * 输入: nums = [5,4,3,2,1]
 * 输出: false
 * 解释: 不存在满足条件的三元组。
 *
 * 输入: nums = [2,1,5,0,4,6]
 * 输出: true
 * 解释: 三元组(3, 4, 5)是有效的，因为nums[3] == 0 < nums[4] == 4 < nums[5] == 6。
 *
 * 解题思路：
 * 我们需要维护两个变量，min1和min2，分别表示第一个最小值和第二个最小值。遍历数组，如果当前元素小于或等于min1，则更新min1为当前元素；
 * 如果当前元素大于min1且小于或等于min2，则更新min2为当前元素；如果当前元素大于min2，说明存在满足条件的三元组，返回true。
 * 最终，如果没有找到满足条件的三元组，返回false。
 *
 * 算法复杂度分析：
 * - 时间复杂度：O(n)，其中n为数组的长度，需要遍历整个数组一次。
 * - 空间复杂度：O(1)，只需要常数级的额外空间来存储min1和min2。
 */

public class IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {
        int min1 = Integer.MAX_VALUE; // 第一个最小值
        int min2 = Integer.MAX_VALUE; // 第二个最小值

        for (int num : nums) {
            if (num <= min1) {
                min1 = num;
            } else if (num <= min2) {
                min2 = num;
            } else {
                // 存在满足条件的三元组
                return true;
            }
        }

        // 没有找到满足条件的三元组
        return false;
    }

    public static void main(String[] args) {
        IncreasingTripletSubsequence solution = new IncreasingTripletSubsequence();

        // 测试用例1
        int[] nums1 = {1, 2, 3, 4, 5};
        boolean result1 = solution.increasingTriplet(nums1);
        System.out.println("Example 1: " + result1); // 预期输出：true

        // 测试用例2
        int[] nums2 = {5, 4, 3, 2, 1};
        boolean result2 = solution.increasingTriplet(nums2);
        System.out.println("Example 2: " + result2); // 预期输出：false

        // 测试用例3
        int[] nums3 = {2, 1, 5, 0, 4, 6};
        boolean result3 = solution.increasingTriplet(nums3);
        System.out.println("Example 3: " + result3); // 预期输出：true
    }
}
