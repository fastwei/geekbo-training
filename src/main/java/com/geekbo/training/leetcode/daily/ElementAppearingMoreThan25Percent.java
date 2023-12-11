package com.geekbo.training.leetcode.daily;

/**
 * 1287. Element Appearing More Than 25% In Sorted Array
 * Easy
 * Given an integer array sorted in non-decreasing order,
 * there is exactly one integer in the array that occurs more than 25% of the time,return that integer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [1,2,2,6,6,6,6,7,10]
 * Output: 6
 * Example 2:
 * <p>
 * Input: arr = [1,1]
 * Output: 1
 */
public class ElementAppearingMoreThan25Percent {
    /**
     * 找出数组中出现次数超过25%的元素
     * 解题思路：
     * <p>
     * 数组已经按照非递减顺序排序，所以出现次数超过25%的元素一定是连续的。
     * 遍历数组，如果当前元素与前一个元素相等，则计数器加1；否则，计数器重置为1。
     * 如果计数器的值超过了阈值（数组长度的1/4），则返回当前元素。
     * 如果遍历结束后还没有返回值，说明最后一个元素是出现次数超过25%的元素。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：O(n)，其中n是数组的长度。
     * 空间复杂度：O(1)。
     *
     * @param arr 给定的整数数组
     * @return 出现次数超过25%的元素
     */
    public static int findSpecialInteger(int[] arr) {
        int n = arr.length;
        int count = 1;
        int threshold = n / 4;

        for (int i = 1; i < n; i++) {
            if (arr[i] == arr[i - 1]) {
                count++;
            } else {
                count = 1;
            }

            if (count > threshold) {
                return arr[i];
            }
        }

        return arr[n - 1];
    }

    public static void main(String[] args) {
        // 测试用例1
        int[] arr1 = {1, 2, 2, 6, 6, 6, 6, 7, 10};
        // 预期输出：6
        int result1 = findSpecialInteger(arr1);
        System.out.println(result1);

        // 测试用例2
        int[] arr2 = {1, 1};
        // 预期输出：1
        int result2 = findSpecialInteger(arr2);
        System.out.println(result2);
    }
}
