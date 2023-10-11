package com.geekbo.training.leetcode.top150;

/**
 * Array / String
 *
 *给定一个整数数组nums，将数组向右旋转k步，其中k为非负整数。
 *示例 1:
 *
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 *
 *代码将整数数组向右旋转k个步骤，其中k是非负整数。代码的思路是先反转整个数组，然后再分别反转前k个元素和后面的元素。
 *
 *
 * 时间复杂度分析：
 *
 * 反转整个数组需要O(n)的时间，其中n是数组的长度。
 * 反转前k个元素需要O(k)的时间。
 * 反转剩余的元素也需要O(k)的时间。
 * 因此，总的时间复杂度为O(n) + O(k) + O(k)，即O(n + k)。在最坏情况下，k可以达到n，因此最终的时间复杂度为O(n)。
 *
 * 这个算法是一个原地旋转，因为它只使用常数额外空间，没有创建新的数组。
 *
 *Array / String
 */
public class RotateArray {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        
        // 如果k大于数组长度，取k的模来避免不必要的旋转
        k = k % n;
        
        // 反转整个数组
        reverse(nums, 0, n - 1);
        
        // 反转前k个元素
        reverse(nums, 0, k - 1);
        
        // 反转剩余的元素
        reverse(nums, k, n - 1);
    }
    
    // 辅助函数用于反转数组的一部分
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        RotateArray rotator = new RotateArray();

        // 测试用例1
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7};
        int k1 = 3;
        rotator.rotate(nums1, k1);
        System.out.print("Test Case 1: ");
        printArray(nums1);
        // 期望输出：[5, 6, 7, 1, 2, 3, 4]

        // 测试用例2
        int[] nums2 = {-1, -100, 3, 99};
        int k2 = 2;
        rotator.rotate(nums2, k2);
        System.out.print("Test Case 2: ");
        printArray(nums2);
        // 期望输出：[3, 99, -1, -100]
    }

    // 辅助函数用于打印数组
    private static void printArray(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

}
