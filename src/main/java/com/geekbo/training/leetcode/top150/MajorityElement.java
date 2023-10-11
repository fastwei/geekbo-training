package com.geekbo.training.leetcode.top150;

/**
 * Array / String
 *
 *绝大多数元素是指在数组中出现次数超过 ⌊n / 2⌋ 次的元素。你可以假设数组中一定存在绝大多数元素。
 *
 * 示例 1:
 *
 * 输入: nums = [3,2,3]
 * 输出: 3
 *
 * 示例 2:
 *
 * 输入: nums = [2,2,1,1,1,2,2]
 * 输出: 2
 *
 *
 *解决思路：
 *
 * 使用投票算法，初始化候选元素为数组的第一个元素，计数器为1。
 * 遍历数组，如果计数器为0，则重新选择候选元素；如果当前元素与候选元素相同，则增加计数器，否则减少计数器。
 * 验证候选元素是否为绝大多数元素，即出现次数超过半数。
 * 返回绝大多数元素。
 * 时间复杂度分析：
 *
 * 遍历数组一次，时间复杂度为 O(n)，其中 n 是数组的长度。
 * 验证候选元素出现的次数，时间复杂度为 O(n)。
 * 总体时间复杂度为 O(n)。
 *
 * Array / String
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
        // 初始化候选元素和计数器
        int candidate = nums[0];
        int count = 1;
        //[2,|2,1,1,1,2,2]
        /*
        *i--value---candidate---count
        * 1--2==2--2
        * 2--1!=2--1
        * 3--1!=2--0
        * 4--1--1--1
        * 5--2!=1--0
        * 6--2--2--1
        *
        *
        * */
        // 遍历数组
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                // 如果计数器为0，重新选择候选元素
                candidate = nums[i];
                count = 1;
            } else if (candidate == nums[i]) {
                // 如果当前元素与候选元素相同，增加计数器
                count++;
            } else {
                // 如果当前元素与候选元素不同，减少计数器
                count--;
            }
        }

        // 验证候选元素是否为绝大多数元素
        count = 0;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            }
        }

        // 返回绝大多数元素
        if (count > nums.length / 2) {
            return candidate;
        } else {
            throw new IllegalArgumentException("没有绝大多数元素");
        }
    }

    public static void main(String[] args) {
        MajorityElement solution = new MajorityElement();
        // 测试用例
        int[] nums1 = {3, 2, 3};
        int[] nums2 = {2, 2, 1, 1, 1, 2, 2};
        int[] nums3 = {2, 2, 1, 1, 1, 1, 2};

        System.out.println("Example 1 Output: " + solution.majorityElement(nums1)); // 输出: 3
        System.out.println("Example 2 Output: " + solution.majorityElement(nums2)); // 输出: 2
        System.out.println("Example 3 Output: " + solution.majorityElement(nums3)); // 输出: 1
    }
}
