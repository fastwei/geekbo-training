package com.geekbo.training.leetcode.premium100;

/**
 * 487. 最大连续1的个数 II
 * 中等
 * 给定一个二进制数组 nums ，如果最多可以翻转一个 0 ，则返回数组中连续 1 的最大个数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,0,1,1,0]
 * 输出：4
 * 解释：翻转第一个 0 可以得到最长的连续 1。
 * 当翻转以后，最大连续 1 的个数为 4。
 * 示例 2:
 * <p>
 * 输入：nums = [1,0,1,1,0,1]
 * 输出：4
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 105
 * nums[i] 不是 0 就是 1.
 * <p>
 * <p>
 * 进阶：如果输入的数字是作为 无限流 逐个输入如何处理？换句话说，内存不能存储下所有从流中输入的数字。您可以有效地解决吗？
 */
public class MaxConsecutiveOnesII {
    /**
     * 解题思路：
     * <p>
     * 使用两个变量 maxConsecutiveOnes 和 currentConsecutiveOnes 来记录最大连续1的个数和当前连续1的个数。
     * 遍历数组 nums，如果当前元素为1，则 currentConsecutiveOnes 加一，并更新 maxConsecutiveOnes。
     * 如果当前元素为0，则将 countZero 加一。如果 countZero 小于等于1，则 currentConsecutiveOnes 加一，并更新 maxConsecutiveOnes。否则，将 currentConsecutiveOnes 和 countZero 重置为0。
     * 最后返回 maxConsecutiveOnes。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n)，其中 n 是数组 nums 的长度。
     * 空间复杂度：O(1)。
     * todo:还有些测试用例不通过
     * @param nums
     * @return
     */
    public static int findMaxConsecutiveOnes(int[] nums) {
        int maxConsecutiveOnes = 0;
        int currentConsecutiveOnes = 0;
        int countZero = 0;

        for (int num : nums) {
            if (num == 1) {
                currentConsecutiveOnes++;
                maxConsecutiveOnes = Math.max(maxConsecutiveOnes, currentConsecutiveOnes);
            } else {
                countZero++;
                if (countZero <= 1) {
                    currentConsecutiveOnes++;
                    maxConsecutiveOnes = Math.max(maxConsecutiveOnes, currentConsecutiveOnes);
                } else {
                    currentConsecutiveOnes = 0;
                    countZero = 0;
                }
            }
        }

        return maxConsecutiveOnes;
    }
    //other
    public int findMaxConsecutiveOnes2(int[] nums) {
        int res = 0, count = 0;
        for(int l = 0, r = 0; r < nums.length; r++) {
            if(nums[r] == 0) {
                count++;
                while(count > 1) {
                    count -= nums[l++] == 0 ? 1 : 0;
                }
            }
            res = Math.max(res, r - l + 1);
        }

        return res;
    }
    public static void main(String[] args) {
        int[] nums1 = {1, 0, 1, 1, 0};
        int[] nums2 = {1, 0, 1, 1, 0, 1};

        System.out.println(findMaxConsecutiveOnes(nums1));  // Output: 4
        System.out.println(findMaxConsecutiveOnes(nums2));  // Output: 4
    }
}
