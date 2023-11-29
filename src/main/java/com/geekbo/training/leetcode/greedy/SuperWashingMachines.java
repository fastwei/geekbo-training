package com.geekbo.training.leetcode.greedy;

import java.util.Arrays;

/**
 * 517. Super Washing Machines
 * Hard
 * You have n super washing machines on a line.
 * Initially, each washing machine has some dresses or is empty.
 * <p>
 * For each move, you could choose any m (1 <= m <= n) washing machines,
 * and pass one dress of each washing machine to one of its adjacent washing machines at the same time.
 * <p>
 * Given an integer array machines representing the number of dresses in each washing machine from left to right on the line,
 * return the minimum number of moves to make all the washing machines have the same number of dresses. If it is not possible to do it, return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: machines = [1,0,5]
 * Output: 3
 * Explanation:
 * 1st move:    1     0 <-- 5    =>    1     1     4
 * 2nd move:    1 <-- 1 <-- 4    =>    2     1     3
 * 3rd move:    2     1 <-- 3    =>    2     2     2
 * Example 2:
 * <p>
 * Input: machines = [0,3,0]
 * Output: 2
 * Explanation:
 * 1st move:    0 <-- 3     0    =>    1     2     0
 * 2nd move:    1     2 --> 0    =>    1     1     1
 * Example 3:
 * <p>
 * Input: machines = [0,2,0]
 * Output: -1
 * Explanation:
 * It's impossible to make all three washing machines have the same number of dresses.
 */
public class SuperWashingMachines {
    /**
     * 解题思路：
     * <p>
     * 首先计算所有洗衣机中衣服的总数sum，如果sum不能被洗衣机的数量n整除，那么无法使所有洗衣机中的衣服数量相等，返回-1。
     * 计算平均每台洗衣机应该有的衣服数量avg，即avg = sum / n。
     * 遍历每台洗衣机，计算洗衣机中衣服数量与平均值之间的差值balance，并更新最大移动次数moves。
     * 最后返回moves。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n)，其中n为洗衣机的数量。
     * 空间复杂度：O(1)。
     *
     * @param machines
     * @return
     */
    public static int findMinMoves(int[] machines) {
        int sum = 0;
        for (int machine : machines) {
            sum += machine;
        }

        int n = machines.length;
        if (sum % n != 0) {
            return -1;
        }

        int avg = sum / n;
        int moves = 0;
        int balance = 0;

        for (int machine : machines) {
            balance += machine - avg;
            moves = Math.max(moves, Math.max(Math.abs(balance), machine - avg));
        }

        return moves;
    }

    public static void main(String[] args) {
        // Test Case 1
        int[] machines1 = {1, 0, 5};
        int result1 = findMinMoves(machines1);
        System.out.println("Test Case 1:");
        System.out.println("Input: " + Arrays.toString(machines1));
        System.out.println("Output: " + result1);

        // Test Case 2
        int[] machines2 = {0, 3, 0};
        int result2 = findMinMoves(machines2);
        System.out.println("\nTest Case 2:");
        System.out.println("Input: " + Arrays.toString(machines2));
        System.out.println("Output: " + result2);

        // Test Case 3
        int[] machines3 = {0, 2, 0};
        int result3 = findMinMoves(machines3);
        System.out.println("\nTest Case 3:");
        System.out.println("Input: " + Arrays.toString(machines3));
        System.out.println("Output: " + result3);
    }
}
