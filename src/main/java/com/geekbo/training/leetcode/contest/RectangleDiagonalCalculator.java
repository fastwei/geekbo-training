package com.geekbo.training.leetcode.contest;
//379.1
public class RectangleDiagonalCalculator {

    /**
     * 计算具有最大对角线长度的矩形的面积。
     * @param dimensions 二维数组，表示矩形的尺寸
     * @return 最大对角线矩形的面积
     */
    public static int areaOfMaxDiagonal(int[][] dimensions) {
        double maxDiagonal = 0;
        int maxArea = 0;

        for (int[] dimension : dimensions) {
            int length = dimension[0];
            int width = dimension[1];

            // 计算对角线长度
            double diagonal = Math.sqrt(length * length + width * width);

            // 计算面积
            int area = length * width;

            // 检查是否为最大对角线长度或相同对角线长度下的最大面积
            if (diagonal > maxDiagonal || (diagonal == maxDiagonal && area > maxArea)) {
                maxDiagonal = diagonal;
                maxArea = area;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        // 测试用例
        int[][] dimensions1 = {{9, 3}, {8, 6}};
        System.out.println("Expected: 48, Actual: " + areaOfMaxDiagonal(dimensions1));

        int[][] dimensions2 = {{3, 4}, {4, 3}};
        System.out.println("Expected: 12, Actual: " + areaOfMaxDiagonal(dimensions2));
    }
}
