package com.geekbo.training.leetcode.daily;

import java.util.TreeSet;

/**
 * 220. Contains Duplicate III
 * Hard
 * You are given an integer array nums and two integers indexDiff and valueDiff.
 * <p>
 * Find a pair of indices (i, j) such that:
 * <p>
 * i != j,
 * abs(i - j) <= indexDiff.
 * abs(nums[i] - nums[j]) <= valueDiff, and
 * Return true if such pair exists or false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1], indexDiff = 3, valueDiff = 0
 * Output: true
 * Explanation: We can choose (i, j) = (0, 3).
 * We satisfy the three conditions:
 * i != j --> 0 != 3
 * abs(i - j) <= indexDiff --> abs(0 - 3) <= 3
 * abs(nums[i] - nums[j]) <= valueDiff --> abs(1 - 1) <= 0
 * Example 2:
 * <p>
 * Input: nums = [1,5,9,1,5,9], indexDiff = 2, valueDiff = 3
 * Output: false
 * Explanation: After trying all the possible pairs (i, j),
 * we cannot satisfy the three conditions, so we return false.
 */
class ContainsDuplicateIII {
    /**
     * 解题思路：
     * <p>
     * 使用TreeSet数据结构来存储窗口中的元素。
     * 遍历数组，对于每个元素nums[i]，在TreeSet中查找是否存在大于等于nums[i] - valueDiff且小于等于nums[i] + valueDiff的元素。
     * 如果存在，则满足条件，返回true。
     * 将nums[i]添加到TreeSet中，并维护窗口大小为indexDiff，保证窗口中的元素的下标差不超过indexDiff。
     * 如果遍历完数组后仍未找到满足条件的元素，返回false。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 数组长度为n，遍历数组需要O(n)的时间复杂度。
     * TreeSet的插入、删除和查找操作的平均时间复杂度为O(logn)。
     * 因此，总的时间复杂度为O(nlogn)。
     * 使用了一个额外的TreeSet来存储窗口中的元素，所以空间复杂度为O(k)，其中k为窗口的大小。
     *
     * @param nums
     * @param indexDiff
     * @param valueDiff
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Long> set = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {
            // 找到集合中大于等于nums[i] - valueDiff的最小值
            Long ceiling = set.ceiling((long) nums[i] - valueDiff);
            if (ceiling != null && ceiling <= (long) nums[i] + valueDiff) {
                return true;
            }

            set.add((long) nums[i]);

            // 维护一个大小为indexDiff的窗口，保证窗口中的元素的下标差不超过indexDiff
            if (i >= indexDiff) {
                set.remove((long) nums[i - indexDiff]);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        ContainsDuplicateIII containsDuplicateIII = new ContainsDuplicateIII();

        // 测试用例1
        int[] nums1 = {1, 2, 3, 1};
        int indexDiff1 = 3;
        int valueDiff1 = 0;
        boolean expected1 = true;
        boolean result1 = containsDuplicateIII.containsNearbyAlmostDuplicate(nums1, indexDiff1, valueDiff1);
        System.out.println("测试用例1:");
        System.out.println("预期输出: " + expected1);
        System.out.println("实际输出: " + result1);

        // 测试用例2
        int[] nums2 = {1, 5, 9, 1, 5, 9};
        int indexDiff2 = 2;
        int valueDiff2 = 3;
        boolean expected2 = false;
        boolean result2 = containsDuplicateIII.containsNearbyAlmostDuplicate(nums2, indexDiff2, valueDiff2);
        System.out.println("测试用例2:");
        System.out.println("预期输出: " + expected2);
        System.out.println("实际输出: " + result2);
    }
}
