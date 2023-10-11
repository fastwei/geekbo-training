package com.geekbo.training.leetcode.top75;


/**
 *
 * Sliding Window
 *
 *Given a binary array nums, you should delete one element from it.
 *
 * Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,0,1]
 * Output: 3
 * Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
 * Example 2:
 *
 * Input: nums = [0,1,1,1,0,1,1,0,1]
 * Output: 5
 * Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
 * Example 3:
 *
 * Input: nums = [1,1,1]
 * Output: 2
 * Explanation: You must delete one element.
 *
 *给定二进制数组 nums 中删除一个元素后，返回最长的只包含1的非空子数组的长度。以下是该代码的解题思路和算法复杂度分析：
 *
 * 解题思路：
 *
 * 我们使用两个指针 left 和 right 来表示子数组的左边界和右边界，初始时都指向数组的第一个元素。
 * 我们还使用一个变量 zeroCount 来记录子数组内0的个数，初始值为0。
 * 遍历数组 nums，从左到右，不断移动 right 指针，同时检查当前元素是否为0，如果是0，则增加 zeroCount。
 * 如果 zeroCount 超过1（即子数组内有两个及以上的0），则需要缩小子数组的左边界，我们使用 while 循环来实现，移动 left 指针并减少 zeroCount 直到 zeroCount 不再超过1。
 * 在遍历过程中，不断更新 maxLength，记录最长的只包含1的非空子数组的长度。
 * 最后，如果最长子数组的长度等于数组长度，说明没有0，返回 maxLength - 1，否则返回 maxLength。
 * 算法复杂度分析：
 *
 * 时间复杂度：该算法只对输入数组进行了一次线性遍历，因此时间复杂度为 O(n)，其中 n 是数组 nums 的长度。
 * 空间复杂度：该算法只使用了常数额外的空间，因此空间复杂度为 O(1)。
 * 总体而言，这段代码通过一次线性扫描数组，高效地解决了问题，时间复杂度为 O(n)，并且只使用了常数额外空间。
 *
 */
class LongestSubarrayOfOnesAfterDeleteOneElement {
    /**
     * 删除数组中的一个元素，返回最长的只包含1的非空子数组的长度。
     *
     * @param nums  二进制数组
     * @return      最长的只包含1的非空子数组的长度
     */
    public int longestSubarray(int[] nums) {
        int maxLength = 0;       // 最长子数组长度
        int left = 0;            // 子数组的左边界
        int zeroCount = 0;       // 记录子数组内0的个数

        for (int right = 0; right < nums.length; right++) {
            // 如果当前元素是0，增加zeroCount
            if (nums[right] == 0) {
                zeroCount++;
            }

            // 如果zeroCount超过1，需要缩小子数组的左边界
            while (zeroCount > 1) {
                // 如果左边界元素是0，减少zeroCount
                if (nums[left] == 0) {
                    zeroCount--;
                }
                // 缩小子数组左边界
                left++;
            }

            // 更新最长子数组的长度
            maxLength = Math.max(maxLength, right - left);
        }

        // 如果最长子数组的长度等于数组长度，说明没有0，返回长度减1，否则返回maxLength
        return maxLength == nums.length ? maxLength - 1 : maxLength;
    }
    public static void main(String[] args) {
        LongestSubarrayOfOnesAfterDeleteOneElement solution = new LongestSubarrayOfOnesAfterDeleteOneElement();

        int[] nums1 = {1, 1, 0, 1};
        System.out.println(solution.longestSubarray(nums1)); // 输出：3

        int[] nums2 = {0, 1, 1, 1, 0, 1, 1, 0, 1};
        System.out.println(solution.longestSubarray(nums2)); // 输出：5

        int[] nums3 = {1, 1, 1};
        System.out.println(solution.longestSubarray(nums3)); // 输出：2
    }

}
