package com.geekbo.training.leetcode.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 1276. Number of Burgers with No Waste of Ingredients
 * Medium
 * Given two integers tomatoSlices and cheeseSlices. The ingredients of different burgers are as follows:
 * <p>
 * Jumbo Burger: 4 tomato slices and 1 cheese slice.
 * Small Burger: 2 Tomato slices and 1 cheese slice.
 * Return [total_jumbo, total_small] so that the number of remaining tomatoSlices equal to 0 and the number of remaining cheeseSlices equal to 0. If it is not possible to make the remaining tomatoSlices and cheeseSlices equal to 0 return [].
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: tomatoSlices = 16, cheeseSlices = 7
 * Output: [1,6]
 * Explantion: To make one jumbo burger and 6 small burgers we need 4*1 + 2*6 = 16 tomato and 1 + 6 = 7 cheese.
 * There will be no remaining ingredients.
 * Example 2:
 * <p>
 * Input: tomatoSlices = 17, cheeseSlices = 4
 * Output: []
 * Explantion: There will be no way to use all ingredients to make small and jumbo burgers.
 * Example 3:
 * <p>
 * Input: tomatoSlices = 4, cheeseSlices = 17
 * Output: []
 * Explantion: Making 1 jumbo burger there will be 16 cheese remaining and making 2 small burgers there will be 15 cheese remaining.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= tomatoSlices, cheeseSlices <= 107
 */
public class NumOfBurgers {
    /**
     * 解题思路：根据题目要求，我们需要计算能够制作的巨无霸汉堡和小汉堡的数量。
     * 巨无霸汉堡需要4个番茄片和1个奶酪片，小汉堡需要2个番茄片和1个奶酪片。
     * 我们可以根据番茄片和奶酪片的数量进行计算，得到制作的巨无霸汉堡和小汉堡的数量。
     * 具体算法如下：
     * <p>
     * 首先，我们需要判断番茄片和奶酪片的数量是否满足制作条件。
     * 如果番茄片数量是奇数，或者番茄片数量小于奶酪片数量的两倍，或者番茄片数量大于奶酪片数量的四倍，那么无法制作汉堡，直接返回空列表。
     * 计算巨无霸汉堡的数量。巨无霸汉堡需要的番茄片数量等于番茄片数量减去奶酪片数量的两倍，然后除以2。
     * 计算小汉堡的数量。小汉堡的数量等于奶酪片数量减去巨无霸汉堡的数量。
     * 将巨无霸汉堡的数量和小汉堡的数量添加到结果列表中，并返回结果列表。
     * 算法复杂度分析：该算法只需要进行简单的数学计算，时间复杂度为O(1)。空间复杂度为O(1)。
     *
     * @param tomatoSlices
     * @param cheeseSlices
     * @return
     */
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        List<Integer> result = new ArrayList<>();

        // Check if the number of tomato slices and cheese slices is valid
        if (tomatoSlices % 2 != 0 || tomatoSlices < cheeseSlices * 2 || tomatoSlices > cheeseSlices * 4) {
            return result;
        }

        int jumboBurgers = (tomatoSlices - 2 * cheeseSlices) / 2;
        int smallBurgers = cheeseSlices - jumboBurgers;

        result.add(jumboBurgers);
        result.add(smallBurgers);

        return result;
    }

    public static void main(String[] args) {
        NumOfBurgers solution = new NumOfBurgers();

        // Test case 1
        int tomatoSlices1 = 16;
        int cheeseSlices1 = 7;
        // Expected output: [1, 6]
        System.out.println(solution.numOfBurgers(tomatoSlices1, cheeseSlices1));

        // Test case 2
        int tomatoSlices2 = 17;
        int cheeseSlices2 = 4;
        // Expected output: []
        System.out.println(solution.numOfBurgers(tomatoSlices2, cheeseSlices2));

        // Test case 3
        int tomatoSlices3 = 4;
        int cheeseSlices3 = 17;
        // Expected output: []
        System.out.println(solution.numOfBurgers(tomatoSlices3, cheeseSlices3));
    }
}
