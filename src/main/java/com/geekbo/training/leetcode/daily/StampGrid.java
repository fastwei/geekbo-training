package com.geekbo.training.leetcode.daily;

import java.util.ArrayList;
import java.util.List;

public class StampGrid {
    public static boolean possibleToStamp(int[][] M, int h, int w) {
        int m = M.length, n = M[0].length;
        int[][] A = new int[m + 1][n + 1], B = new int[m + 1][n + 1];
        int[][] good = new int[m][n];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                A[i + 1][j + 1] = A[i + 1][j] + A[i][j + 1] - A[i][j] + (1 - M[i][j]);

                if (i + 1 >= h && j + 1 >= w) {
                    int x = i + 1 - h, y = j + 1 - w;
                    if (A[i + 1][j + 1] - A[x][j + 1] - A[i + 1][y] + A[x][y] == w * h)
                        good[i][j]++;
                }
            }
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                B[i + 1][j + 1] = B[i + 1][j] + B[i][j + 1] - B[i][j] + good[i][j];
            }
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int x = Math.min(i + h, m), y = Math.min(j + w, n);
                if (M[i][j] == 0 && B[x][y] - B[i][y] - B[x][j] + B[i][j] == 0)
                    return false;
            }
        }

        return true;
    }

    /**
     * 解题思路：
     * 1.我们使用两层循环来遍历矩阵中的每个位置，尝试放置邮票。
     * 2.在每个位置，我们检查该位置是否可以放置邮票。如果可以，我们将该位置标记为-1，并将其添加到邮票位置列表中。
     * 3.在检查是否所有的空格子都被覆盖时，我们遍历整个矩阵，并检查是否存在未被覆盖的空格子（值为0）。
     * 4.如果所有的空格子都被覆盖，我们返回true；否则，返回false。
     * <p>
     * 算法复杂度分析：
     * -时间复杂度：假设矩阵的大小为m x n，邮票的尺寸为stampHeight x stampWidth。在最坏情况下，每个位置都需要尝试放置邮票，
     * 因此时间复杂度为O(m x n x stampHeight x stampWidth)。
     * -空间复杂度：我们使用了一个邮票位置列表来保存放置邮票的位置，其大小不会超过m x n，因此空间复杂度为O(m x n)。
     * todo:超出时间限制，还有待优化
     */
    public static boolean possibleToStamp2(int[][] grid, int stampHeight, int stampWidth) {
        int m = grid.length;
        int n = grid[0].length;

        List<int[]> stamps = new ArrayList<>();

        // 遍历矩阵中的每个位置
        for (int i = 0; i <= m - stampHeight; i++) {
            for (int j = 0; j <= n - stampWidth; j++) {
                boolean canPlace = true;

                // 检查当前位置是否可以放置邮票
                for (int k = 0; k < stampHeight; k++) {
                    for (int l = 0; l < stampWidth; l++) {
                        if (grid[i + k][j + l] == 1 && grid[i + k][j + l] != -1) {
                            canPlace = false;
                            break;
                        }
                    }
                    if (!canPlace) {
                        break;
                    }
                }

                // 如果可以放置邮票，更新矩阵和邮票位置列表
                if (canPlace) {
                    for (int k = 0; k < stampHeight; k++) {
                        for (int l = 0; l < stampWidth; l++) {
                            if (grid[i + k][j + l] == 0) {
                                grid[i + k][j + l] = -1; // -1表示该位置已被邮票覆盖
                            }
                        }
                    }
                    stamps.add(new int[]{i, j});
                }
            }
        }

        // 检查是否所有的空格子都被覆盖
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    return false; // 存在未被覆盖的空格子
                }
            }
        }


        return true;
    }

    public static void main(String[] args) {
        // 测试用例
        int[][] grid1 = {{1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}};
        int stampHeight1 = 4;
        int stampWidth1 = 3;
        boolean result1 = possibleToStamp(grid1, stampHeight1, stampWidth1);
        System.out.println(result1); // 输出：true

        int[][] grid2 = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        int stampHeight2 = 2;
        int stampWidth2 = 2;
        boolean result2 = possibleToStamp(grid2, stampHeight2, stampWidth2);
        System.out.println(result2); // 输出：false
    }
}

