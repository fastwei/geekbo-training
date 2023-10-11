package com.geekbo.training.leetcode.top75;

/**
 *
 * Prefix Sum
 *
 *There is a biker going on a road trip.
 * The road trip consists of n + 1 points at different altitudes.
 * The biker starts his trip on point 0 with altitude equal 0.
 *
 * You are given an integer array gain of length n where gain[i] is the net gain in altitude between points i​​​​​​
 * and i + 1 for all (0 <= i < n). Return the highest altitude of a point.
 *
 *
 *
 * Example 1:
 *
 * Input: gain = [-5,1,5,0,-7]
 * Output: 1
 * Explanation: The altitudes are [0,-5,-4,1,1,-6]. The highest is 1.
 * Example 2:
 *
 * Input: gain = [-4,-3,-2,-1,4,3,2]
 * Output: 0
 * Explanation: The altitudes are [0,-4,-7,-9,-10,-6,-3,-1]. The highest is 0.
 *
 *
 *解决思路：
 *
 * 我们使用两个变量，maxAltitude 用于追踪最大高度，currentAltitude 用于追踪当前高度。
 * 遍历数组 gain，依次更新 currentAltitude，并将其与 maxAltitude 比较，以找到最大高度。
 * 算法复杂度分析：
 *
 * 时间复杂度：O(n)，其中 n 是数组 gain 的长度。我们只需遍历一次数组。
 * 空间复杂度：O(1)，我们只使用了常数额外的空间来存储变量。
 * 这个算法通过一次遍历数组就能够找到最大高度，具有较高的效率。
 *
 *Prefix Sum
 *
 */
public class FindHighestAltitude {
    public int largestAltitude(int[] gain) {
        int maxAltitude = 0; // 初始高度为0
        int currentAltitude = 0; // 当前高度从0开始

        for (int i = 0; i < gain.length; i++) {
            currentAltitude += gain[i]; // 更新当前高度
            maxAltitude = Math.max(maxAltitude, currentAltitude); // 更新最大高度
        }

        return maxAltitude;
    }

    public static void main(String[] args) {
        FindHighestAltitude solution = new FindHighestAltitude();
        
        // 测试用例1
        int[] gain1 = {-5, 1, 5, 0, -7};
        int result1 = solution.largestAltitude(gain1);
        System.out.println("Test Case 1: " + result1); // 预期输出: 1

        // 测试用例2
        int[] gain2 = {-4, -3, -2, -1, 4, 3, 2};
        int result2 = solution.largestAltitude(gain2);
        System.out.println("Test Case 2: " + result2); // 预期输出: 0
    }
}
