package com.geekbo.training.leetcode.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,3]
 * Output: [3]
 * Example 2:
 * <p>
 * Input: nums = [1]
 * Output: [1]
 * Example 3:
 * <p>
 * Input: nums = [1,2]
 * Output: [1,2]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 5 * 104
 * -109 <= nums[i] <= 109
 * <p>
 * <p>
 * Follow up: Could you solve the problem in linear time and in O(1) space?
 */
public class MajorityElementII {
    /**
     * 解题思路： 这道题可以使用摩尔投票算法来解决。
     * 我们使用两个变量candidate1和candidate2来表示可能的众数，以及两个变量count1和count2来表示它们出现的次数。
     * <p>
     * 我们遍历整个数组，对于数组中的每个元素，如果它等于candidate1，则将count1加1；
     * 如果它等于candidate2，则将count2加1；如果count1为0，则将当前元素设置为candidate1，并将count1设置为1；
     * 如果count2为0，则将当前元素设置为candidate2，并将count2设置为1；
     * 如果当前元素既不等于candidate1，也不等于candidate2，则将count1和count2都减1。
     * <p>
     * 遍历完整个数组后，我们需要再次遍历数组来确定candidate1和candidate2出现的次数。
     * 如果count1大于nums.length / 3，则将candidate1加入结果列表；
     * 如果count2大于nums.length / 3，则将candidate2加入结果列表。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历数组两次，时间复杂度为O(n)，其中n是数组的长度。
     * 空间复杂度：使用了常数级别的额外空间，空间复杂度为O(1)。
     *
     * todo:加深理解
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return result;
        }

        int candidate1 = 0;
        int candidate2 = 0;
        int count1 = 0;
        int count2 = 0;

        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;

        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            }
        }

        if (count1 > nums.length / 3) {
            result.add(candidate1);
        }

        if (count2 > nums.length / 3) {
            result.add(candidate2);
        }

        return result;
    }

    public static void main(String[] args) {
        MajorityElementII majorityElementII = new MajorityElementII();

        // Test Case 1
        int[] nums1 = {3, 2, 3};
        List<Integer> result1 = majorityElementII.majorityElement(nums1);
        System.out.println(result1);  // Expected output: [3]

        // Test Case 2
        int[] nums2 = {1};
        List<Integer> result2 = majorityElementII.majorityElement(nums2);
        System.out.println(result2);  // Expected output: [1]

        // Test Case 3
        int[] nums3 = {1, 2};
        List<Integer> result3 = majorityElementII.majorityElement(nums3);
        System.out.println(result3);  // Expected output: [1, 2]
    }
}
