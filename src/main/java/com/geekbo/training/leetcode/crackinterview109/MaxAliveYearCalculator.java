package com.geekbo.training.leetcode.crackinterview109;

/**
 *
 * 面试题 16.10. 生存人数
 * 中等
 * 给定 N 个人的出生年份和死亡年份，第 i 个人的出生年份为 birth[i]，死亡年份为 death[i]，实现一个方法以计算生存人数最多的年份。
 *
 * 你可以假设所有人都出生于 1900 年至 2000 年（含 1900 和 2000 ）之间。如果一个人在某一年的任意时期处于生存状态，那么他应该被纳入那一年的统计中。例如，生于 1908 年、死于 1909 年的人应当被列入 1908 年和 1909 年的计数。
 *
 * 如果有多个年份生存人数相同且均为最大值，输出其中最小的年份。
 *
 *
 *
 * 示例：
 *
 * 输入：
 * birth = [1900, 1901, 1950]
 * death = [1948, 1951, 2000]
 * 输出： 1901
 *
 *
 * 提示：
 *
 * 0 < birth.length == death.length <= 10000
 * birth[i] <= death[i]
 *
 */
class MaxAliveYearCalculator {
    public int maxAliveYear(int[] birth, int[] death) {
        int startYear = 1900; // 开始年份
        int endYear = 2000; // 结束年份
        int[] population = new int[endYear - startYear + 1]; // 用于记录每年的人口数量

        // 遍历每个人的出生年份和死亡年份
        for (int i = 0; i < birth.length; i++) {
            int birthYear = birth[i];
            int deathYear = death[i];

            // 增加出生年份的人口数量
            population[birthYear - startYear]++;

            // 减少死亡年份的下一年人口数量，如果不超过结束年份的下一年
            if (deathYear + 1 <= endYear) {
                population[deathYear + 1 - startYear]--;
            }
        }

        // 计算每年的累积人口数量
        int maxAlive = 0; // 最大生存人数
        int maxAliveYear = startYear; // 生存人数最多的年份
        int alive = 0; // 当前年份的生存人数
        for (int i = 0; i < population.length; i++) {
            alive += population[i];
            if (alive > maxAlive) {
                maxAlive = alive;
                maxAliveYear = startYear + i;
            }
        }

        return maxAliveYear;
    }
    public static void main(String[] args) {
        MaxAliveYearCalculator calculator = new MaxAliveYearCalculator();

        // 测试用例1
        int[] birth1 = {1900, 1901, 1950};
        int[] death1 = {1948, 1951, 2000};
        int expected1 = 1901;
        int result1 = calculator.maxAliveYear(birth1, death1);
        System.out.println(result1 == expected1 ? "Test case 1 passed" : "Test case 1 failed");

        // 测试用例2
        int[] birth2 = {1910, 1920, 1930};
        int[] death2 = {1950, 1940, 1950};
        int expected2 = 1930;
        int result2 = calculator.maxAliveYear(birth2, death2);
        System.out.println(result2 == expected2 ? "Test case 2 passed" : "Test case 2 failed");

        // 测试用例3
        int[] birth3 = {1900};
        int[] death3 = {2000};
        int expected3 = 1900;
        int result3 = calculator.maxAliveYear(birth3, death3);
        System.out.println(result3 == expected3 ? "Test case 3 passed" : "Test case 3 failed");
    }
}