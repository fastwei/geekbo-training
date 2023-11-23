package com.geekbo.training.leetcode.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1630. Arithmetic Subarrays
 * Medium
 * A sequence of numbers is called arithmetic if it consists of at least two elements,
 * and the difference between every two consecutive elements is the same. More formally,
 * a sequence s is arithmetic if and only if s[i+1] - s[i] == s[1] - s[0] for all valid i.
 * <p>
 * For example, these are arithmetic sequences:
 * <p>
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * The following sequence is not arithmetic:
 * <p>
 * 1, 1, 2, 5, 7
 * You are given an array of n integers, nums, and two arrays of m integers each, l and r,
 * representing the m range queries, where the ith query is the range [l[i], r[i]]. All the arrays are 0-indexed.
 * <p>
 * Return a list of boolean elements answer, where answer[i] is true if the subarray nums[l[i]], nums[l[i]+1], ... ,
 * nums[r[i]] can be rearranged to form an arithmetic sequence, and false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,6,5,9,3,7], l = [0,0,2], r = [2,3,5]
 * Output: [true,false,true]
 * Explanation:
 * In the 0th query, the subarray is [4,6,5]. This can be rearranged as [6,5,4], which is an arithmetic sequence.
 * In the 1st query, the subarray is [4,6,5,9]. This cannot be rearranged as an arithmetic sequence.
 * In the 2nd query, the subarray is [5,9,3,7]. This can be rearranged as [3,5,7,9], which is an arithmetic sequence.
 * Example 2:
 * <p>
 * Input: nums = [-12,-9,-3,-12,-6,15,20,-25,-20,-15,-10], l = [0,1,6,4,8,7], r = [4,4,9,7,9,10]
 * Output: [false,true,false,false,true,true]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * m == l.length
 * m == r.length
 * 2 <= n <= 500
 * 1 <= m <= 500
 * 0 <= l[i] < r[i] < n
 * -105 <= nums[i] <= 105
 */
class ArithmeticSubarrays {
    /**
     * 解题思路：
     * <p>
     * 对于每个查询，将nums数组中指定范围的元素复制到一个新的子数组中。
     * 对子数组进行排序。
     * 检查排序后的子数组是否是等差数列，即检查相邻元素之间的差值是否相等。
     * 将结果添加到结果列表中。
     * 返回结果列表。
     * 算法复杂度分析：
     * <p>
     * 假设n是nums数组的长度，m是查询的数量。
     * 对于每个查询，复制子数组的时间复杂度为O(r[i]-l[i])，排序子数组的时间复杂度为O((r[i]-l[i])log(r[i]-l[i]))，
     * 检查子数组是否是等差数列的时间复杂度为O(r[i]-l[i])。
     * 总的时间复杂度为O(m*(r[i]-l[i])log(r[i]-l[i]))。
     * 空间复杂度为O(r[i]-l[i])。
     *
     * @param nums
     * @param l
     * @param r
     * @return
     */
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> result = new ArrayList<>();

        // Iterate through each query
        for (int i = 0; i < l.length; i++) {
            int left = l[i];
            int right = r[i];

            // Create a subarray from the original array
            int[] subarray = Arrays.copyOfRange(nums, left, right + 1);

            // Sort the subarray
            Arrays.sort(subarray);

            // Check if the subarray is arithmetic
            boolean isArithmetic = true;
            int diff = subarray[1] - subarray[0];
            for (int j = 2; j < subarray.length; j++) {
                if (subarray[j] - subarray[j - 1] != diff) {
                    isArithmetic = false;
                    break;
                }
            }

            result.add(isArithmetic);
        }

        return result;
    }

    public static void main(String[] args) {
        ArithmeticSubarrays solution = new ArithmeticSubarrays();

        int[] nums1 = {4, 6, 5, 9, 3, 7};
        int[] l1 = {0, 0, 2};
        int[] r1 = {2, 3, 5};
        System.out.println(solution.checkArithmeticSubarrays(nums1, l1, r1)); // Expected output: [true, false, true]

        int[] nums2 = {-12, -9, -3, -12, -6, 15, 20, -25, -20, -15, -10};
        int[] l2 = {0, 1, 6, 4, 8, 7};
        int[] r2 = {4, 4, 9, 7, 9, 10};
        System.out.println(solution.checkArithmeticSubarrays(nums2, l2, r2)); // Expected output: [false, true, false, false, true, true]
    }
}
