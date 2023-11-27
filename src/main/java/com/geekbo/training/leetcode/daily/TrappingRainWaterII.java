package com.geekbo.training.leetcode.daily;

import java.util.*;

/**
 * 407. Trapping Rain Water II
 * Hard
 * Given an m x n integer matrix heightMap representing the height of each unit cell in a 2D elevation map, return the volume of water it can trap after raining.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
 * Output: 4
 * Explanation: After the rain, water is trapped between the blocks.
 * We have two small ponds 1 and 3 units trapped.
 * The total volume of water trapped is 4.
 * Example 2:
 * <p>
 * <p>
 * Input: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
 * Output: 10
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == heightMap.length
 * n == heightMap[i].length
 * 1 <= m, n <= 200
 * 0 <= heightMap[i][j] <= 2 * 104
 */
public class TrappingRainWaterII {

    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    List<int[]>[] g;
    int start;

    private int[] dijkstra() {
        int[] dist = new int[g.length];
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        dist[start] = 0;
        TreeSet<int[]> tree = new TreeSet<>((u, v) -> u[1] == v[1] ? u[0] - v[0] : u[1] - v[1]);
        tree.add(new int[]{start, 0});
        while (!tree.isEmpty()) {
            int u = tree.first()[0], d = tree.pollFirst()[1];
            for (int[] e : g[u]) {
                int v = e[0], w = e[1];
                if (Math.max(d, w) < dist[v]) {
                    tree.remove(new int[]{v, dist[v]});
                    dist[v] = Math.max(d, w);
                    tree.add(new int[]{v, dist[v]});
                }
            }
        }
        return dist;
    }

    public int trapRainWater(int[][] a) {
        if (a == null || a.length == 0 || a[0].length == 0) return 0;
        int r = a.length, c = a[0].length;

        start = r * c;
        g = new List[r * c + 1];
        for (int i = 0; i < g.length; i++) g[i] = new ArrayList<>();
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++) {
                if (i == 0 || i == r - 1 || j == 0 || j == c - 1) g[start].add(new int[]{i * c + j, 0});
                for (int k = 0; k < 4; k++) {
                    int x = i + dx[k], y = j + dy[k];
                    if (x >= 0 && x < r && y >= 0 && y < c) g[i * c + j].add(new int[]{x * c + y, a[i][j]});
                }
            }

        int ans = 0;
        int[] dist = dijkstra();
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++) {
                int cb = dist[i * c + j];
                if (cb > a[i][j]) ans += cb - a[i][j];
            }

        return ans;
    }

    /**
     * 计算在给定的二维高度图中下雨后可以积累的水的体积
     * <p>
     * 解题思路：
     * <p>
     * 使用优先队列和广度优先搜索来解决问题。
     * 先将边界上的单元格加入优先队列，并标记为已访问。
     * 从优先队列中依次取出最小高度的单元格，并遍历其四个相邻单元格。
     * 如果相邻单元格的位置合法且未访问过，将其标记为已访问，并将其高度与当前单元格的高度进行比较。
     * 如果相邻单元格的高度小于当前单元格的高度，将其加入优先队列，并计算积累的水的体积。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 二维高度图的大小为 m × n，遍历所有单元格的时间复杂度为 O(mn)。
     * 在优先队列中，每个单元格最多被加入和取出一次，所以优先队列的操作时间复杂度为 O(log(mn))。
     * 因此，总的时间复杂度为 O(mn log(mn))。
     * <p>
     * todo:还有错误，结果不对。
     *
     * @param heightMap 二维高度图
     * @return 积累的水的体积
     */
    public int trapRainWater2(int[][] heightMap) {
        // 获取二维高度图的行数和列数
        int m = heightMap.length;
        int n = heightMap[0].length;

        // 创建一个优先队列来存储边界上的单元格
        PriorityQueue<Cell> pq = new PriorityQueue<>((a, b) -> a.height - b.height);

        // 创建一个二维数组来标记已经访问过的单元格
        boolean[][] visited = new boolean[m][n];

        // 将边界上的单元格加入到优先队列中，并标记为已访问
        for (int i = 0; i < m; i++) {
            pq.offer(new Cell(i, 0, heightMap[i][0]));
            visited[i][0] = true;
            pq.offer(new Cell(i, n - 1, heightMap[i][n - 1]));
            visited[i][n - 1] = true;
        }
        for (int j = 0; j < n; j++) {
            pq.offer(new Cell(0, j, heightMap[0][j]));
            visited[0][j] = true;
            pq.offer(new Cell(m - 1, j, heightMap[m - 1][j]));
            visited[m - 1][j] = true;
        }

        // 定义四个方向的偏移量
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // 定义积累的水的体积
        int volume = 0;

        // 从优先队列中依次取出最小高度的单元格，并计算积累的水的体积
        while (!pq.isEmpty()) {
            Cell cell = pq.poll();
            int row = cell.row;
            int col = cell.col;
            int height = cell.height;

            // 遍历四个方向的相邻单元格
            for (int[] dir : dirs) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // 如果新的单元格位置在二维高度图范围内，并且没有访问过
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && !visited[newRow][newCol]) {
                    // 将新的单元格高度与当前高度比较，取较大的值作为新的高度，并标记为已访问
                    int newHeight = Math.max(height, heightMap[newRow][newCol]);
                    visited[newRow][newCol] = true;
                    pq.offer(new Cell(newRow, newCol, newHeight));
                    // 如果新的单元格高度小于当前高度，计算积累的水的体积
                    if (newHeight > height) {
                        volume += newHeight - height;
                    }
                }
            }
        }

        // 返回积累的水的体积
        return volume;
    }

    /**
     * 定义一个单元格类，存储行、列和高度信息
     */
    public static class Cell {
        int row;
        int col;
        int height;

        public Cell(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }

    public static void main(String[] args) {
        TrappingRainWaterII solution = new TrappingRainWaterII();

        // 测试用例
        int[][] heightMap1 = {
                {1, 4, 3, 1, 3, 2},
                {3, 2, 1, 3, 2, 4},
                {2, 3, 3, 2, 3, 1}
        };
        // 两个小池塘被积累了水，总体积为 4
        int expected1 = 4;
        int result1 = solution.trapRainWater(heightMap1);
        System.out.println(result1 == expected1); // 预期输出为 true

        int[][] heightMap2 = {
                {3, 3, 3, 3, 3},
                {3, 2, 2, 2, 3},
                {3, 2, 1, 2, 3},
                {3, 2, 2, 2, 3},
                {3, 3, 3, 3, 3}
        };
        // 一个大池塘被积累了水，总体积为 10
        int expected2 = 10;
        int result2 = solution.trapRainWater(heightMap2);
        System.out.println(result2 == expected2); // 预期输出为 true
    }
}
