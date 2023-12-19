package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

/**
 * 661. Image Smoother
 * Solved
 * Easy
 * An image smoother is a filter of the size 3 x 3 that can be applied to each cell of an image by rounding down the average of the cell and the eight surrounding cells (i.e., the average of the nine cells in the blue smoother). If one or more of the surrounding cells of a cell is not present, we do not consider it in the average (i.e., the average of the four cells in the red smoother).
 * <p>
 * <p>
 * Given an m x n integer matrix img representing the grayscale of an image, return the image after applying the smoother on each cell of it.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: img = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[0,0,0],[0,0,0],[0,0,0]]
 * Explanation:
 * For the points (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
 * For the points (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
 * For the point (1,1): floor(8/9) = floor(0.88888889) = 0
 * Example 2:
 * <p>
 * <p>
 * Input: img = [[100,200,100],[200,50,200],[100,200,100]]
 * Output: [[137,141,137],[141,138,141],[137,141,137]]
 * Explanation:
 * For the points (0,0), (0,2), (2,0), (2,2): floor((100+200+200+50)/4) = floor(137.5) = 137
 * For the points (0,1), (1,0), (1,2), (2,1): floor((200+200+50+200+100+100)/6) = floor(141.666667) = 141
 * For the point (1,1): floor((50+200+200+200+200+100+100+100+100)/9) = floor(138.888889) = 138
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == img.length
 * n == img[i].length
 * 1 <= m, n <= 200
 * 0 <= img[i][j] <= 255
 */
class ImageSmoother {

    /**
     * 对图像进行平滑处理
     * 解题思路：
     * <p>
     * 遍历图像的每个单元格，对于每个单元格，计算其和其周围八个单元格的总和。
     * 计算总和的时候，需要注意边界情况，不在图像范围内的单元格不计入总和。
     * 将总和除以计数的单元格数，得到平均值，并将其赋值给结果矩阵。
     * 返回平滑处理后的结果矩阵。
     * <p>
     * 算法复杂度分析
     * <p>
     * 时间复杂度：遍历每个单元格的操作需要 O(m * n) 的时间。
     * 空间复杂度：除了输入和输出的二维数组，我们还需要额外的空间来存储结果矩阵，因此空间复杂度也为 O(m * n)。
     * 由于每个单元格的处理是独立的，不存在重叠子问题，因此该解法的时间复杂度和空间复杂度是最优的。
     *
     * @param img 输入的二维整数矩阵，表示图像的灰度
     * @return 平滑处理后的图像
     */
    public int[][] imageSmoother(int[][] img) {
        int m = img.length;
        int n = img[0].length;
        int[][] result = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int sum = 0;
                int count = 0;

                // 计算当前单元格及其周围单元格的总和
                for (int r = i - 1; r <= i + 1; r++) {
                    for (int c = j - 1; c <= j + 1; c++) {
                        if (r >= 0 && r < m && c >= 0 && c < n) {
                            sum += img[r][c];
                            count++;
                        }
                    }
                }

                // 计算平均值并将其赋值给结果矩阵
                result[i][j] = sum / count;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ImageSmoother solution = new ImageSmoother();

        int[][] img = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[][] result = solution.imageSmoother(img);
        System.out.println("输入: " + Arrays.deepToString(img));
        System.out.println("预期输出: [[0, 0, 0], [0, 0, 0], [0, 0, 0]]");
        System.out.println("实际输出: " + Arrays.deepToString(result));
        System.out.println();

        img = new int[][]{{100, 200, 100}, {200, 50, 200}, {100, 200, 100}};
        result = solution.imageSmoother(img);
        System.out.println("输入: " + Arrays.deepToString(img));
        System.out.println("预期输出: [[137, 141, 137], [141, 138, 141], [137, 141, 137]]");
        System.out.println("实际输出: " + Arrays.deepToString(result));
        System.out.println();
    }
}
