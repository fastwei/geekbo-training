package com.geekbo.training.leetcode.top75;


/**
 * 1137. N-th Tribonacci Number
 *
 * 解题思路和算法复杂度分析：
 *
 * 我们创建一个memo数组，用于存储计算结果。初始条件T0、T1、T2的值已知，所以将它们存储在memo中。
 * 然后，从第3项开始，通过迭代计算Tn = Tn-1 + Tn-2 + Tn-3的值，并将结果存储在memo数组中。
 * 最后，返回memo[n]即可得到第n项的Tribonacci数。
 * 算法复杂度分析：
 *
 * 时间复杂度：O(n)，因为我们计算了所有从3到n的Tribonacci数。
 * 空间复杂度：O(n)，我们使用了一个memo数组来存储计算结果。
 *
 *
 */
public class Tribonacci {
    public int tribonacci(int n) {
        // 创建一个数组用于存储计算结果
        int[] memo = new int[Math.max(n + 1, 3)];
        
        // 初始条件
        memo[0] = 0;
        memo[1] = 1;
        memo[2] = 1;
        
        // 从第3项开始计算
        for (int i = 3; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2] + memo[i - 3];
        }

        return memo[n];
    }
    
    public static void main(String[] args) {
        Tribonacci solution = new Tribonacci();
        
        // 测试用例1
        int result1 = solution.tribonacci(4);
        System.out.println("Test Case 1: " + result1); // 预期输出：4

        // 测试用例2
        int result2 = solution.tribonacci(25);
        System.out.println("Test Case 2: " + result2); // 预期输出：1389537

        for (int i = 0; i < 25; i++) {
            System.out.println("memo[" + i + "] = " + solution.tribonacci(i));
        }

    }
}
