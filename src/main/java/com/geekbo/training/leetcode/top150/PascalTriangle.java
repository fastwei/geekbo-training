package com.geekbo.training.leetcode.top150;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 * <p>
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 * <p>
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: numRows = 5
 * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * Example 2:
 * <p>
 * Input: numRows = 1
 * Output: [[1]]
 */
public class PascalTriangle {
    /**
     * 解题思路： 本题可以使用动态规划的方法来解决。
     * 我们可以使用一个二维数组来表示Pascal三角形，其中triangle[i][j]表示第i行第j列的元素。
     * 对于任意的i和j，有以下关系：triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j]
     * 我们从第二行开始生成Pascal三角形。
     * 首先，我们添加第一行只包含一个元素1。
     * 然后，对于每一行i（i>=2），我们可以根据上一行的元素生成当前行的元素。
     * 当前行的第一个元素为1，中间元素的值为上一行中相邻两个元素的和，最后一个元素也为1。
     * 我们将每一行添加到Pascal三角形中，直到生成了numRows行。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(numRows^2)，其中numRows是要生成的Pascal三角形的行数。
     * 我们需要生成numRows行，每行的元素个数与行数相等，所以时间复杂度是O(numRows^2)。
     * 空间复杂度：O(numRows^2)，我们需要使用一个二维数组来存储Pascal三角形的元素。
     * 在最坏情况下，Pascal三角形的行数和列数都为numRows，所以空间复杂度是O(numRows^2)。
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        // 处理特殊情况
        if (numRows == 0) {
            return triangle;
        }

        // 添加第一行
        triangle.add(new ArrayList<>());
        triangle.get(0).add(1);

        // 从第二行开始生成Pascal三角形
        for (int i = 1; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> prevRow = triangle.get(i - 1);

            // 添加当前行的第一个元素
            row.add(1);

            // 生成当前行的中间元素
            for (int j = 1; j < i; j++) {
                row.add(prevRow.get(j - 1) + prevRow.get(j));
            }

            // 添加当前行的最后一个元素
            row.add(1);

            // 将当前行添加到Pascal三角形中
            triangle.add(row);
        }

        return triangle;
    }

    public static void main(String[] args) {
        PascalTriangle pascalTriangle = new PascalTriangle();

        // Test Case 1
        int numRows1 = 5;
        List<List<Integer>> result1 = pascalTriangle.generate(numRows1);
        System.out.println(result1);  // Expected output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]

        // Test Case 2
        int numRows2 = 1;
        List<List<Integer>> result2 = pascalTriangle.generate(numRows2);
        System.out.println(result2);  // Expected output: [[1]]
    }
}
