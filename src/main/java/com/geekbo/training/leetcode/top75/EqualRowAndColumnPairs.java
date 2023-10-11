package com.geekbo.training.leetcode.top75;

import java.util.*;

/**
 * Given a 0-indexed n x n integer matrix grid, return the number of pairs (ri, cj) such that row ri and column cj are equal.
 * <p>
 * A row and column pair is considered equal if they contain the same elements in the same order (i.e., an equal array).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: grid = [[3,2,1],[1,7,6],[2,7,7]]
 * Output: 1
 * Explanation: There is 1 equal row and column pair:
 * - (Row 2, Column 1): [2,7,7]
 * Example 2:
 * <p>
 * <p>
 * Input: grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
 * Output: 3
 * Explanation: There are 3 equal row and column pairs:
 * - (Row 0, Column 0): [3,1,2,2]
 * - (Row 2, Column 2): [2,4,2,2]
 * - (Row 3, Column 2): [2,4,2,2]
 * <p>
 * <p>
 * 解题思路和时间复杂度分析：
 * <p>
 * 我们首先遍历矩阵的每一行，并使用哈希表（rowMap）来记录每一行的元素。
 * 哈希表的键是由行元素构成的列表，值是出现的次数。
 * 接着，我们遍历矩阵的每一列，构建列元素的列表。然后，我们检查该列元素的列表是否在 rowMap 中。
 * 如果在，我们增加计数，以统计等于的行列对数。
 * 这个优化后的算法的时间复杂度为O(n^2)，因为我们需要遍历矩阵的每一行和每一列，并使用哈希表来快速查找元素。
 *
 */
class EqualRowAndColumnPairs {
    /**
     * 计算相等的行列对数。
     *
     * @param grid 二维整数矩阵
     * @return 等于的行列对数
     */
    public int equalPairs(int[][] grid) {
        int n = grid.length; // 矩阵的大小
        int count = 0; // 等于的行列对数

        // 使用哈希表记录每一行的元素
        Map<List<Integer>, Integer> rowMap = new HashMap<>();

        // 遍历矩阵的每一行
        for (int i = 0; i < n; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(grid[i][j]);
            }
            // 将每一行的元素加入哈希表
            rowMap.put(row, rowMap.getOrDefault(row, 0) + 1);
        }

        // 遍历矩阵的每一列
        for (int j = 0; j < n; j++) {
            List<Integer> column = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                column.add(grid[i][j]);
            }
            // 检查每一列的元素是否在哈希表中
            if (rowMap.containsKey(column)) {
                count += rowMap.get(column);
            }
        }

        return count;
    }

    public int equalPairs2(int[][] grid) {
        int n = grid.length; // 矩阵的大小
        int count = 0; // 等于的行列对数

        // 使用哈希表记录每一行的元素
        Map<String, Integer> rowMap = new HashMap<>();

        // 遍历矩阵的每一行
        for (int i = 0; i < n; i++) {
            int[] row = grid[i];
            String rowStr = Arrays.toString(row);
            // 将每一行的元素转换为字符串并加入哈希表
            rowMap.put(rowStr, rowMap.getOrDefault(rowStr, 0) + 1);
        }

        // 遍历矩阵的每一列
        for (int j = 0; j < n; j++) {
            int[] column = new int[n];
            for (int i = 0; i < n; i++) {
                column[i] = grid[i][j];
            }
            String columnStr = Arrays.toString(column);
            // 检查每一列的元素是否在哈希表中
            if (rowMap.containsKey(columnStr)) {
                count += rowMap.get(columnStr);
            }
        }

        return count;
    }



    public static void main(String[] args) {
        EqualRowAndColumnPairs solution = new EqualRowAndColumnPairs();

        // 测试用例1
        int[][] grid1 = {{3, 2, 1}, {1, 7, 6}, {2, 7, 7}};
        int result1 = solution.equalPairs(grid1);
        System.out.println("测试用例1结果：" + result1); // 预期输出: 1

        // 测试用例2
        int[][] grid2 = {{3, 1, 2, 2}, {1, 4, 4, 5}, {2, 4, 2, 2}, {2, 4, 2, 2}};
        int result2 = solution.equalPairs(grid2);
        System.out.println("测试用例2结果：" + result2); // 预期输出: 3
    }
}
