package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

/**
 * 1921. Eliminate Maximum Number of Monsters
 * Medium
 * You are playing a video game where you are defending your city from a group of n monsters.
 * You are given a 0-indexed integer array dist of size n,
 * where dist[i] is the initial distance in kilometers of the ith monster from the city.
 *
 * The monsters walk toward the city at a constant speed.
 * The speed of each monster is given to you in an integer array speed of size n,
 * where speed[i] is the speed of the ith monster in kilometers per minute.
 *
 * You have a weapon that, once fully charged, can eliminate a single monster.
 * However, the weapon takes one minute to charge. The weapon is fully charged at the very start.
 *
 * You lose when any monster reaches your city.
 * If a monster reaches the city at the exact moment the weapon is fully charged,
 * it counts as a loss, and the game ends before you can use your weapon.
 *
 * Return the maximum number of monsters that you can eliminate before you lose,
 * or n if you can eliminate all the monsters before they reach the city.
 *
 *
 *
 * Example 1:
 *
 * Input: dist = [1,3,4], speed = [1,1,1]
 * Output: 3
 * Explanation:
 * In the beginning, the distances of the monsters are [1,3,4]. You eliminate the first monster.
 * After a minute, the distances of the monsters are [X,2,3]. You eliminate the second monster.
 * After a minute, the distances of the monsters are [X,X,2]. You eliminate the thrid monster.
 * All 3 monsters can be eliminated.
 * Example 2:
 *
 * Input: dist = [1,1,2,3], speed = [1,1,1,1]
 * Output: 1
 * Explanation:
 * In the beginning, the distances of the monsters are [1,1,2,3]. You eliminate the first monster.
 * After a minute, the distances of the monsters are [X,0,1,2], so you lose.
 * You can only eliminate 1 monster.
 * Example 3:
 *
 * Input: dist = [3,2,4], speed = [5,3,2]
 * Output: 1
 * Explanation:
 * In the beginning, the distances of the monsters are [3,2,4]. You eliminate the first monster.
 * After a minute, the distances of the monsters are [X,0,2], so you lose.
 * You can only eliminate 1 monster.
 *
 */
class EliminateMaximumNumberOfMonsters {
    /**
     * 解题思路： 首先，计算每个怪物到达城市所需的时间（距离除以速度，向上取整）并存储在数组timeToReach中。
     * 然后，对timeToReach数组进行排序。
     * 接下来，遍历timeToReach数组，对于每个怪物，如果它到达时间小于等于已经消灭的怪物数量，则无法消灭更多怪物，直接返回已消灭的怪物数量。
     * 否则，消灭当前怪物，并增加已消灭的怪物数量。最后，返回已消灭的怪物数量。
     *
     * 算法复杂度分析： 假设有n个怪物。
     *
     * 计算每个怪物到达时间的时间复杂度为O(n)。
     * 对timeToReach数组进行排序的时间复杂度为O(nlogn)。
     * 遍历timeToReach数组的时间复杂度为O(n)。 综上所述，算法的总时间复杂度为O(nlogn)，总空间复杂度为O(n)。
     *
     * @param dist
     * @param speed
     * @return
     */
    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        int[] timeToReach = new int[n];

        // 计算每个怪物到达城市所需的时间
        for (int i = 0; i < n; i++) {
            timeToReach[i] = (int) Math.ceil((double) dist[i] / speed[i]);
        }

        Arrays.sort(timeToReach);

        int count = 0;
        for (int i = 0; i < n; i++) {
            // 如果当前怪物到达时间小于等于已经消灭的怪物数量，无法消灭更多怪物，返回已消灭的怪物数量
            if (timeToReach[i] <= count) {
                return count;
            }
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        EliminateMaximumNumberOfMonsters eliminateMaximumNumberOfMonsters = new EliminateMaximumNumberOfMonsters();

        // 测试用例1
        int[] dist1 = {1, 3, 4};
        int[] speed1 = {1, 1, 1};
        int expected1 = 3;
        int result1 = eliminateMaximumNumberOfMonsters.eliminateMaximum(dist1, speed1);
        System.out.println("测试用例1:");
        System.out.println("预期输出: " + expected1);
        System.out.println("实际输出: " + result1);

        // 测试用例2
        int[] dist2 = {1, 1, 2, 3};
        int[] speed2 = {1, 1, 1, 1};
        int expected2 = 1;
        int result2 = eliminateMaximumNumberOfMonsters.eliminateMaximum(dist2, speed2);
        System.out.println("测试用例2:");
        System.out.println("预期输出: " + expected2);
        System.out.println("实际输出: " + result2);

        // 测试用例3
        int[] dist3 = {3, 2, 4};
        int[] speed3 = {5, 3, 2};
        int expected3 = 1;
        int result3 = eliminateMaximumNumberOfMonsters.eliminateMaximum(dist3, speed3);
        System.out.println("测试用例3:");
        System.out.println("预期输出: " + expected3);
        System.out.println("实际输出: " + result3);
    }
}
