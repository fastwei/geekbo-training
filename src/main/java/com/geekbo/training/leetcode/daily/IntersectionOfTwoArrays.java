package com.geekbo.training.leetcode.daily;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoArrays {
    /**
     * 寻找两个数组的交集
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n + m)，其中 n 和 m 分别为两个输入数组的长度。
     * 我们需要遍历两个数组分别构建两个集合，然后遍历其中一个集合判断元素是否存在于另一个集合中。
     * 空间复杂度：O(n + m)，我们需要额外的空间存储两个集合的元素。
     * 最坏情况下，两个集合的元素都不相同，所以需要的空间为两个数组的长度之和。
     *
     * @param nums1 第一个数组
     * @param nums2 第二个数组
     * @return 交集数组
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            if (set1.contains(num)) {
                set2.add(num);
            }
        }
        int[] result = new int[set2.size()];
        int i = 0;
        for (int num : set2) {
            result[i++] = num;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        // 数组nums1和nums2的交集为2
        int[] expected1 = {2};

        int[] nums3 = {4, 9, 5};
        int[] nums4 = {9, 4, 9, 8, 4};
        // 数组nums3和nums4的交集为4和9
        int[] expected2 = {4, 9};

        // 测试用例1
        int[] result1 = intersection(nums1, nums2);
        System.out.println(Arrays.toString(result1)); // 输出：[2]
        System.out.println(Arrays.equals(result1, expected1)); // true

        // 测试用例2
        int[] result2 = intersection(nums3, nums4);
        System.out.println(Arrays.toString(result2)); // 输出：[4, 9]
        System.out.println(Arrays.equals(result2, expected2)); // true
    }
}
