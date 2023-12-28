package com.geekbo.training.leetcode.daily;

import java.util.*;

/**
 *
 * 539. Minimum Time Difference
 * Medium
 * Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes difference between any two time-points in the list.
 *
 *
 * Example 1:
 *
 * Input: timePoints = ["23:59","00:00"]
 * Output: 1
 * Example 2:
 *
 * Input: timePoints = ["00:00","23:59","00:00"]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 2 <= timePoints.length <= 2 * 104
 * timePoints[i] is in the format "HH:MM".
 *
 */
public class MinimumTimeDifference {
    /**
     * 计算24小时制时间列表中任意两个时间点之间的最小分钟差。
     *
     * 解题思路：
     * 1. 将时间点转换为分钟数表示，方便计算差值。
     * 2. 将时间点列表按照分钟数排序。
     * 3. 计算相邻两个时间点的差值，并记录最小差值。
     * 4. 如果最后一个时间点和第一个时间点的差值小于等于720（12小时），则需要考虑跨天的情况，计算最小差值。
     *
     * 算法复杂度分析：
     * - 时间复杂度：O(nlogn)，其中n为时间点列表的长度。排序的时间复杂度为O(nlogn)，计算差值的时间复杂度为O(n)。
     * - 空间复杂度：O(n)，需要额外的空间存储转换后的时间点。
     *
     * @param timePoints 时间点列表
     * @return 最小分钟差
     */
    public static int findMinDifference(List<String> timePoints) {
        // 将时间点转换为分钟数表示
        List<Integer> minutes = new ArrayList<>();
        for (String timePoint : timePoints) {
            int hour = Integer.parseInt(timePoint.split(":")[0]);
            int minute = Integer.parseInt(timePoint.split(":")[1]);
            minutes.add(hour * 60 + minute);
        }

        // 对时间点列表按照分钟数排序
        Collections.sort(minutes);

        // 计算相邻两个时间点的差值，并记录最小差值
        int minDifference = Integer.MAX_VALUE;
        for (int i = 1; i < minutes.size(); i++) {
            minDifference = Math.min(minDifference, minutes.get(i) - minutes.get(i - 1));
        }

        // 如果最后一个时间点和第一个时间点的差值小于等于720，则需要考虑跨天的情况
        if (minutes.get(0) + 1440 - minutes.get(minutes.size() - 1) <= 720) {
            minDifference = Math.min(minDifference, minutes.get(0) + 1440 - minutes.get(minutes.size() - 1));
        }

        return minDifference;
    }

    public static void main(String[] args) {
        // 测试用例1
        List<String> timePoints1 = Arrays.asList("23:59", "00:00");
        int result1 = findMinDifference(timePoints1);
        System.out.println(result1);  // 预期输出：1

        // 测试用例2
        List<String> timePoints2 = Arrays.asList("00:00", "23:59", "00:00");
        int result2 = findMinDifference(timePoints2);
        System.out.println(result2);  // 预期输出：0
    }
}