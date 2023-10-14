package com.geekbo.training.leetcode.top150;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a sorted unique integer array nums.
 * <p>
 * A range [a,b] is the set of all integers from a to b (inclusive).
 * <p>
 * Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.
 * <p>
 * Each range [a,b] in the list should be output as:
 * <p>
 * "a->b" if a != b
 * "a" if a == b
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: The ranges are:
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 * Example 2:
 * <p>
 * Input: nums = [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: The ranges are:
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 */
public class SummaryRanges {


    /**
     *
     * 优化思路：
     *
     * 使用两个指针start和end来记录当前区间的起始数字和结束数字。
     * 遍历数组，如果当前数字与end连续递增，则更新end为当前数字；
     * 否则，将当前区间的字符串表示添加到结果列表中，并更新start和end为当前数字。
     * 在循环结束后，将最后一个区间的字符串表示添加到结果列表中。
     * 优化后的算法性能：
     *
     * 时间复杂度：遍历整个数组需要O(n)的时间，其中n为数组的长度。
     * 在遍历过程中，每个数字最多会被比较一次，因此总体时间复杂度为O(n)。
     * 空间复杂度：使用了一个结果列表来存储区间的字符串表示，最坏情况下，结果列表的大小为n。因此，空间复杂度为O(n)。
     *
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> ranges = new ArrayList<>();

        if (nums.length == 0) {
            return ranges;
        }

        int start = nums[0];
        int end = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == end + 1) {
                end = nums[i];
            } else {
                if (start == end) {
                    ranges.add(Integer.toString(start));
                } else {
                    ranges.add(start + "->" + end);
                }
                start = nums[i];
                end = nums[i];
            }
        }

        if (start == end) {
            ranges.add(Integer.toString(start));
        } else {
            ranges.add(start + "->" + end);
        }

        return ranges;
    }

    /**
     * 解题思路：
     * <p>
     * 遍历数组，对于每个数字，找到连续递增的最后一个数字。
     * 如果连续递增的区间只包含一个数字，则将该数字以字符串形式添加到结果列表中；
     * 否则，添加区间的起始数字和结束数字以"->"连接的形式添加到结果列表中。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：遍历整个数组需要O(n)的时间，其中n为数组的长度。
     * 在遍历过程中，每个数字最多会被比较一次，因此总体时间复杂度为O(n)。
     * 空间复杂度：使用了一个结果列表来存储区间的字符串表示，最坏情况下，结果列表的大小为n。因此，空间复杂度为O(n)。
     *todo:这个算法是n^2的复杂度?
     * @param nums
     * @return
     */
    public List<String> summaryRanges2(int[] nums) {
        List<String> ranges = new ArrayList<>();

        if (nums.length == 0) {
            return ranges;
        }

        for (int i = 0; i < nums.length; i++) {
            int start = nums[i];

            // 找到连续递增的最后一个数字
            while (i < nums.length - 1 && nums[i] + 1 == nums[i + 1]) {
                i++;
            }

            int end = nums[i];

            // 添加区间到结果列表
            if (start == end) {
                ranges.add(Integer.toString(start));
            } else {
                ranges.add(start + "->" + end);
            }
        }

        return ranges;
    }

    public static void main(String[] args) {
        SummaryRanges solution = new SummaryRanges();

        // Test Case 1
        int[] nums1 = {0, 1, 2, 4, 5, 7};
        List<String> result1 = solution.summaryRanges(nums1);
        List<String> expected1 = new ArrayList<>();
        expected1.add("0->2");
        expected1.add("4->5");
        expected1.add("7");
        System.out.println(result1.equals(expected1)); // true

        // Test Case 2
        int[] nums2 = {0, 2, 3, 4, 6, 8, 9};
        List<String> result2 = solution.summaryRanges(nums2);
        List<String> expected2 = new ArrayList<>();
        expected2.add("0");
        expected2.add("2->4");
        expected2.add("6");
        expected2.add("8->9");
        System.out.println(result2.equals(expected2)); // true
    }
}