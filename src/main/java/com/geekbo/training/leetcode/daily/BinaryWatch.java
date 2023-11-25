package com.geekbo.training.leetcode.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 401. Binary Watch
 * Easy
 * A binary watch has 4 LEDs on the top to represent the hours (0-11), and 6 LEDs on the bottom to represent the minutes (0-59). Each LED represents a zero or one, with the least significant bit on the right.
 * <p>
 * For example, the below binary watch reads "4:51".
 * <p>
 * <p>
 * Given an integer turnedOn which represents the number of LEDs that are currently on (ignoring the PM), return all possible times the watch could represent. You may return the answer in any order.
 * <p>
 * The hour must not contain a leading zero.
 * <p>
 * For example, "01:00" is not valid. It should be "1:00".
 * The minute must consist of two digits and may contain a leading zero.
 * <p>
 * For example, "10:2" is not valid. It should be "10:02".
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: turnedOn = 1
 * Output: ["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
 * Example 2:
 * <p>
 * Input: turnedOn = 9
 * Output: []
 */
public class BinaryWatch {
    /**
     * 解题思路：
     * <p>
     * 遍历小时和分钟的所有可能组合，范围分别是0到11和0到59。
     * 对于每个组合，统计小时和分钟中1的个数，通过countBits方法实现。
     * 如果1的个数等于给定的turnedOn，则将时间添加到结果列表中。
     * 最后，返回结果列表。
     * <p>
     * 复杂度分析：
     * <p>
     * 时间复杂度：由于需要遍历小时和分钟的所有可能组合，所以时间复杂度为O(12*60)=O(720)。
     * 空间复杂度：结果列表的大小取决于满足条件的时间数量，最多为720个，所以空间复杂度为O(720)。
     *
     * @param turnedOn
     * @return
     */
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> result = new ArrayList<>();
        // 遍历小时和分钟的所有可能组合
        for (int hour = 0; hour < 12; hour++) {
            for (int minute = 0; minute < 60; minute++) {
                // 统计小时和分钟中1的个数
                int count = countBits(hour) + countBits(minute);
                // 如果1的个数等于给定的turnedOn，则将时间添加到结果中
                if (count == turnedOn) {
                    String time = String.format("%d:%02d", hour, minute);
                    result.add(time);
                }
            }
        }
        return result;
    }

    private int countBits(int num) {
        int count = 0;
        int n = num;
        while (n > 0) {
            count += n & 1;
            n >>= 1;
        }
        return count;
    }

    public static void main(String[] args) {
        BinaryWatch solution = new BinaryWatch();

        // 测试用例1
        int turnedOn1 = 1;
        List<String> result1 = solution.readBinaryWatch(turnedOn1);
        System.out.println("Test Case 1:");
        System.out.println(result1);

        // 测试用例2
        int turnedOn2 = 9;
        List<String> result2 = solution.readBinaryWatch(turnedOn2);
        System.out.println("Test Case 2:");
        System.out.println(result2);
    }
}

