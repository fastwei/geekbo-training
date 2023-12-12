package com.geekbo.training.leetcode.daily;

/**
 * 给定整数数组 nums，你需要选择不同下标 i 和 j，返回 nums[i] 和 nums[j] 的乘积的最大值。
 * 
 * 示例 1：
 * 输入：nums = [3,4,5,2]
 * 输出：12 
 * 解释：如果选择下标 i=1 和 j=2（下标从 0 开始），则可以获得最大乘积，即 (4-1)*(5-1)=3*4=12 。
 * 
 * 示例 2：
 * 输入：nums = [1,5,4,5]
 * 输出：16
 * 解释：选择下标 i=1 和 j=3（下标从 0 开始），则可以获得最大乘积，即 (5-1)*(5-1)=16 。
 * 
 * 示例 3：
 * 输入：nums = [3,7]
 * 输出：12
 * 
 * 提示：
 * 2 <= nums.length <= 500
 * 1 <= nums[i] <= 10^3
 */
public class MaximumProduct {
    /**
     * 解题思路：
     * 遍历数组，找出最大的两个数，然后计算它们的乘积减一。
     *
     * 算法复杂度分析：
     * 时间复杂度：O(n)，其中n为数组的长度，需要遍历整个数组。
     * 空间复杂度：O(1)，只使用了常数级别的额外空间。
     */
    public int maxProduct(int[] nums) {
        int max1 = 0;
        int max2 = 0;
        
        for (int num : nums) {
            if (num > max1) {
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max2 = num;
            }
        }
        
        return (max1 - 1) * (max2 - 1);
    }
    
    public static void main(String[] args) {
        MaximumProduct solution = new MaximumProduct();
        
        // 测试用例1
        int[] nums1 = {3, 4, 5, 2};
        int expected1 = 12;
        int result1 = solution.maxProduct(nums1);
        System.out.println("测试用例1：");
        System.out.println("Expected: " + expected1);
        System.out.println("Result: " + result1);
        
        // 测试用例2
        int[] nums2 = {1, 5, 4, 5};
        int expected2 = 16;
        int result2 = solution.maxProduct(nums2);
        System.out.println("测试用例2：");
        System.out.println("Expected: " + expected2);
        System.out.println("Result: " + result2);
        
        // 测试用例3
        int[] nums3 = {3, 7};
        int expected3 = 12;
        int result3 = solution.maxProduct(nums3);
        System.out.println("测试用例3：");
        System.out.println("Expected: " + expected3);
        System.out.println("Result: " + result3);
    }
}