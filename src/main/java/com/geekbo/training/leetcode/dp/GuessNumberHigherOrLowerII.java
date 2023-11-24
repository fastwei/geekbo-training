package com.geekbo.training.leetcode.dp;

/**
 * 375. Guess Number Higher or Lower II
 * Medium
 * We are playing the Guessing Game. The game will work as follows:
 * <p>
 * I pick a number between 1 and n.
 * You guess a number.
 * If you guess the right number, you win the game.
 * If you guess the wrong number, then I will tell you whether the number I picked is higher or lower, and you will continue guessing.
 * Every time you guess a wrong number x, you will pay x dollars. If you run out of money, you lose the game.
 * Given a particular n, return the minimum amount of money you need to guarantee a win regardless of what number I pick.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 10
 * Output: 16
 * Explanation: The winning strategy is as follows:
 * - The range is [1,10]. Guess 7.
 * - If this is my number, your total is $0. Otherwise, you pay $7.
 * - If my number is higher, the range is [8,10]. Guess 9.
 * - If this is my number, your total is $7. Otherwise, you pay $9.
 * - If my number is higher, it must be 10. Guess 10. Your total is $7 + $9 = $16.
 * - If my number is lower, it must be 8. Guess 8. Your total is $7 + $9 = $16.
 * - If my number is lower, the range is [1,6]. Guess 3.
 * - If this is my number, your total is $7. Otherwise, you pay $3.
 * - If my number is higher, the range is [4,6]. Guess 5.
 * - If this is my number, your total is $7 + $3 = $10. Otherwise, you pay $5.
 * - If my number is higher, it must be 6. Guess 6. Your total is $7 + $3 + $5 = $15.
 * - If my number is lower, it must be 4. Guess 4. Your total is $7 + $3 + $5 = $15.
 * - If my number is lower, the range is [1,2]. Guess 1.
 * - If this is my number, your total is $7 + $3 = $10. Otherwise, you pay $1.
 * - If my number is higher, it must be 2. Guess 2. Your total is $7 + $3 + $1 = $11.
 * The worst case in all these scenarios is that you pay $16. Hence, you only need $16 to guarantee a win.
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: 0
 * Explanation: There is only one possible number, so you can guess 1 and not have to pay anything.
 * Example 3:
 * <p>
 * Input: n = 2
 * Output: 1
 * Explanation: There are two possible numbers, 1 and 2.
 * - Guess 1.
 * - If this is my number, your total is $0. Otherwise, you pay $1.
 * - If my number is higher, it must be 2. Guess 2. Your total is $1.
 * The worst case is that you pay $1.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 200
 */
class GuessNumberHigherOrLowerII {
    /**
     * 解题思路：
     * 这道题是一个经典的博弈问题，可以使用动态规划来解决。
     * 我们可以定义dp[start][end] 表示在范围[start, end]内猜数字所需要的最小金额。对于这个范围内的每一个数字i，
     * 我们可以选择它作为猜测的数字，然后根据猜测的结果，将问题分解为两个子问题：[start, i - 1]和[i + 1, end]。
     * 我们选择其中的较大值，然后再加上i作为猜测的数字所需要的金额。
     * 我们遍历所有的数字i，并选择其中的最小值作为dp[start][end] 的值。最后返回dp[1][n] 即可。
     * <p>
     * 算法复杂度分析：
     * -时间复杂度：动态规划的过程中，我们需要填充dp数组，每个位置需要O(n) 的时间复杂度，总共有O(n ^ 2) 个位置，
     * 所以总时间复杂度为O(n ^ 3)。
     * -空间复杂度：我们使用了一个二维数组dp来保存中间结果，空间复杂度为O(n ^ 2)。
     *
     * @param n
     * @return
     */
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        return calculate(dp, 1, n);
    }

    private int calculate(int[][] dp, int start, int end) {
        if (start >= end) {
            return 0;
        }
        if (dp[start][end] != 0) {
            return dp[start][end];
        }
        int minCost = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            int currentCost = i + Math.max(calculate(dp, start, i - 1), calculate(dp, i + 1, end));
            minCost = Math.min(minCost, currentCost);
        }
        dp[start][end] = minCost;
        return minCost;
    }

    public static void main(String[] args) {
        GuessNumberHigherOrLowerII solution = new GuessNumberHigherOrLowerII();
        // Test Case 1
        // Explanation: The winning strategy is as follows:
        // - The range is [1,10]. Guess 7.
        //   - If this is my number, your total is $0. Otherwise, you pay $7.
        //   - If my number is higher, the range is [8,10]. Guess 9.
        //       - If this is my number, your total is $7. Otherwise, you pay $9.
        //       - If my number is higher, it must be 10. Guess 10. Your total is $7 + $9 = $16.
        //       - If my number is lower, it must be 8. Guess 8. Your total is $7 + $9 = $16.
        //   - If my number is lower, the range is [1,6]. Guess 3.
        //       - If this is my number, your total is $7. Otherwise, you pay $3.
        //       - If my number is higher, the range is [4,6]. Guess 5.
        //           - If this is my number, your total is $7 + $3 = $10. Otherwise, you pay $5.
        //           - If my number is higher, it must be 6. Guess 6. Your total is $7 + $3 + $5 = $15.
        //           - If my number is lower, it must be 4. Guess 4. Your total is $7 + $3 + $5 = $15.
        //       - If my number is lower, the range is [1,2]. Guess 1.
        //           - If this is my number, your total is $7 + $3 = $10. Otherwise, you pay $1.
        //           - If my number is higher, it must be 2. Guess 2. Your total is $7 + $3 + $1 = $11.
        // The worst case in all these scenarios is that you pay $16. Hence, you only need $16 to guarantee a win.
        int n1 = 10;
        int expected1 = 16;
        int result1 = solution.getMoneyAmount(n1);
        System.out.println(result1 == expected1 ? "Test Case 1 Passed" : "Test Case 1 Failed");

        // Test Case 2
        // Explanation: There is only one possible number, so you can guess 1 and not have to pay anything.
        int n2 = 1;
        int expected2 = 0;
        int result2 = solution.getMoneyAmount(n2);
        System.out.println(result2 == expected2 ? "Test Case 2 Passed" : "Test Case 2 Failed");

        // Test Case 3
        // Explanation: There are two possible numbers, 1 和2。 // - Guess 1. // - If this is my number, your total is $0. Otherwise, you pay $1. // - If my number is higher, it must be 2. Guess 2. Your total is $1. // The worst case is that you pay $1. int n3 = 2; int expected3 = 1; int result3 = solution.getMoneyAmount(n3); System.out.println(result3 == expected3 ? "Test Case 3 Passed" : "Test Case 3 Failed"); } }
    }
}
