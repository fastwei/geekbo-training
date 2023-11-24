package com.geekbo.training.leetcode.daily;

import java.util.TreeSet;

/**
 * 363. Max Sum of Rectangle No Larger Than K
 * Hard
 * Given an m x n matrix matrix and an integer k, return the max sum of a rectangle in the matrix such that its sum is no larger than k.
 * <p>
 * It is guaranteed that there will be a rectangle with a sum no larger than k.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [[1,0,1],[0,-2,3]], k = 2
 * Output: 2
 * Explanation: Because the sum of the blue rectangle [[0, 1], [-2, 3]] is 2, and 2 is the max number no larger than k (k = 2).
 * Example 2:
 * <p>
 * Input: matrix = [[2,2,-1]], k = 3
 * Output: 3
 */
class MaxSumOfRectangleNoLargerThanK {
    /**
     * 解题思路：
     * <p>
     * 该问题可以通过动态规划和前缀和来解决。
     * 首先，固定矩形的左边界，然后遍历右边界。这样，我们将问题转化为求解每一对左右边界所形成的矩形的和。
     * 对于每一对左右边界，我们遍历每一行，计算当前行到上边界的前缀和。然后，我们使用TreeSet来存储已经计算过的前缀和，并使用TreeSet的ceiling方法找到最接近且小于等于k的前缀和。
     * 通过遍历每一行，我们可以计算出每一个矩形的和，并更新最大和的值。最后，返回最大和作为结果。
     * 算法复杂度分析：
     * <p>
     * 设矩阵的行数为m，列数为n。
     * 外层循环固定左边界，时间复杂度为O(n)。
     * 内层循环遍历右边界，时间复杂度为O(n)。
     * <p>
     * 在每个右边界处，我们需要遍历每一行来计算前缀和，时间复杂度为O(m)。
     * 在每一行中，我们需要使用TreeSet来查找最接近且小于等于k的前缀和，时间复杂度为O(logm)。
     * 因此，总的时间复杂度为O(n^2 * m * logm)。
     * 空间复杂度为O(m)，用于存储每一行的前缀和。
     * 请注意，上述复杂度分析假设矩阵的行数和列数都是大于0的常数。
     * 如果矩阵的行数和列数可以变化，则需要考虑这些因素对算法复杂度的影响。
     *
     * @param matrix
     * @param k
     * @return
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxSum = Integer.MIN_VALUE;

        // 固定左边界
        for (int left = 0; left < cols; left++) {
            int[] rowSum = new int[rows];

            // 遍历右边界
            for (int right = left; right < cols; right++) {
                // 计算每一行的前缀和
                for (int row = 0; row < rows; row++) {
                    rowSum[row] += matrix[row][right];
                }

                // 使用TreeSet来存储已经计算过的前缀和
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0); // 前缀和为0，用于处理从第一行开始的情况
                int curSum = 0;

                // 遍历每一行，计算当前矩形的和
                for (int row = 0; row < rows; row++) {
                    curSum += rowSum[row];

                    // 使用TreeSet的ceiling方法找到最接近且小于等于k的前缀和
                    Integer ceiling = set.ceiling(curSum - k);
                    if (ceiling != null) {
                        maxSum = Math.max(maxSum, curSum - ceiling);
                    }

                    set.add(curSum);
                }
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        MaxSumOfRectangleNoLargerThanK solution = new MaxSumOfRectangleNoLargerThanK();

        int[][] matrix1 = {{1, 0, 1}, {0, -2, 3}};
        int k1 = 2;
        // 预期输出: 2
        System.out.println(solution.maxSumSubmatrix(matrix1, k1));

        int[][] matrix2 = {{2, 2, -1}};
        int k2 = 3;
        // 预期输出: 3
        System.out.println(solution.maxSumSubmatrix(matrix2, k2));
    }
}


