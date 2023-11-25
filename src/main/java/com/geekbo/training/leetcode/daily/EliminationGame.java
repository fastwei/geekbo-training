package com.geekbo.training.leetcode.daily;

/**
 * 390. Elimination Game
 * Medium
 * You have a list arr of all integers in the range [1, n] sorted in a strictly increasing order. Apply the following algorithm on arr:
 * <p>
 * Starting from left to right, remove the first number and every other number afterward until you reach the end of the list.
 * Repeat the previous step again, but this time from right to left, remove the rightmost number and every other number from the remaining numbers.
 * Keep repeating the steps again, alternating left to right and right to left, until a single number remains.
 * Given the integer n, return the last number that remains in arr.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 9
 * Output: 6
 * Explanation:
 * arr = [1, 2, 3, 4, 5, 6, 7, 8, 9]
 * arr = [2, 4, 6, 8]
 * arr = [2, 6]
 * arr = [6]
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 109
 */
public class EliminationGame {
    /**
     * 解题思路：
     * <p>
     * 根据题目描述，每次从左到右和从右到左交替进行删除操作，直到最后只剩下一个数字。
     * 从左到右的过程中，每次删除第一个数字和每隔一个数字删除一个，剩余数字的个数减半，步长翻倍。
     * 从右到左的过程中，同样每次删除最后一个数字和每隔一个数字删除一个，剩余数字的个数减半，步长翻倍。
     * 重复上述操作，直到剩余数字个数为1，返回最后剩下的数字即可。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：O(log n)，每次遍历过程中剩余数字个数减半，所以最多需要 log n 次遍历。
     * 空间复杂度：O(1)，只使用了常数级别的额外空间。
     *
     * @param n
     * @return
     */
    public int lastRemaining(int n) {
        // 特殊情况处理
        if (n == 1) {
            return 1;
        }
        // 从左到右的标志位
        boolean leftToRight = true;
        // 步长，每次遍历过程中的间隔数
        int step = 1;
        // 剩余数字的个数
        int remaining = n;
        // 第一个数字
        int first = 1;

        while (remaining > 1) {
            if (leftToRight || remaining % 2 == 1) {
                // 从左到右或者剩余数字个数为奇数时，第一个数字需要更新
                first += step;
            }
            // 步长翻倍
            step *= 2;
            // 剩余数字个数减半
            remaining /= 2;
            // 切换方向
            leftToRight = !leftToRight;
        }

        return first;
    }

    public static void main(String[] args) {
        EliminationGame solution = new EliminationGame();

        // 测试用例1
        int n1 = 9;
        int result1 = solution.lastRemaining(n1);
        System.out.println("Test Case 1:");
        System.out.println("Expected: 6");
        System.out.println("Actual: " + result1);

        // 测试用例2
        int n2 = 1;
        int result2 = solution.lastRemaining(n2);
        System.out.println("Test Case 2:");
        System.out.println("Expected: 1");
        System.out.println("Actual: " + result2);
    }
}
