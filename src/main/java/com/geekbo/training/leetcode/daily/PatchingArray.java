package com.geekbo.training.leetcode.daily;

/**
 * 330. Patching Array
 * Hard
 * Given a sorted integer array nums and an integer n, add/patch elements to the array such that any number in the range [1, n] inclusive can be formed by the sum of some elements in the array.
 * <p>
 * Return the minimum number of patches required.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3], n = 6
 * Output: 1
 * Explanation:
 * Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
 * Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
 * Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
 * So we only need 1 patch.
 * Example 2:
 * <p>
 * Input: nums = [1,5,10], n = 20
 * Output: 2
 * Explanation: The two patches can be [2, 4].
 * Example 3:
 * <p>
 * Input: nums = [1,2,2], n = 5
 * Output: 0
 */
class PatchingArray {
    /**
     * 解题思路：
     * <p>
     * 这道题可以使用贪心算法来解决。
     * 我们需要维护一个变量coveredRange，表示可以通过数组中的元素组合得到的最大数。
     * 初始时，coveredRange为0，表示我们还无法得到任何数。
     * 我们遍历数组中的元素，如果当前元素小于等于coveredRange + 1，则可以用当前元素扩展coveredRange，将其更新为coveredRange + nums[i]。
     * 如果当前元素大于coveredRange + 1，则我们需要补充一个数coveredRange + 1，这样可以扩展coveredRange，将其更新为coveredRange + coveredRange + 1。
     * 我们继续遍历数组，直到coveredRange大于等于n为止。
     * 最后，返回所需补丁的个数。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度为O(m)，其中m是数组的长度。
     * 空间复杂度为O(1)。
     *
     * @param nums
     * @param n
     * @return
     */
    public int minPatches(int[] nums, int n) {
        int patches = 0;
        long coveredRange = 0; // Represents the maximum number that can be formed using the numbers in the array

        int i = 0;
        while (coveredRange < n) {
            if (i < nums.length && nums[i] <= coveredRange + 1) {
                coveredRange += nums[i]; // Extend the covered range
                i++;
            } else {
                coveredRange += coveredRange + 1; // Add a patch to the array and extend the covered range
                patches++;
            }
        }

        return patches;
    }

    public static void main(String[] args) {
        PatchingArray solution = new PatchingArray();

        int[] nums1 = {1, 3};
        int n1 = 6;
        System.out.println(solution.minPatches(nums1, n1)); // Expected output: 1

        int[] nums2 = {1, 5, 10};
        int n2 = 20;
        System.out.println(solution.minPatches(nums2, n2)); // Expected output: 2

        int[] nums3 = {1, 2, 2};
        int n3 = 5;
        System.out.println(solution.minPatches(nums3, n3)); // Expected output: 0
    }
}
