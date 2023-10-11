package com.geekbo.training.leetcode.top75;
/**
 * Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where:
 *
 * answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
 * answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
 * Note that the integers in the lists may be returned in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,3], nums2 = [2,4,6]
 * Output: [[1,3],[4,6]]
 * Explanation:
 * For nums1, nums1[1] = 2 is present at index 0 of nums2, whereas nums1[0] = 1 and nums1[2] = 3 are not present in nums2. Therefore, answer[0] = [1,3].
 * For nums2, nums2[0] = 2 is present at index 1 of nums1, whereas nums2[1] = 4 and nums2[2] = 6 are not present in nums2. Therefore, answer[1] = [4,6].
 * Example 2:
 *
 * Input: nums1 = [1,2,3,3], nums2 = [1,1,2,2]
 * Output: [[3],[]]
 * Explanation:
 * For nums1, nums1[2] and nums1[3] are not present in nums2. Since nums1[2] == nums1[3], their value is only included once and answer[0] = [3].
 * Every integer in nums2 is present in nums1. Therefore, answer[1] = [].
 *
 * 题目描述：
 * 给定两个整数数组nums1和nums2，返回一个大小为2的列表answer，其中：
 * - answer[0]是nums1中所有不在nums2中出现的不重复整数的列表。
 * - answer[1]是nums2中所有不在nums1中出现的不重复整数的列表。
 * 注意，列表中的整数可以以任何顺序返回。
 *
 * 解题思路：
 * 使用两个集合（Set）分别存储nums1和nums2中的元素，然后遍历nums1和nums2，将不在另一个集合中的元素加入结果列表中。
 *
 * 算法复杂度分析：
 * - 时间复杂度：O(n)，其中n为两个数组的长度之和。
 * - 空间复杂度：O(m + k)，其中m和k分别为nums1和nums2中不同元素的数量。
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindTheDifferenceOfTwoArray {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        // 使用两个集合存储不同数组的元素
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for (int num : nums1) {
            set1.add(num);
        }

        for (int num : nums2) {
            set2.add(num);
        }

        List<Integer> answer1 = new ArrayList<>();
        List<Integer> answer2 = new ArrayList<>();

        //存在多个的也不显示，去重
        Set<Integer> set3 = new HashSet<>();
        Set<Integer> set4 = new HashSet<>();
        // 遍历nums1，将不在set2中的元素添加到answer1中
        for (int num : nums1) {
            if (!set2.contains(num)&&!set3.contains(num)) {
                answer1.add(num);
                set3.add(num);
            }
        }

        // 遍历nums2，将不在set1中的元素添加到answer2中
        for (int num : nums2) {
            if (!set1.contains(num)&&!set4.contains(num)) {
                answer2.add(num);
                set4.add(num);
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(answer1);
        result.add(answer2);

        return result;
    }

    public static void main(String[] args) {
        FindTheDifferenceOfTwoArray solution = new FindTheDifferenceOfTwoArray();

        // 测试用例1
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {2, 4, 6};
        List<List<Integer>> result1 = solution.findDifference(nums1, nums2);
        System.out.println("Example 1: Output: " + result1); // 预期输出：[[1, 3], [4, 6]]

        // 测试用例2
        int[] nums3 = {1, 2, 3, 3};
        int[] nums4 = {1, 1, 2, 2};
        List<List<Integer>> result2 = solution.findDifference(nums3, nums4);
        System.out.println("Example 2: Output: " + result2); // 预期输出：[[3], []]
    }
}
