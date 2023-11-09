package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

/**
 * 1982. Find Array Given Subset Sums
 * Hard
 * You are given an integer n representing the length of an unknown array
 * that you are trying to recover. You are also given an array sums containing
 * the values of all 2n subset sums of the unknown array (in no particular order).
 * <p>
 * Return the array ans of length n representing the unknown array.
 * If multiple answers exist, return any of them.
 * <p>
 * An array sub is a subset of an array arr if sub can be obtained from arr by deleting some
 * (possibly zero or all) elements of arr.
 * The sum of the elements in sub is one possible subset sum of arr.
 * The sum of an empty array is considered to be 0.
 * <p>
 * Note: Test cases are generated such that there will always be at least one correct answer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3, sums = [-3,-2,-1,0,0,1,2,3]
 * Output: [1,2,-3]
 * Explanation: [1,2,-3] is able to achieve the given subset sums:
 * - []: sum is 0
 * - [1]: sum is 1
 * - [2]: sum is 2
 * - [1,2]: sum is 3
 * - [-3]: sum is -3
 * - [1,-3]: sum is -2
 * - [2,-3]: sum is -1
 * - [1,2,-3]: sum is 0
 * Note that any permutation of [1,2,-3] and also any permutation of [-1,-2,3] will also be accepted.
 * Example 2:
 * <p>
 * Input: n = 2, sums = [0,0,0,0]
 * Output: [0,0]
 * Explanation: The only correct answer is [0,0].
 * Example 3:
 * <p>
 * Input: n = 4, sums = [0,0,5,5,4,-1,4,9,9,-1,4,3,4,8,3,8]
 * Output: [0,-1,4,5]
 * Explanation: [0,-1,4,5] is able to achieve the given subset sums.
 */
public class FindArrayGivenSubsetSums {
    /**
     * 这个算法的时间复杂度为O(nlogn)，空间复杂度为O(n)。
     * <p>
     * 算法中的循环是根据指定的n值进行的，因此时间复杂度为O(n)。
     * 在每次循环中，使用Arrays.binarySearch函数对sums数组进行二分查找，其时间复杂度为O(logn)。
     * 另外，算法中还有一次排序操作Arrays.sort，其时间复杂度为O(nlogn)。因此，整个算法的时间复杂度为O(nlogn)。
     * <p>
     * 对于空间复杂度，算法使用了一个大小为n的结果数组res，因此空间复杂度为O(n)。
     * <p>
     * 综上所述，该算法的时间复杂度为O(nlogn)，空间复杂度为O(n)。
     */
    public int[] recoverArray(int n, int[] sums) {
        Arrays.sort(sums);
        int m = sums.length;
        int[] res = new int[n];
        int new_zero_spot = 0;
        for (int i = 0; i < n; ++i) {
            int diff = sums[1] - sums[0], p = 0, k = 0;
            for (int j = 0; j < m; ++j) {
                if (k < p && sums[k] == sums[j]) k++;
                else sums[p++] = sums[j] + diff;
            }
            if (Arrays.binarySearch(sums, 0, m / 2, new_zero_spot) >= 0) {
                res[i] = -diff;
            } else {
                res[i] = diff;
                new_zero_spot += diff;
            }
            m /= 2;
        }
        return res;
    }

    public static void main(String[] args) {
        // 测试用例1
        int n1 = 3;
        int[] sums1 = {-3, -2, -1, 0, 0, 1, 2, 3};
        int[] expected1 = {1, 2, -3};
        int[] result1 = recoverArray(n1, sums1);
        System.out.println("Input: n = " + n1 + ", sums = " + Arrays.toString(sums1));
        System.out.println("Output: " + Arrays.toString(result1));
        System.out.println("Expected: " + Arrays.toString(expected1));
        System.out.println();

        // 测试用例2
        int n2 = 2;
        int[] sums2 = {0, 0, 0, 0};
        int[] expected2 = {0, 0};
        int[] result2 = recoverArray(n2, sums2);
        System.out.println("Input: n = " + n2 + ", sums = " + Arrays.toString(sums2));
        System.out.println("Output: " + Arrays.toString(result2));
        System.out.println("Expected: " + Arrays.toString(expected2));
        System.out.println();

        // 测试用例3
        int n3 = 4;
        int[] sums3 = {0, 0, 5, 5, 4, -1, 4, 9, 9, -1, 4, 3, 4, 8, 3, 8};
        int[] expected3 = {0, -1, 4, 5};
        int[] result3 = recoverArray(n3, sums3);
        System.out.println("Input: n = " + n3 + ", sums = " + Arrays.toString(sums3));
        System.out.println("Output: " + Arrays.toString(result3));
        System.out.println("Expected: " + Arrays.toString(expected3));
        System.out.println();
    }
}
