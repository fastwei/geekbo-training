package com.geekbo.training.leetcode.skill;

/**
 * You are given an m x n integer grid accounts where accounts[i][j] is the amount of money the i​​​​​​​​​​​th​​​​ customer has in the j​​​​​​​​​​​th​​​​ bank. Return the wealth that the richest customer has.
 * <p>
 * A customer's wealth is the amount of money they have in all their bank accounts. The richest customer is the customer that has the maximum wealth.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: accounts = [[1,2,3],[3,2,1]]
 * Output: 6
 * Explanation:
 * 1st customer has wealth = 1 + 2 + 3 = 6
 * 2nd customer has wealth = 3 + 2 + 1 = 6
 * Both customers are considered the richest with a wealth of 6 each, so return 6.
 * Example 2:
 * <p>
 * Input: accounts = [[1,5],[7,3],[3,5]]
 * Output: 10
 * Explanation:
 * 1st customer has wealth = 6
 * 2nd customer has wealth = 10
 * 3rd customer has wealth = 8
 * The 2nd customer is the richest with a wealth of 10.
 * Example 3:
 * <p>
 * Input: accounts = [[2,8,7],[7,1,3],[1,9,5]]
 * Output: 17
 */
public class RichestCustomerWealth {
    /**
     * 计算最富有客户的财富值。
     *
     * @param accounts 银行账户二维数组
     * @return 最富有客户的财富值
     */
    public static int maximumWealth(int[][] accounts) {
        int maxWealth = 0; // 最富有客户的财富值

        for (int[] customerAccounts : accounts) {
            int wealth = 0; // 每个客户的财富值

            for (int account : customerAccounts) {
                wealth += account; // 计算每个客户的财富值
            }

            maxWealth = Math.max(maxWealth, wealth); // 更新最富有客户的财富值
        }

        return maxWealth;
    }

    public static void main(String[] args) {
        int[][] accounts1 = {{1, 2, 3}, {3, 2, 1}};
        // 预期输出: 6
        System.out.println(maximumWealth(accounts1));

        int[][] accounts2 = {{1, 5}, {7, 3}, {3, 5}};
        // 预期输出: 10
        System.out.println(maximumWealth(accounts2));

        int[][] accounts3 = {{2, 8, 7}, {7, 1, 3}, {1, 9, 5}};
        // 预期输出: 17
        System.out.println(maximumWealth(accounts3));
    }
}