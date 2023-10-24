package com.geekbo.training.leetcode.skill;

import java.util.Arrays;

public class CanMakeArithmeticProgressionFromSequence {
    /**
     * 判断给定的数组是否可以重排成等差数列
     * <p>
     * 解题思路：
     * <p>
     * 首先对给定的数组进行排序，以便找到最小和最大的元素。
     * 计算排序后相邻元素之间的差值，作为等差数列的公差。
     * 遍历排序后的数组，判断每个相邻元素之间的差值是否等于公差。
     * 如果存在任意两个相邻元素之间的差值不等于公差，则说明无法将数组重排成等差数列，返回 false。
     * 如果所有相邻元素之间的差值都等于公差，则返回 true。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 对数组进行排序的时间复杂度为 O(n log n)，其中 n 是数组的长度。
     * 遍历数组的时间复杂度为 O(n)。
     * 因此，总的时间复杂度为 O(n log n)。
     *
     * @param arr 给定的整数数组
     * @return 是否可以重排成等差数列
     */
    public static boolean canMakeArithmeticProgression(int[] arr) {
        // 对数组进行排序
        Arrays.sort(arr);

        // 计算等差数列的公差
        int diff = arr[1] - arr[0];

        // 遍历数组，判断是否满足等差数列的条件
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != diff) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 5, 1};
        // 预期输出：true
        System.out.println(canMakeArithmeticProgression(arr1));

        int[] arr2 = {1, 2, 4};
        // 预期输出：false
        System.out.println(canMakeArithmeticProgression(arr2));
    }
}
