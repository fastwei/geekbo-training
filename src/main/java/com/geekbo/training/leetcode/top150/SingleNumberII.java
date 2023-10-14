package com.geekbo.training.leetcode.top150;

/**
 * Given an integer array nums where every element appears three times except for one,
 * which appears exactly once. Find the single element and return it.
 * <p>
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,2,3,2]
 * Output: 3
 * Example 2:
 * <p>
 * Input: nums = [0,1,0,1,0,1,99]
 * Output: 99
 *
 * todo:有空加深理解
 */
public class SingleNumberII {
    /**
     * 找出数组中唯一出现一次的元素
     * <p>
     * 解题思路：
     * <p>
     * 数组中的所有元素都出现了三次，只有一个元素出现了一次。
     * 我们可以使用两个变量ones和twos来记录每个位上出现1和2的次数。
     * 遍历数组中的每个元素，更新ones和twos的值：
     * 如果某个位上出现了1，则ones对应位上的值为1，否则为0；
     * 如果某个位上出现了2，则twos对应位上的值为1，否则为0。
     * 最后，ones记录的值就是唯一出现一次的元素。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n)，其中n是数组的长度。我们需要遍历数组中的每个元素。
     * 空间复杂度：O(1)。除了存储结果的变量，我们没有使用额外的空间。
     *
     * @param nums 数组
     * @return 唯一出现一次的元素
     */
    public static int singleNumber(int[] nums) {
        int ones = 0;  // 记录每个位上出现1的次数
        int twos = 0;  // 记录每个位上出现2的次数

        for (int num : nums) {
            // 如果某个位上出现了1，则ones对应位上的值为1，否则为0
            ones = (ones ^ num) & ~twos;
            // 如果某个位上出现了2，则twos对应位上的值为1，否则为0
            twos = (twos ^ num) & ~ones;
        }

        return ones;
    }

    public static void main(String[] args) {
        // 测试用例1
        int[] nums1 = {2, 2, 3, 2};
        // 预期输出：3
        System.out.println(singleNumber(nums1));

        // 测试用例2
        int[] nums2 = {0, 1, 0, 1, 0, 1, 99};
        // 预期输出：99
        System.out.println(singleNumber(nums2));
    }
}