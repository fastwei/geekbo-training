package com.geekbo.training.leetcode.crackinterview109;

/**
 *
 *
 * 面试题 08.03. 魔术索引
 * 简单
 * 魔术索引。 在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。给定一个有序整数数组，编写一种方法找出魔术索引，若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。若有多个魔术索引，返回索引值最小的一个。
 *
 * 示例1:
 *
 *  输入：nums = [0, 2, 3, 4, 5]
 *  输出：0
 *  说明: 0下标的元素为0
 * 示例2:
 *
 *  输入：nums = [1, 1, 1]
 *  输出：1
 * 说明:
 *
 * nums长度在[1, 1000000]之间
 * 此题为原书中的 Follow-up，即数组中可能包含重复元素的版本
 */
public class MagicIndex {
    /**
     * 解题思路：
     * 由于数组是有序的，我们可以使用二分查找的方法来查找魔术索引。
     * 首先，我们从数组的中间位置开始，判断中间位置的值是否等于中间位置的索引。
     * 如果相等，则找到了魔术索引，返回中间位置。
     * 如果中间位置的值大于中间位置的索引，那么魔术索引只可能在左边，我们继续在左边的子数组中查找。
     * 如果中间位置的值小于中间位置的索引，那么魔术索引只可能在右边，我们继续在右边的子数组中查找。
     * 这样不断缩小查找范围，直到找到魔术索引或者查找范围为空，返回-1。
     *
     * 算法复杂度分析：
     * 由于每次查找都将查找范围缩小一半，所以查找时间复杂度为 O(log n)。
     * 空间复杂度为 O(1)。
     *
     * @param nums
     * @return
     */
    public static int findMagicIndex(int[] nums) {
        return getAnswer(nums, 0, nums.length - 1);
    }

    public static int getAnswer(int[] nums, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (right - left) / 2 + left;
        int leftAnswer = getAnswer(nums, left, mid - 1);
        if (leftAnswer != -1) {
            return leftAnswer;
        } else if (nums[mid] == mid) {
            return mid;
        }
        return getAnswer(nums, mid + 1, right);
    }

    public static void main(String[] args) {
        // 测试用例 1
        int[] nums1 = {0, 2, 3, 4, 5};
        // 预期输出：0
        System.out.println(findMagicIndex(nums1));

        // 测试用例 2
        int[] nums2 = {1, 1, 1};
        // 预期输出：1
        System.out.println(findMagicIndex(nums2));
    }
}