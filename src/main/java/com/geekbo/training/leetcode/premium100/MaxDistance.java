package com.geekbo.training.leetcode.premium100;

import java.util.Arrays;
import java.util.List;

/**
 *
 * 624. 数组列表中的最大距离
 * 中等
 * 给定 m 个数组，每个数组都已经按照升序排好序了。
 * 现在你需要从两个不同的数组中选择两个整数（每个数组选一个）并且计算它们的距离。
 * 两个整数 a 和 b 之间的距离定义为它们差的绝对值 |a-b| 。你的任务就是去找到最大距离
 *
 * 示例 1：
 *
 * 输入：
 * [[1,2,3],
 *  [4,5],
 *  [1,2,3]]
 * 输出： 4
 * 解释：
 * 一种得到答案 4 的方法是从第一个数组或者第三个数组中选择 1，同时从第二个数组中选择 5 。
 *
 *
 * 注意：
 *
 * 每个给定数组至少会有 1 个数字。列表中至少有两个非空数组。
 * 所有 m 个数组中的数字总数目在范围 [2, 10000] 内。
 * m 个数组中所有整数的范围在 [-10000, 10000] 内。
 *
 */
public class MaxDistance {

    /**
     * 解题思路：
     * 由于每个数组都已经按照升序排好序了，我们可以观察到最大距离一定是来自于最大的数和最小的数之间的差值。
     * 因此，我们只需要找到每个数组中的最大数和最小数，然后计算它们之间的差值，取最大值即可。
     *
     * 算法复杂度分析：
     * 时间复杂度：O(m * n)，其中 m 是数组的个数，n 是每个数组的长度。
     * 空间复杂度：O(1)。
     *
     * @param arrays 给定的 m 个数组
     * @return 最大距离
     */
    public int maxDistance(List<List<Integer>> arrays) {
        int maxDistance = 0;
        int min = arrays.get(0).get(0);
        int max = arrays.get(0).get(arrays.get(0).size() - 1);

        for (int i = 1; i < arrays.size(); i++) {
            int currMin = arrays.get(i).get(0);
            int currMax = arrays.get(i).get(arrays.get(i).size() - 1);

            maxDistance = Math.max(maxDistance, Math.abs(max - currMin));
            maxDistance = Math.max(maxDistance, Math.abs(currMax - min));

            min = Math.min(min, currMin);
            max = Math.max(max, currMax);
        }

        return maxDistance;
    }

    public static void main(String[] args) {
        MaxDistance maxDistance = new MaxDistance();

        List<List<Integer>> arrays = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(1, 2, 3)
        );
        // 测试用例
        int expected = 4;
        int result = maxDistance.maxDistance(arrays);
        System.out.println("输入: " + arrays);
        System.out.println("预期输出: " + expected);
        System.out.println("实际输出: " + result);
        System.out.println();
    }
}