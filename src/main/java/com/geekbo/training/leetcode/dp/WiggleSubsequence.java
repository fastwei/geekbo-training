package com.geekbo.training.leetcode.dp;

/**
 * 376. Wiggle Subsequence
 * Medium
 * A wiggle sequence is a sequence where the differences between successive numbers
 * strictly alternate between positive and negative.
 * The first difference (if one exists) may be either positive or negative.
 * A sequence with one element and a sequence with two non-equal elements are trivially wiggle sequences.
 * <p>
 * For example, [1, 7, 4, 9, 2, 5] is a wiggle sequence because the differences (6, -3, 5, -7, 3) alternate between positive and negative.
 * In contrast, [1, 4, 7, 2, 5] and [1, 7, 4, 5, 5] are not wiggle sequences.
 * The first is not because its first two differences are positive, and the second is not because its last difference is zero.
 * A subsequence is obtained by deleting some elements (possibly zero) from the original sequence,
 * leaving the remaining elements in their original order.
 * <p>
 * Given an integer array nums, return the length of the longest wiggle subsequence of nums.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,7,4,9,2,5]
 * Output: 6
 * Explanation: The entire sequence is a wiggle sequence with differences (6, -3, 5, -7, 3).
 * Example 2:
 * <p>
 * Input: nums = [1,17,5,10,13,15,10,5,16,8]
 * Output: 7
 * Explanation: There are several subsequences that achieve this length.
 * One is [1, 17, 10, 13, 10, 16, 8] with differences (16, -7, 3, -3, 6, -8).
 * Example 3:
 * <p>
 * Input: nums = [1,2,3,4,5,6,7,8,9]
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 * <p>
 * <p>
 * Follow up: Could you solve this in O(n) time?
 */
class WiggleSubsequence {
    /**
     * 解题思路： 这道题要求找出给定数组中的最长摆动子序列的长度。
     * 摆动序列是指相邻元素之间的差值严格交替为正负。
     * 我们可以使用动态规划来解决这个问题。
     * <p>
     * 我们使用两个变量up和down来分别记录以当前元素结尾的具有正差值和负差值的最长摆动子序列的长度。
     * 初始化时，up和down都为1，因为任何一个元素都可以作为一个摆动序列。
     * <p>
     * 然后，我们遍历数组，对于每个 元素，我们判断其与前一个元素的大小关系：
     * <p>
     * 如果当前元素大于前一个元素，则可以将当前元素添加到一个以前一个元素结尾的具有负差值的最长摆动子序列之后，从而形成一个以当前元素结尾的具有正差值的最长摆动子序列。因此，我们更新up的值为down + 1。
     * 如果当前元素小于前一个元素，则可以将当前元素添加到一个以前一个元素结尾的具有正差值的最长摆动子序列之后，从而形成一个以当前元素结尾的具有负差值的最长摆动子序列。因此，我们更新down的值为up + 1。
     * 最后，最长摆动子序列的长度就是up和down中的较大值。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历一次数组，所以时间复杂度是O(n)，其中n是数组的长度。
     * 空间复杂度：只使用了常数级别的额外空间，所以空间复杂度是O(1)。
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }

        int up = 1; // length of the longest wiggle subsequence ending at current index with a positive difference
        int down = 1; // length of the longest wiggle subsequence ending at current index with a negative difference

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1; // increase the length of the wiggle subsequence ending at current index with a positive difference
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1; // increase the length of the wiggle subsequence ending at current index with a negative difference
            }
        }

        return Math.max(up, down);
    }

    public static void main(String[] args) {
        WiggleSubsequence solution = new WiggleSubsequence();

        // Test Case 1
        int[] nums1 = {1, 7, 4, 9, 2, 5};
        // The entire sequence is a wiggle sequence with differences (6, -3, 5, -7, 3).
        // So, the length of the longest wiggle subsequence is 6.
        int expected1 = 6;
        int result1 = solution.wiggleMaxLength(nums1);
        System.out.println(result1 == expected1 ? "Test Case 1 Passed" : "Test Case 1 Failed");

        // Test Case 2
        int[] nums2 = {1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
        // One of the subsequences that achieve the length 7 is [1, 17, 10, 13, 10, 16, 8] with differences (16, -7, 3, -3, 6, -8).
        // So, the length of the longest wiggle subsequence is 7.
        int expected2 = 7;
        int result2 = solution.wiggleMaxLength(nums2);
        System.out.println(result2 == expected2 ? "Test Case 2 Passed" : "Test Case 2 Failed");

        // Test Case 3
        int[] nums3 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        // The longest wiggle subsequence that can be formed is [1, 2].
        // So, the length of the longest wiggle subsequence is 2.
        int expected3 = 2;
        int result3 = solution.wiggleMaxLength(nums3);
        System.out.println(result3 == expected3 ? "Test Case 3 Passed" : "Test Case 3 Failed");
    }
}
