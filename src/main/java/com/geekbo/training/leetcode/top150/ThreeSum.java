package com.geekbo.training.leetcode.top150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ThreeSum {

    /**
     * 解题思路：
     *
     * 先对数组进行排序，然后使用三个指针：一个在外循环，两个在内循环。
     * 外循环从数组头部开始，内循环使用左右两个指针，分别指向当前元素后面和数组末尾。
     * 如果三个元素之和等于0，将它们添加到结果列表中，并且移动左右指针。
     * 如果和小于0，移动左指针；如果和大于0，移动右指针。
     * 避免重复解，跳过相同的元素。
     * 循环直到外循环遍历完整个数组。
     * 算法复杂度分析：
     *
     * 时间复杂度：O(n^2)，其中 n 是数组的长度。
     * 空间复杂度：O(1)。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // 先对数组进行排序

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int left = i + 1;
                int right = nums.length - 1;
                int target = -nums[i];

                while (left < right) {
                    if (nums[left] + nums[right] == target) {
                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left++;
                        right--;
                    } else if (nums[left] + nums[right] < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println(solution.threeSum(nums1)); // 预期输出：[[-1, -1, 2], [-1, 0, 1]]

        int[] nums2 = {0, 1, 1};
        System.out.println(solution.threeSum(nums2)); // 预期输出：[]

        int[] nums3 = {0, 0, 0};
        System.out.println(solution.threeSum(nums3)); // 预期输出：[[0, 0, 0]]
    }
}
