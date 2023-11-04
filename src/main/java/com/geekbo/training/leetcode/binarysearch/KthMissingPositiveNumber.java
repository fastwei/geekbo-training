package com.geekbo.training.leetcode.binarysearch;


/**
 * Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
 * <p>
 * Return the kth positive integer that is missing from this array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [2,3,4,7,11], k = 5
 * Output: 9
 * Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...].
 * The 5th missing positive integer is 9.
 * Example 2:
 * <p>
 * Input: arr = [1,2,3,4], k = 2
 * Output: 6
 * Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 1000
 * 1 <= arr[i] <= 1000
 * 1 <= k <= 1000
 * arr[i] < arr[j] for 1 <= i < j <= arr.length
 * <p>
 * <p>
 * Follow up:
 * <p>
 * Could you solve this problem in less than O(n) complexity?
 */
public class KthMissingPositiveNumber {
    /**
     * 解题思路：
     * <p>
     * 由于数组已经按照严格递增的顺序排列，我们可以使用二分查找来确定第k个缺失的正整数。
     * 使用两个指针left和right分别指向数组的起始和结束位置。
     * 在每一次二分查找过程中，我们计算中间元素的缺失正整数的个数missing。
     * 如果missing小于k，则说明缺失的正整数在右半部分，更新left为mid + 1；
     * 如果missing大于等于k，则说明缺失的正整数在左半部分，更新right为mid - 1。
     * 最终，当left指向的位置为第k个缺失的正整数应该出现的位置时，
     * 我们可以根据公式arr[left] - (arr[left] - left - 1 - k) = left + k + 1计算出第k个缺失的正整数。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：二分查找的时间复杂度为O(logn)，其中n为数组的长度。因此，总的时间复杂度为O(logn)。
     * 空间复杂度：仅使用了常数个额外变量，因此空间复杂度为O(1)。
     *
     * @param arr
     * @param k
     * @return
     */
    public static int findKthPositive(int[] arr, int k) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Calculate the number of missing positive integers before the middle element
            int missing = arr[mid] - mid - 1;

            if (missing < k) {
                // If the number of missing positive integers is less than k, search on the right side
                left = mid + 1;
            } else {
                // If the number of missing positive integers is greater than or equal to k, search on the left side
                right = mid - 1;
            }
        }

        // At this point, left is pointing to the element where the kth missing positive integer should be
        // The missing positive integer is arr[left] - (arr[left] - left - 1 - k) = left + k + 1
        return left + k;
    }

    public static void main(String[] args) {
        // Test case 1
        int[] arr1 = {2, 3, 4, 7, 11};
        int k1 = 5;
        int expected1 = 9;
        int result1 = findKthPositive(arr1, k1);
        System.out.println("Test case 1:");
        System.out.println("Expected: " + expected1);
        System.out.println("Result: " + result1);
        System.out.println();

        // Test case 2
        int[] arr2 = {1, 2, 3, 4};
        int k2 = 2;
        int expected2 = 6;
        int result2 = findKthPositive(arr2, k2);
        System.out.println("Test case 2:");
        System.out.println("Expected: " + expected2);
        System.out.println("Result: " + result2);
    }
}
