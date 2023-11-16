package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

/**
 * 1846. Maximum Element After Decreasing and Rearranging
 * Medium
 * You are given an array of positive integers arr.
 * Perform some operations (possibly none) on arr so that it satisfies these conditions:
 * <p>
 * The value of the first element in arr must be 1.
 * The absolute difference between any 2 adjacent elements must be less than or equal to 1.
 * In other words, abs(arr[i] - arr[i - 1]) <= 1 for each i where 1 <= i < arr.length (0-indexed).
 * abs(x) is the absolute value of x.
 * There are 2 types of operations that you can perform any number of times:
 * <p>
 * Decrease the value of any element of arr to a smaller positive integer.
 * Rearrange the elements of arr to be in any order.
 * Return the maximum possible value of an element in arr after performing the operations to satisfy the conditions.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [2,2,1,2,1]
 * Output: 2
 * Explanation:
 * We can satisfy the conditions by rearranging arr so it becomes [1,2,2,2,1].
 * The largest element in arr is 2.
 * Example 2:
 * <p>
 * Input: arr = [100,1,1000]
 * Output: 3
 * Explanation:
 * One possible way to satisfy the conditions is by doing the following:
 * 1. Rearrange arr so it becomes [1,100,1000].
 * 2. Decrease the value of the second element to 2.
 * 3. Decrease the value of the third element to 3.
 * Now arr = [1,2,3], which satisfies the conditions.
 * The largest element in arr is 3.
 * Example 3:
 * <p>
 * Input: arr = [1,2,3,4,5]
 * Output: 5
 * Explanation: The array already satisfies the conditions, and the largest element is 5.
 */
public class MaximumElementAfterDecreasingAndRearranging {

    public static int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr); // 对arr进行排序

        int n = arr.length;
        arr[0] = 1; // 将第一个元素设置为1

        // 遍历arr并更新元素
        for (int i = 1; i < n; i++) {
            arr[i] = Math.min(arr[i], arr[i - 1] + 1); // 更新元素为前一个元素加1
        }

        return arr[n - 1]; // 返回arr中的最大元素
    }

    public static void main(String[] args) {
        // Test case 1
        int[] arr1 = {2, 2, 1, 2, 1};
        // 通过重新排序arr，变为 [1, 2, 2, 2, 1]
        // 最大元素为2
        int expected1 = 2;
        int result1 = maximumElementAfterDecrementingAndRearranging(arr1);
        System.out.println(result1 == expected1); // Output: true

        // Test case 2
        int[] arr2 = {100, 1, 1000};
        // 通过重新排序arr并将第二个元素减小为2，第三个元素减小为3，变为 [1, 2, 3]
        // 最大元素为3
        int expected2 = 3;
        int result2 = maximumElementAfterDecrementingAndRearranging(arr2);
        System.out.println(result2 == expected2); // Output: true

        // Test case 3
        int[] arr3 = {1, 2, 3, 4, 5}; // arr已经满足条件，最大元素为5
        int expected3 = 5;
        int result3 = maximumElementAfterDecrementingAndRearranging(arr3);
        System.out.println(result3 == expected3); // Output: true

// 3 int[] arr4 = {4, 2, 1, 3}; // 通过重新排序arr并将第一个元素减小为1，变为 [1, 2, 3, 4] // 最大元素为4 int expected4 = 4; int result4 = maximumElementAfterDecrementingAndRearranging(arr4); System.out.println(result4 == expected4); // Output: true
    }
}