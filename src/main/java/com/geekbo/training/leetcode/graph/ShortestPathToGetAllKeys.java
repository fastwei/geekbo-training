package com.geekbo.training.leetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 864. Shortest Path to Get All Keys
 * Hard
 * <p>
 * You are given an m x n grid grid where:
 * <p>
 * '.' is an empty cell.
 * '#' is a wall.
 * '@' is the starting point.
 * Lowercase letters represent keys.
 * Uppercase letters represent locks.
 * You start at the starting point and one move consists of walking one space in one of the four cardinal directions.
 * You cannot walk outside the grid, or walk into a wall.
 * <p>
 * If you walk over a key, you can pick it up and you cannot walk over a lock unless you have its corresponding key.
 * <p>
 * For some 1 <= k <= 6, there is exactly one lowercase
 * and one uppercase letter of the first k letters of the English alphabet in the grid.
 * This means that there is exactly one key for each lock, and one lock for each key;
 * and also that the letters used to represent the keys and locks were chosen in the same order as the English alphabet.
 * <p>
 * Return the lowest number of moves to acquire all keys. If it is impossible, return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: grid = ["@.a..","###.#","b.A.B"]
 * Output: 8
 * Explanation: Note that the goal is to obtain all the keys not to open all the locks.
 * Example 2:
 * <p>
 * <p>
 * Input: grid = ["@..aA","..B#.","....b"]
 * Output: 6
 * Example 3:
 * <p>
 * <p>
 * Input: grid = ["@Aa"]
 * Output: -1
 */
public class ShortestPathToGetAllKeys {
    /**
     * 计算获取所有钥匙的最少移动次数
     * 解题思路：
     * -我们可以使用广度优先搜索（BFS）来解决这个问题。首先，我们遍历二维字符网格，统计起点、钥匙和锁的位置。
     * -然后，我们使用一个队列和一个三维布尔数组来进行BFS搜索。
     * 队列中存储状态类对象，表示当前位置、已获取的钥匙的二进制表示。
     * -在每一步搜索中，我们从队列中取出一个状态，然后尝试移动到当前位置的上、下、左、右四个相邻位置。
     * 如果相邻位置合法且可达，则将其添加到队列中，并更新已获取的钥匙的状态。
     * -当队列为空时，表示无法获取所有的钥匙，返回-1。否则，返回最少移动次数。
     * <p>
     * 算法复杂度分析：
     * -遍历二维字符网格的时间复杂度为 O(m*n)，其中 m 是网格的行数，n 是网格的列数。
     * -BFS搜索的时间复杂度为 O(m*n*2^k)，其中 k 是钥匙的数量。
     * 每个位置最多有 2^k 种状态（每个钥匙有取或不取两种状态）。
     * -BFS搜索的空间复杂度为 O(m*n*2^k)，用于存储队列和三维布尔数组。
     *
     * @param grid 二维字符网格
     * @return 最少移动次数
     */
    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length;
        int n = grid[0].length();

        // 统计起点、钥匙和锁的位置
        int startRow = -1, startCol = -1;
        int numKeys = 0;
        int[] keyRows = new int[6];
        int[] keyCols = new int[6];
        int[] lockRows = new int[6];
        int[] lockCols = new int[6];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);
                if (c == '@') {
                    startRow = i;
                    startCol = j;
                } else if (c >= 'a' && c <= 'f') {
                    int keyIndex = c - 'a';
                    keyRows[keyIndex] = i;
                    keyCols[keyIndex] = j;
                    numKeys++;
                } else if (c >= 'A' && c <= 'F') {
                    int lockIndex = c - 'A';
                    lockRows[lockIndex] = i;
                    lockCols[lockIndex] = j;
                }
            }
        }

        // BFS搜索
        Queue<State> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[m][n][64];
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        queue.offer(new State(startRow, startCol, 0));
        visited[startRow][startCol][0] = true;
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                State curr = queue.poll();
                if (curr.keys == (1 << numKeys) - 1) {
                    return steps;
                }
                for (int[] dir : directions) {
                    int newRow = curr.row + dir[0];
                    int newCol = curr.col + dir[1];
                    if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n) {
                        char nextCell = grid[newRow].charAt(newCol);
                        if (nextCell == '#') {
                            continue;
                        }
                        int keys = curr.keys;
                        if (nextCell >= 'a' && nextCell <= 'f') {
                            keys |= 1 << (nextCell - 'a');
                        }
                        if (nextCell >= 'A' && nextCell <= 'F' && (keys & (1 << (nextCell - 'A'))) == 0) {
                            continue;
                        }
                        if (!visited[newRow][newCol][keys]) {
                            visited[newRow][newCol][keys] = true;
                            queue.offer(new State(newRow, newCol, keys));
                        }
                    }
                }
            }
            steps++;
        }

        return -1;
    }

    /**
     * 状态类
     */
    private static class State {
        int row;
        int col;
        int keys;

        public State(int row, int col, int keys) {
            this.row = row;
            this.col = col;
            this.keys = keys;
        }
    }

    public static void main(String[] args) {
        // 创建解法对象
        ShortestPathToGetAllKeys shortestPathToGetAllKeys = new ShortestPathToGetAllKeys();

        // 测试用例1
        // 预期输出: 8
        String[] grid1 = {"@.a..", "###.#", "b.A.B"};
        int result1 = shortestPathToGetAllKeys.shortestPathAllKeys(grid1);
        System.out.println(result1);


        // 测试用例2
        // 预期输出: 6
        String[] grid2 = {"@..aA", "..B#.", "....b"};
        int result2 = shortestPathToGetAllKeys.shortestPathAllKeys(grid2);
        System.out.println(result2);

        // 测试用例3
        // 预期输出: -1
        String[] grid3 = {"@Aa"};
        int result3 = shortestPathToGetAllKeys.shortestPathAllKeys(grid3);
        System.out.println(result3);
    }
}
