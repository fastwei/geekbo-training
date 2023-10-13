package com.geekbo.training.leetcode.top75;

import java.util.Arrays;

/**
 * 2300. Successful Pairs of Spells and Potions
 * <p>
 * You are given two positive integer arrays spells and potions, of length n and m respectively,
 * where spells[i] represents the strength of the ith spell and potions[j] represents the strength of the jth potion.
 * <p>
 * You are also given an integer success.
 * A spell and potion pair is considered successful if the product of their strengths is at least success.
 * <p>
 * Return an integer array pairs of length n where pairs[i] is the number of potions that will form a successful pair with the ith spell.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: spells = [5,1,3], potions = [1,2,3,4,5], success = 7
 * Output: [4,0,3]
 * Explanation:
 * - 0th spell: 5 * [1,2,3,4,5] = [5,10,15,20,25]. 4 pairs are successful.
 * - 1st spell: 1 * [1,2,3,4,5] = [1,2,3,4,5]. 0 pairs are successful.
 * - 2nd spell: 3 * [1,2,3,4,5] = [3,6,9,12,15]. 3 pairs are successful.
 * Thus, [4,0,3] is returned.
 * Example 2:
 * <p>
 * Input: spells = [3,1,2], potions = [8,5,8], success = 16
 * Output: [2,0,2]
 * Explanation:
 * - 0th spell: 3 * [8,5,8] = [24,15,24]. 2 pairs are successful.
 * - 1st spell: 1 * [8,5,8] = [8,5,8]. 0 pairs are successful.
 * - 2nd spell: 2 * [8,5,8] = [16,10,16]. 2 pairs are successful.
 * Thus, [2,0,2] is returned.
 */
public class SuccessfulPairsOfSpellsAndPotions {

    /**
     * 解题思路：
     * <p>
     * 首先，对药水的强度数组进行排序，以便能够使用二分查找。
     * 遍历每个法术的强度，对每个法术使用双指针和二分查找来寻找配对的药水。
     * 使用左指针指向药水数组的起始位置，使用右指针指向药水数组的末尾。
     * 在二分查找中，计算法术强度与中间药水的乘积，然后与success进行比较。
     * 如果乘积大于或等于success，则说明所有右侧的药水也符合条件，因此增加count并将右指针左移。
     * 如果乘积小于success，则将左指针右移以寻找更大的药水。
     * 返回count作为每个法术的成功配对数量。
     * 算法复杂度：
     * <p>
     * 时间复杂度：O(n * log(m))，其中n是法术的数量，m是药水的数量。在最坏情况下，对于每个法术，需要进行log(m)次二分查找。
     * 空间复杂度：O(1)。没有使用额外的数据结构。
     *
     * @param spells
     * @param potions
     * @param success
     * @return
     */
    public static int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions); // 首先对药水的强度进行排序
        int n = spells.length;
        int m = potions.length;
        int[] pairs = new int[n];

        for (int i = 0; i < n; i++) {
            int spell = spells[i];
            int left = 0;
            int right = m - 1;
            int count = 0;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                long product = (long) spell * potions[mid];

                if (product >= success) {
                    count += (right - mid + 1); // 找到一个成功的配对后，累加符合条件的药水个数
                    right = mid - 1; // 继续在左侧查找更小的药水
                } else {
                    left = mid + 1; // 继续在右侧查找更大的药水
                }
            }

            pairs[i] = count;
        }

        return pairs;
    }

    public static void main(String[] args) {
        int[] spells1 = {5, 1, 3};
        int[] potions1 = {1, 2, 3, 4, 5};
        long success1 = 7;
        int[] result1 = successfulPairs(spells1, potions1, success1);
        System.out.println(Arrays.toString(result1)); // 预期输出：[4, 0, 3]

        int[] spells2 = {3, 1, 2};
        int[] potions2 = {8, 5, 8};
        long success2 = 16;
        int[] result2 = successfulPairs(spells2, potions2, success2);
        System.out.println(Arrays.toString(result2)); // 预期输出：[2, 0, 2]
    }
}