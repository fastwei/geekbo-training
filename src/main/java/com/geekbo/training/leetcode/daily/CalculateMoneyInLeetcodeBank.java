package com.geekbo.training.leetcode.daily;

public class CalculateMoneyInLeetcodeBank {

    /**
     * 解题思路：
     * <p>
     * 首先，我们计算整数周数 weeks，即 n 除以 7 的商。
     * 每周的金额是固定的，为 28 元，因此我们将整数周数乘以 28，并加到总金额 money 中。
     * 接下来，我们计算整数周的金额。我们可以使用等差数列求和公式 (7 * weeks * (weeks - 1)) / 2 来计算整数周的金额，并将其加到 money 中。
     * 如果 n 除以 7 的余数不为 0，说明有余数部分。我们需要计算余数部分的金额。
     * 余数部分的天数为 n 除以 7 的余数 extraDays。
     * 余数部分的金额每天递增，初始值为整数周数加 1，即 weeks + 1。
     * 使用循环遍历余数部分的天数，将每天的金额加到 money 中，并将每天的金额递增。
     * 最后，返回计算得到的总金额 money。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param n
     * @return
     */
    public int totalMoney(int n) {
        int weeks = n / 7; // 计算整数周数
        int money = weeks * 28; // 每周的金额

        // 计算整数周的金额
        money += (7 * weeks * (weeks - 1)) / 2;

        if (n % 7 != 0) {
            int extraDays = n % 7; // 计算余数部分的天数
            int moneyToAdd = weeks + 1; // 每天递增的金额

            // 计算余数部分的金额
            for (int i = 0; i < extraDays; ++i) {
                money += moneyToAdd;
                moneyToAdd += 1;
            }
        }

        return money;
    }

    public static void main(String[] args) {
        CalculateMoneyInLeetcodeBank solution = new CalculateMoneyInLeetcodeBank();

        // Test case 1
        int n1 = 4;
        int expected1 = 10;
        int result1 = solution.totalMoney(n1);
        System.out.println(result1 == expected1); // Output: true

        // Test case 2
        int n2 = 10;
        int expected2 = 37;
        int result2 = solution.totalMoney(n2);
        System.out.println(result2 == expected2); // Output: true

        // Test case 3
        int n3 = 20;
        int expected3 = 96;
        int result3 = solution.totalMoney(n3);
        System.out.println(result3 == expected3); // Output: true
    }
}
