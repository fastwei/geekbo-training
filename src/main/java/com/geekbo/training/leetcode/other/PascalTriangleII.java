package com.geekbo.training.leetcode.other;

import java.util.ArrayList;
import java.util.List;


/**
 * Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
 * <p>
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 * <p>
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: rowIndex = 3
 * Output: [1,3,3,1]
 * Example 2:
 * <p>
 * Input: rowIndex = 0
 * Output: [1]
 * Example 3:
 * <p>
 * Input: rowIndex = 1
 * Output: [1,1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= rowIndex <= 33
 * <p>
 * <p>
 * Follow up: Could you optimize your algorithm to use only O(rowIndex) extra space?
 */
public class PascalTriangleII {
    /**
     * 返回帕斯卡三角形中第 rowIndex 行的数组表示。
     * <p>
     * 解题思路：
     * <p>
     * 我们使用动态规划的方法来生成帕斯卡三角形的第 rowIndex 行。
     * 从第二行开始，每一行的元素都由上一行的元素计算得出。
     * 对于每一行，我们从第二个元素开始，通过将上一行的当前位置和前一个位置的元素相加，得到当前位置的值。
     * 同时，每行的最后一个元素为1，我们需要在循环结束后将1添加到当前行的末尾。
     * 通过这种方式，我们可以生成帕斯卡三角形的指定行。
     * <p>
     * 在优化方面，我们只需要使用一个列表来表示当前行，通过不断更新列表中的元素来生成当前行的值。
     * 这样，我们只需要 O(rowIndex)的额外空间。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(rowIndex^2)，生成第 rowIndex 行需要遍历 rowIndex 次，并且每次遍历都需要更新当前行的元素。
     * 因此，总的时间复杂度为 O(rowIndex^2)。
     * 空间复杂度：O(rowIndex)，我们只需要使用一个列表来表示当前行的元素。
     *
     * @param rowIndex 行索引
     * @return 第 rowIndex 行的数组表示
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        row.add(1); // 第一个元素为1

        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i - 1; j >= 1; j--) {
                // 计算当前位置的值，等于上一行的 j 和 j-1 位置的值之和
                int sum = row.get(j) + row.get(j - 1);
                row.set(j, sum);
            }
            row.add(1); // 每行最后一个元素为1
        }

        return row;
    }

    public static void main(String[] args) {
        PascalTriangleII pascalTriangleII = new PascalTriangleII();

        // Test Case 1
        int rowIndex1 = 3;
        List<Integer> result1 = pascalTriangleII.getRow(rowIndex1);
        System.out.println("Test Case 1:");
        System.out.println("Input: rowIndex = " + rowIndex1);
        System.out.println("Output: " + result1);

        // Test Case 2
        int rowIndex2 = 0;
        List<Integer> result2 = pascalTriangleII.getRow(rowIndex2);
        System.out.println("\nTest Case 2:");
        System.out.println("Input: rowIndex = " + rowIndex2);
        System.out.println("Output: " + result2);

        // Test Case 3
        int rowIndex3 = 1;
        List<Integer> result3 = pascalTriangleII.getRow(rowIndex3);
        System.out.println("\nTest Case 3:");
        System.out.println("Input: rowIndex = " + rowIndex3);
        System.out.println("Output: " + result3);
    }
}
