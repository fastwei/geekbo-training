package com.geekbo.training.leetcode.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1424. Diagonal Traverse II
 * Medium
 * Given a 2D integer array nums,
 * return all elements of nums in diagonal order as shown in the below images.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,4,2,7,5,3,8,6,9]
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
 * Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
 */

public class DiagonalTraverseII {

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int count = 0;

        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> row = nums.get(i);

            for (int j = 0; j < row.size(); j++) {
                int idx = i + j;

                if (lists.size() < idx + 1) {
                    lists.add(new ArrayList<>());
                }
                lists.get(idx).add(row.get(j));

                count ++;
            }
        }

        int[] res = new int[count];
        int idx = 0;
        for (List<Integer> list : lists) {
            for (int i = list.size() - 1; i >= 0; i--) {
                res[idx++] = list.get(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        DiagonalTraverseII solution = new DiagonalTraverseII();

        List<List<Integer>> nums1 = new ArrayList<>();
        nums1.add(Arrays.asList(1, 2, 3));
        nums1.add(Arrays.asList(4, 5, 6));
        nums1.add(Arrays.asList(7, 8, 9));
        int[] result1 = solution.findDiagonalOrder(nums1);
        for (int num : result1) {
            System.out.print(num + " ");
        }
        System.out.println();  // Output: 1 4 2 7 5 3 8 6 9

        List<List<Integer>> nums2 = new ArrayList<>();
        nums2.add(Arrays.asList(1, 2, 3, 4, 5));
        nums2.add(Arrays.asList(6, 7));
        nums2.add(Arrays.asList(8));
        nums2.add(Arrays.asList(9, 10, 11));
        nums2.add(Arrays.asList(12, 13, 14, 15, 16));
        int[] result2 = solution.findDiagonalOrder(nums2);
        for (int num : result2) {
            System.out.print(num + " ");
        }
        System.out.println();  // Output: 1 6 2 8 7 3 9 4 12 10 5 13 11 14 15 16
    }
}