package com.geekbo.training.leetcode.top150.hard;

/**
 *
 * Given two sorted arrays nums1 and nums2 of size m and n respectively,
 * return the median of the two sorted arrays.
 *
 * The overall run time complexity should be O(log (m+n)).
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 * Example 2:
 *
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 *
 *
 * todo:加深理解
 */
public class MedianOfTwoSortedArrays {

    /**
     * 解题思路：
     * <p>
     * 题目要求在两个有序数组中找到中位数，且时间复杂度为O(log (m+n))。
     * 由于数组是有序的，我们可以使用二分查找的思想来解决问题。
     * 假设两个数组的长度分别为m和n，我们需要在两个数组中找到一个划分，使得左边部分的元素个数等于右边部分的元素个数，并且左边部分的元素都小于右边部分的元素。
     * 假设第一个数组的划分点为i，第二个数组的划分点为j，我们需要满足以下条件：
     * nums1[i-1] <= nums2[j]，即划分点左边的元素都小于等于划分点右边的元素。
     * nums2[j-1] <= nums1[i]，即划分点左边的元素都小于等于划分点右边的元素。
     * 根据上述条件，我们可以使用二分查找的思想，初始化左边界left为0，右边界right为第一个数组的长度m。同时，计算出中位数划分的位置halfLen = (m + n + 1) / 2。
     * 在每一次循环中，计算第一个数组的划分点i和第二个数组的划分点j，根据划分点的位置来判断是否满足条件。
     * 如果第一个数组的划分点i小于右边界right，且第二个数组的划分点j-1的值大于第一个数组的划分点i，说明划分点在第一个数组的右边，需要将左边界left右移。
     * 如果第一个数组的划分点i大于左边界left，且第一个数组的划分点i-1的值大于第二个数组的划分点j，说明划分点在第一个数组的左边，需要将右边界right左移。
     * 如果划分点满足条件，则找到了划分点位置，根据划分点位置将数组分为左右两部分。根据奇偶性来计算中位数：
     * 如果数组的总长度为奇数，返回左边部分的最大值；
     * 如果数组的总长度为偶数，返回左边部分的最大值和右边部分的最小值的平均值。
     * 如果没有找到划分点，返回0.0作为默认值。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 由于每次循环将搜索范围减少一半，所以时间复杂度为O(log (m+n))。
     * 空间复杂度为O(1)。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        // 确保nums1的长度小于等于nums2的长度
        if (m > n) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;

            int tempLen = m;
            m = n;
            n = tempLen;
        }

        int left = 0;
        int right = m;
        int halfLen = (m + n + 1) / 2;

        while (left <= right) {
            int i = left + (right - left) / 2;
            int j = halfLen - i;

            if (i < right && nums2[j - 1] > nums1[i]) {
                left = i + 1;
            } else if (i > left && nums1[i - 1] > nums2[j]) {
                right = i - 1;
            } else {
                int maxLeft;
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }

                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight;
                if (i == m) {
                    minRight = nums2[j];
                } else if (j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums1[i], nums2[j]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }

        return 0.0;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println(findMedianSortedArrays(nums1, nums2)); // Expected: 2.0

        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        System.out.println(findMedianSortedArrays(nums3, nums4)); // Expected: 2.5
    }
}
