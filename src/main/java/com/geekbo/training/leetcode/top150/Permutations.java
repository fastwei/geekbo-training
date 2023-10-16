package com.geekbo.training.leetcode.top150;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array nums of distinct integers, return all the possible permutations.
 * You can return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * Example 2:
 * <p>
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 * Example 3:
 * <p>
 * Input: nums = [1]
 * Output: [[1]]
 */
public class Permutations {
    /**
     * 返回给定数组的所有可能排列。
     * <p>
     * <p>
     * 解题思路：
     * <p>
     * 我们使用回溯算法来生成排列。从给定数组的第一个元素开始，将其添加到当前排列中，并标记为已使用。
     * 然后递归调用，继续生成下一个元素的排列。当当前排列的长度等于给定数组的长度时，将当前排列添加到结果列表中。
     * 然后，我们从当前排列中移除最后一个元素，并将其标记为未使用，继续遍历下一个元素。
     * 通过这种方式，我们可以生成所有可能的排列。
     * <p>
     * 时间复杂度分析：
     * <p>
     * 我们需要生成 N! 个排列，其中 N 是给定数组的长度。
     * 在每个排列中，我们需要通过遍历数组来选择下一个未使用的元素。
     * 对于第一个位置，我们有 N 个选择，第二个位置有 N-1 个选择，第三个位置有 N-2 个选择，以此类推。
     * 因此，总的时间复杂度可以近似为 O(N × N!)。
     * 注意，这里的 N! 是生成所有排列的总数量，而 N 是给定数组的长度。
     * <p>
     * 空间复杂度分析：
     * <p>
     * 在回溯过程中，我们使用了一个额外的布尔数组 used 来标记已经使用的元素，以及一个列表 current 来保存当前的排列。
     * 除此之外，我们不需要使用额外的空间。因此，总的空间复杂度为 O(N)。
     *
     * @param nums 给定的数组
     * @return 所有可能的排列列表
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(result, current, nums, used);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> current, int[] nums, boolean[] used) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            current.add(nums[i]);
            used[i] = true;
            backtrack(result, current, nums, used);
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        Permutations permutations = new Permutations();

        // Test Case 1
        int[] nums1 = {1, 2, 3};
        List<List<Integer>> result1 = permutations.permute(nums1);
        System.out.println("Test Case 1:");
        System.out.println("Input: nums = " + arrayToString(nums1));
        System.out.println("Output: " + result1);

        // Test Case 2
        int[] nums2 = {0, 1};
        List<List<Integer>> result2 = permutations.permute(nums2);
        System.out.println("\nTest Case 2:");
        System.out.println("Input: nums = " + arrayToString(nums2));
        System.out.println("Output: " + result2);

        // Test Case 3
        int[] nums3 = {1};
        List<List<Integer>> result3 = permutations.permute(nums3);
        System.out.println("\nTest Case 3:");
        System.out.println("Input: nums = " + arrayToString(nums3));
        System.out.println("Output: " + result3);
    }

    // 辅助方法：将数组转换为字符串
    private static String arrayToString(int[] nums) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < nums.length; i++) {
            sb.append(nums[i]);
            if (i < nums.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}