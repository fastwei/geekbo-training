package com.geekbo.training.leetcode.crackinterview109;

import java.util.Arrays;

/**
 * 面试题 16.06. 最小差
 * 中等
 * 给定两个整数数组a和b，计算具有最小差绝对值的一对数值（每个数组中取一个值），并返回该对数值的差
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：{1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}
 * 输出：3，即数值对(11, 8)
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= a.length, b.length <= 100000
 * -2147483648 <= a[i], b[i] <= 2147483647
 * 正确结果在区间 [0, 2147483647] 内
 */
public class SmallestDifference {
    /**
     * 解题思路：
     * <p>
     * 首先对两个数组进行排序，这样可以使得相邻的数更有可能是差值最小的一对数值。
     * 使用双指针遍历数组，比较当前指针指向的数值的差值，并更新最小差值。
     * 如果a[i]小于b[j]，则移动指向a的指针，否则移动指向b的指针，以便寻找更小的差值。
     * 最后返回最小差值。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 排序数组的时间复杂度为O(nlogn)，其中n是数组的长度。
     * 双指针遍历数组的时间复杂度为O(n)，其中n是数组的长度。
     * 因此，总的时间复杂度为O(nlogn)。
     * todo:还有一个测试用例没通过
     * @param a
     * @param b
     * @return
     */
    public static int smallestDifference(int[] a, int[] b) {
        // 首先对两个数组进行排序
        Arrays.sort(a);
        Arrays.sort(b);

        int minDiff = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;

        // 使用双指针遍历数组，找到差值最小的一对数值
        while (i < a.length && j < b.length) {
            long diff = Math.abs((long)a[i] - (long)b[j]);
            minDiff = Math.min(minDiff, (int)diff);

            // 如果a[i]小于b[j]，则移动指向a的指针，否则移动指向b的指针
            if (a[i] < b[j]) {
                i++;
            } else {
                j++;
            }
        }

        return minDiff;
    }

    public int smallestDifference2(int[] a, int[] b) {

        Arrays.sort(a);
        Arrays.sort(b);

        int i = 0,j = 0;
        long min = Long.MAX_VALUE;

        while (i < a.length && j <b.length) {
            if (a[i] == b[j]) return 0;
            else if (a[i] > b[j]) {
                min = Math.min(min,(long) a[i] -(long) b[j]);
                j ++;
            } else {
                min = Math.min(min,(long) b[j] -(long) a[i]);
                i ++;
            }
        }
        return (int)min;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 15, 11, 2};
        int[] b = {23, 127, 235, 19, 8};

        int minDiff = smallestDifference(a, b);
        System.out.println("最小差：" + minDiff);
    }
}
