package com.geekbo.training.leetcode.daily;

import java.util.HashMap;
import java.util.Map;

/**
 * 2661. First Completely Painted Row or Column
 * Medium
 * You are given a 0-indexed integer array arr, and an m x n integer matrix mat. arr and mat both contain all the integers in the range [1, m * n].
 * <p>
 * Go through each index i in arr starting from index 0 and paint the cell in mat containing the integer arr[i].
 * <p>
 * Return the smallest index i at which either a row or a column will be completely painted in mat.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * image explanation for example 1
 * Input: arr = [1,3,4,2], mat = [[1,4],[2,3]]
 * Output: 2
 * Explanation: The moves are shown in order, and both the first row and second column of the matrix become fully painted at arr[2].
 * Example 2:
 * <p>
 * image explanation for example 2
 * Input: arr = [2,8,7,4,1,3,5,6,9], mat = [[3,2,5],[1,4,6],[8,7,9]]
 * Output: 3
 * Explanation: The second column becomes fully painted at arr[3].
 */
class FirstCompletelyPaintedRowColumn {


    /**
     * 解题思路： 首先，创建一个映射表 map，用于记录每个数字在 arr 数组中的索引。
     * 然后，初始化最小索引 min 为 arr 数组的长度。
     * 接下来，使用两层循环逐行遍历矩阵 mat，在每一行中找出数字在 arr 数组中的最大索引值，并更新 max。
     * 然后，更新最小索引 min 为 max 和当前的 min 中的较小值。
     * 接着，使用两层循环逐列遍历矩阵 mat，在每一列中找出数字在 arr 数组中的最大索引值，并更新 max。
     * 同样地，更新最小索引 min 为 max 和当前的 min 中的较小值。 最后，返回最小索引 min。
     * <p>
     * 算法复杂度分析： 设 arr 数组的长度为 k，矩阵 mat 的大小为 m x n。
     * 创建映射表 map 需要遍历 arr 数组，时间复杂度为 O(k)。
     * 逐行遍历矩阵 mat，需要两层循环，时间复杂度为 O(m x n)。
     * 同理，逐列遍历矩阵 mat，时间复杂度为 O(m x n)。
     * 因此，总的时间复杂度为 O(k + m x n)。
     * <p>
     * 在空间复杂度上，除了输入参数外，额外使用了一个映射表 map，空间复杂度为 O(k)。
     *
     * @param arr
     * @param mat
     * @return
     */
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        // 创建一个映射表，记录每个数字在 arr 数组中的索引
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }

        // 初始化最小索引为 arr 数组的长度
        int min = arr.length;

        // 逐行检查
        for (int i = 0; i < mat.length; i++) {
            int max = 0;
            for (int j = 0; j < mat[0].length; j++) {
                // 找出当前行中数字在 arr 数组中的最大索引值
                max = Math.max(max, map.get(mat[i][j]));
            }
            // 更新最小索引
            min = Math.min(min, max);
        }

        // 逐列检查
        for (int i = 0; i < mat[0].length; i++) {
            int max = 0;
            for (int j = 0; j < mat.length; j++) {
                // 找出当前列中数字在 arr 数组中的最大索引值
                max = Math.max(max, map.get(mat[j][i]));
            }
            // 更新最小索引
            min = Math.min(min, max);
        }

        // 返回最小索引
        return min;
    }
}