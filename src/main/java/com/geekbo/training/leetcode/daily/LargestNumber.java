package com.geekbo.training.leetcode.daily;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 179. Largest Number
 * Medium
 * Topics
 * Companies
 * Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.
 * <p>
 * Since the result may be very large, so you need to return a string instead of an integer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [10,2]
 * Output: "210"
 * Example 2:
 * <p>
 * Input: nums = [3,30,34,5,9]
 * Output: "9534330"
 */
public class LargestNumber {
    /**
     * 在这个实现中，我们首先将整数数组 nums 转换为字符串数组 numStrings。
     * 然后，我们定义一个自定义比较器 comparator，它用于比较两个数字。
     * 通过将数字串按不同的顺序进行拼接，并比较它们的大小来实现自定义比较。
     * <p>
     * 我们使用 Arrays.sort 方法并传入自定义比较器来对数字串进行排序。
     * 排序后，我们将排序后的数字串连接成一个单独的字符串。
     * <p>
     * 最后，我们删除结果字符串中的前导零（如果有的话），并将其作为最大的数字字符串返回。如果结果字符串为空，则返回 "0"。
     *
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        // Convert the integers to strings
        String[] numStrings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numStrings[i] = String.valueOf(nums[i]);
        }

        // Custom comparator to compare two numbers
        Comparator<String> comparator = new Comparator<String>() {
            public int compare(String a, String b) {
                String option1 = a + b;
                String option2 = b + a;
                return option2.compareTo(option1);
            }
        };

        // Sort the numbers using the custom comparator
        Arrays.sort(numStrings, comparator);

        // Join the sorted numbers into a single string
        StringBuilder sb = new StringBuilder();
        for (String num : numStrings) {
            sb.append(num);
        }

        // Remove leading zeros if any
        while (sb.charAt(0) == '0' && sb.length() > 1) {
            sb.deleteCharAt(0);
        }

        // Return the largest number as a string
        return sb.toString();
    }

    public static void main(String[] args) {
        LargestNumber solution = new LargestNumber();
        int[] nums1 = {10, 2};
        int[] nums2 = {3, 30, 34, 5, 9};
        System.out.println(solution.largestNumber(nums1)); // Output: "210"
        System.out.println(solution.largestNumber(nums2)); // Output: "9534330"
    }
}
