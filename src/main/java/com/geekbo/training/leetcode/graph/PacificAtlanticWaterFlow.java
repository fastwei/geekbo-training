package com.geekbo.training.leetcode.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 417. Pacific Atlantic Water Flow
 * Medium
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean.
 * The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
 * <p>
 * The island is partitioned into a grid of square cells.
 * You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
 * <p>
 * The island receives a lot of rain, and the rain water can flow to neighboring cells
 * directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height.
 * Water can flow from any cell adjacent to an ocean into the ocean.
 * <p>
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci]
 * denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
 * [0,4]: [0,4] -> Pacific Ocean
 * [0,4] -> Atlantic Ocean
 * [1,3]: [1,3] -> [0,3] -> Pacific Ocean
 * [1,3] -> [1,4] -> Atlantic Ocean
 * [1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean
 * [1,4] -> Atlantic Ocean
 * [2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean
 * [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
 * [3,0]: [3,0] -> Pacific Ocean
 * [3,0] -> [4,0] -> Atlantic Ocean
 * [3,1]: [3,1] -> [3,0] -> Pacific Ocean
 * [3,1] -> [4,1] -> Atlantic Ocean
 * [4,0]: [4,0] -> Pacific Ocean
 * [4,0] -> Atlantic Ocean
 * Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
 * Example 2:
 * <p>
 * Input: heights = [[1]]
 * Output: [[0,0]]
 * Explanation: The water can flow from the only cell to the Pacific and Atlantic oceans.
 */
public class PacificAtlanticWaterFlow {

    /**
     * 寻找可以流向太平洋和大西洋的格子坐标
     * 解题思路：
     * <p>
     * 首先创建两个布尔型二维数组canReachPacific和canReachAtlantic，用来记录能否流向太平洋或大西洋的格子。
     * 从太平洋和大西洋的边界出发，使用深度优先搜索（DFS）的方式标记可以流向太平洋和大西洋的格子。
     * 最后遍历整个矩阵，找到同时能够流向太平洋和大西洋的格子，加入结果集合。
     * 返回结果集合。
     * 算法复杂度分析：
     * <p>
     * 遍历整个矩阵，时间复杂度为O(m * n)，其中m和n分别是矩阵的行数和列数。
     * 使用深度优先搜索标记可以流向太平洋和大西洋的格子，时间复杂度为O(m * n)。
     * 因此，总的时间复杂度为O(m * n)。
     * 空间复杂度分析：
     * <p>
     * 使用了两个布尔型二维数组来记录可以流向太平洋和大西洋的格子，空间复杂度为O(m * n)。
     * 除此之外，还使用了一个结果集合来存储符合条件的格子坐标，空间复杂度取决于结果集合的大小，最坏情况下为O(m * n)。
     * 总的空间复杂度为O(m * n)。
     *
     * @param heights 格子高度数组
     * @return 可以流向太平洋和大西洋的格子坐标
     */
    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return result;
        }

        int m = heights.length; // 行数
        int n = heights[0].length; // 列数

        boolean[][] canReachPacific = new boolean[m][n]; // 记录可以流向太平洋的格子
        boolean[][] canReachAtlantic = new boolean[m][n]; // 记录可以流向大西洋的格子

        // 从边界出发，深度优先搜索，标记可以流向太平洋的格子
        for (int i = 0; i < m; i++) {
            dfs(heights, canReachPacific, Integer.MIN_VALUE, i, 0);
        }
        for (int j = 0; j < n; j++) {
            dfs(heights, canReachPacific, Integer.MIN_VALUE, 0, j);
        }

        // 从边界出发，深度优先搜索，标记可以流向大西洋的格子
        for (int i = 0; i < m; i++) {
            dfs(heights, canReachAtlantic, Integer.MIN_VALUE, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            dfs(heights, canReachAtlantic, Integer.MIN_VALUE, m - 1, j);
        }

        // 寻找同时可以流向太平洋和大西洋的格子
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (canReachPacific[i][j] && canReachAtlantic[i][j]) {
                    List<Integer> coordinates = new ArrayList<>();
                    coordinates.add(i);
                    coordinates.add(j);
                    result.add(coordinates);
                }
            }
        }

        return result;
    }

    private static void dfs(int[][] heights, boolean[][] visited, int prevHeight, int row, int col) {
        int m = heights.length; // 行数
        int n = heights[0].length; // 列数

        // 边界判断
        if (row < 0 || row >= m || col < 0 || col >= n || visited[row][col] || heights[row][col] < prevHeight) {
            return;
        }

        visited[row][col] = true; // 标记当前格子已访问

        // 深度优先搜索四个方向
        dfs(heights, visited, heights[row][col], row - 1, col); // 上
        dfs(heights, visited, heights[row][col], row + 1, col); // 下
        dfs(heights, visited, heights[row][col], row, col - 1); // 左
        dfs(heights, visited, heights[row][col], row, col + 1); // 右
    }

    public static void main(String[] args) {
        // 测试用例1
        int[][] heights1 = {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };
        // 预期输出：[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]]
        System.out.println(pacificAtlantic(heights1));

        // 测试用例2
        int[][] heights2 = {{1}};
        // 预期输出：[[0, 0]]
        System.out.println(pacificAtlantic(heights2));
    }
}
