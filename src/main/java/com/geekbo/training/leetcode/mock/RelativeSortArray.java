package com.geekbo.training.leetcode.mock;

import java.util.*;

/**
 *
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
 *
 * Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2. Elements that do not appear in arr2 should be placed at the end of arr1 in ascending order.
 *
 *
 *
 * Example 1:
 *
 * Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * Output: [2,2,2,1,4,3,3,9,6,7,19]
 * Example 2:
 *
 * Input: arr1 = [28,6,22,8,44,17], arr2 = [22,28,8,6]
 * Output: [22,28,8,6,17,44]
 */
public class RelativeSortArray {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // 使用 TreeMap 存储 arr1 中的元素及其出现次数
        Map<Integer, Integer> countMap = new TreeMap<>();
        for (int num : arr1) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // 按照 arr2 的顺序构建结果数组
        int[] result = new int[arr1.length];
        int index = 0;

        for (int num : arr2) {
            int count = countMap.get(num);
            for (int i = 0; i < count; i++) {
                result[index++] = num;
            }
            countMap.remove(num);
        }

        // 处理剩余的元素，按升序排列
        for (int num : countMap.keySet()) {
            int count = countMap.get(num);
            for (int i = 0; i < count; i++) {
                result[index++] = num;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        RelativeSortArray solution = new RelativeSortArray();

        // 测试用例1
        int[] arr1 = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] arr2 = {2, 1, 4, 3, 9, 6};
        int[] result1 = solution.relativeSortArray(arr1, arr2);
        System.out.println(Arrays.toString(result1)); // [2, 2, 2, 1, 4, 3, 3, 9, 6, 7, 19]

        // 测试用例2
        int[] arr3 = {28, 6, 22, 8, 44, 17};
        int[] arr4 = {22, 28, 8, 6};
        int[] result2 = solution.relativeSortArray(arr3, arr4);
        System.out.println(Arrays.toString(result2)); // [22, 28, 8, 6, 17, 44]
    }
}
