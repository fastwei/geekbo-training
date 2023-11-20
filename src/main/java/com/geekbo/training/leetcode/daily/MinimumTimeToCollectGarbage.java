package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

/**
 * 2391. Minimum Amount of Time to Collect Garbage
 * Medium
 * You are given a 0-indexed array of strings garbage where garbage[i] represents the assortment of garbage at the ith house.
 * garbage[i] consists only of the characters 'M', 'P' and 'G' representing one unit of metal, paper and glass garbage respectively.
 * Picking up one unit of any type of garbage takes 1 minute.
 * <p>
 * You are also given a 0-indexed integer array travel where travel[i] is the number of minutes needed to go from house i to house i + 1.
 * <p>
 * There are three garbage trucks in the city,
 * each responsible for picking up one type of garbage.
 * Each garbage truck starts at house 0 and must visit each house in order;
 * however, they do not need to visit every house.
 * <p>
 * Only one garbage truck may be used at any given moment.
 * While one truck is driving or picking up garbage, the other two trucks cannot do anything.
 * <p>
 * Return the minimum number of minutes needed to pick up all the garbage.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: garbage = ["G","P","GP","GG"], travel = [2,4,3]
 * Output: 21
 * Explanation:
 * The paper garbage truck:
 * 1. Travels from house 0 to house 1
 * 2. Collects the paper garbage at house 1
 * 3. Travels from house 1 to house 2
 * 4. Collects the paper garbage at house 2
 * Altogether, it takes 8 minutes to pick up all the paper garbage.
 * The glass garbage truck:
 * 1. Collects the glass garbage at house 0
 * 2. Travels from house 0 to house 1
 * 3. Travels from house 1 to house 2
 * 4. Collects the glass garbage at house 2
 * 5. Travels from house 2 to house 3
 * 6. Collects the glass garbage at house 3
 * Altogether, it takes 13 minutes to pick up all the glass garbage.
 * Since there is no metal garbage, we do not need to consider the metal garbage truck.
 * Therefore, it takes a total of 8 + 13 = 21 minutes to collect all the garbage.
 * Example 2:
 * <p>
 * Input: garbage = ["MMM","PGM","GP"], travel = [3,10]
 * Output: 37
 * Explanation:
 * The metal garbage truck takes 7 minutes to pick up all the metal garbage.
 * The paper garbage truck takes 15 minutes to pick up all the paper garbage.
 * The glass garbage truck takes 15 minutes to pick up all the glass garbage.
 * It takes a total of 7 + 15 + 15 = 37 minutes to collect all the garbage.
 */
public class MinimumTimeToCollectGarbage {
    /**
     * 解题思路：
     * <p>
     * 首先，我们遍历每个房子的垃圾类型，并计算每种垃圾的收集时间。
     * 对于每个房子，我们将垃圾类型分为金属、纸张和玻璃三种，并根据垃圾类型增加相应的收集时间。
     * 如果还有下一个房子，我们增加到下一个房子的时间，即遍历时间数组 travel。
     * 最后，返回金属、纸张和玻璃三种垃圾收集时间中的最大值作为结果。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历每个房子和垃圾类型的时间复杂度为 O(n)，其中 n 是垃圾数组的长度。
     * 在遍历过程中，我们计算了每种垃圾的收集时间，并增加到下一个房子的时间，因此总体时间复杂度为 O(n)
     *
     * @param garbage
     * @param travel
     * @return
     */
    public static int minTimeToCollectGarbage(String[] garbage, int[] travel) {
        int metalTime = 0; // 金属垃圾收集时间
        int paperTime = 0; // 纸张垃圾收集时间
        int glassTime = 0; // 玻璃垃圾收集时间

        for (int i = 0; i < garbage.length; i++) {
            for (char c : garbage[i].toCharArray()) {
                if (c == 'M') {
                    metalTime += 1; // 金属垃圾收集时间增加1分钟
                } else if (c == 'P') {
                    paperTime += 1; // 纸张垃圾收集时间增加1分钟
                } else if (c == 'G') {
                    glassTime += 1; // 玻璃垃圾收集时间增加1分钟
                }
            }
        }

        int[] times = {metalTime, paperTime, glassTime};
        Arrays.sort(times); // 对三种垃圾的收集时间进行排序

        int totalTravelTime = 0; // 总的旅行时间
        for (int i = 0; i < travel.length; i++) {
            totalTravelTime += travel[i]; // 累加旅行时间
        }

        return totalTravelTime + times[2]; // 返回总的旅行时间加上最长的垃圾收集时间
    }

    public static void main(String[] args) {
        String[] garbage1 = {"G", "P", "GP", "GG"};
        int[] travel1 = {2, 4, 3};
        // 预期输出: 21
        System.out.println("Input: garbage = [\"G\",\"P\",\"GP\",\"GG\"], travel = [2,4,3]");
        System.out.println("Output: " + minTimeToCollectGarbage(garbage1, travel1));

        String[] garbage2 = {"MMM", "PGM", "GP"};
        int[] travel2 = {3, 10};
        // 预期输出: 37
        System.out.println("\nInput: garbage = [\"MMM\",\"PGM\",\"GP\"], travel = [3,10]");
        System.out.println("Output: " + minTimeToCollectGarbage(garbage2, travel2));
    }
}
