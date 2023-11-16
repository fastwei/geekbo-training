package com.geekbo.training.leetcode.daily;

import java.util.HashSet;
import java.util.Set;

/**
 * 1980. Find Unique Binary String
 * Medium
 * Given an array of strings nums containing n unique binary strings each of length n,
 * return a binary string of length n that does not appear in nums.
 * If there are multiple answers, you may return any of them.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = ["01","10"]
 * Output: "11"
 * Explanation: "11" does not appear in nums. "00" would also be correct.
 * Example 2:
 * <p>
 * Input: nums = ["00","01"]
 * Output: "11"
 * Explanation: "11" does not appear in nums. "10" would also be correct.
 * Example 3:
 * <p>
 * Input: nums = ["111","011","001"]
 * Output: "101"
 * Explanation: "101" does not appear in nums. "000", "010", "100", and "110" would also be correct.
 */
public class FindUniqueBinaryString {

    /**
     * 当给定一个包含n个长度为n的唯一二进制字符串的数组nums时，我们需要找到一个长度为n的二进制字符串，它在nums中不存在。
     * 如果存在多个答案，可以返回任意一个。
     * <p>
     * 解题思路： 我们可以使用一个Set来存储nums中的所有二进制字符串。
     * 然后从0到2^n-1的数字中遍历，将每个数字转换为长度为n的二进制字符串。
     * 如果这个二进制字符串不在Set中，那么它就是我们要找的结果。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 遍历nums数组并将元素添加到Set中的时间复杂度为O(n^2)，其中n是二进制字符串的长度。
     * 从0到2^n-1的数字中遍历的时间复杂度为O(2^n)。
     * 因此，总的时间复杂度为O(n^2 + 2^n)。
     *
     * @param nums
     * @return
     */
    public static String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        Set<String> set = new HashSet<>();

        // 将nums中的二进制字符串添加到set中
        for (String num : nums) {
            set.add(num);
        }

        // 从0到2^n-1的数字中遍历
        for (int i = 0; i < (1 << n); i++) {
            String binaryString = Integer.toBinaryString(i);
            int diff = n - binaryString.length();

            // 在二进制字符串的前面补0，使其长度为n
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < diff; j++) {
                sb.append('0');
            }
            sb.append(binaryString);

            // 如果二进制字符串不在nums中，则返回它
            if (!set.contains(sb.toString())) {
                return sb.toString();
            }
        }

        return "";
    }

    public static void main(String[] args) {
        // Test case 1
        String[] nums1 = {"01", "10"};
        // "11" 不在 nums1 中
        String expected1 = "11";
        String result1 = findDifferentBinaryString(nums1);
        System.out.println(result1.equals(expected1)); // Output: true

        // Test case 2
        String[] nums2 = {"00", "01"};
        // "11" 不在 nums2 中
        String expected2 = "11";
        String result2 = findDifferentBinaryString(nums2);
        System.out.println(result2.equals(expected2)); // Output: true

        // Test case 3
        String[] nums3 = {"111", "011", "001"};
        // "101" 不在 nums3 中
        String expected3 = "101";
        String result3 = findDifferentBinaryString(nums3);
        System.out.println(result3.equals(expected3)); // Output: true
    }
}