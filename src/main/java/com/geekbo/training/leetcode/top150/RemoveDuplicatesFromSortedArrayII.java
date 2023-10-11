package com.geekbo.training.leetcode.top150;

/**
 * Array / String
 *
 * 假设有一个按非递减顺序排列的整数数组 nums，需要原地删除一些重复元素，使得每个唯一元素最多出现两次。同时，要保持元素的相对顺序不变。
 * <p>
 * 由于在某些编程语言中无法改变数组的长度，因此必须将结果放在数组 nums 的前部。更正式地说，如果删除重复元素后还剩下 k 个元素，那么数组 nums 的前 k 个元素应该包含最终结果。不用担心在第 k 个元素之后留下什么。
 * <p>
 * 在将最终结果放入 nums 的前 k 个插槽后，返回 k。
 * <p>
 * 不要为另一个数组分配额外的空间。必须通过修改输入数组来原地操作，且额外内存使用为 O(1)。
 * <p>
 * 解决思路：
 * <p>
 * 首先，处理特殊情况：如果数组为空，直接返回0。
 * 使用两个指针，一个指针i用于遍历数组，另一个计数器count用于记录当前元素的重复次数。
 * 遍历整个数组，如果当前元素与前一个元素相等且count小于2，则将当前元素复制到指针i位置，并递增i和count。
 * 如果当前元素与前一个元素不相等，将当前元素复制到指针i位置，并重置count为1。
 * 最后返回指针i的值，即去重后的元素个数。
 * 时间复杂度分析：
 * <p>
 * 由于只遍历一次数组，时间复杂度为O(n)，其中n是数组的长度。因为没有使用额外的数据结构，额外的内存空间为O(1)。
 *
 *
 * Array / String
 */
public class RemoveDuplicatesFromSortedArrayII {
    public int removeDuplicates(int[] nums) {
        // 特殊情况处理：如果数组为空，则返回0
        if (nums.length == 0) {
            return 0;
        }

        // 初始化计数器和指针
        int count = 1; // 初始计数为1，因为第一个元素不需要重复判断
        int i = 1; // i为指针，从第二个元素开始遍历

        // 遍历整个数组
        for (int j = 1; j < nums.length; j++) {
            // 如果当前元素与前一个元素相等，并且计数器小于2
            if (nums[j] == nums[j - 1] && count < 2) {
                nums[i] = nums[j]; // 将当前元素复制到指针位置
                i++; // 指针移动
                count++; // 计数器增加
            }
            // 如果当前元素与前一个元素不相等
            else if (nums[j] != nums[j - 1]) {
                nums[i] = nums[j]; // 将当前元素复制到指针位置
                i++; // 指针移动
                count = 1; // 计数器重置为1
            }
            // 如果当前元素与前一个元素相等，并且计数器已经达到2
            // 则不做任何操作，跳过重复元素
        }

        return i; // 返回最终的元素个数
    }

    public static void main(String[] args) {

        RemoveDuplicatesFromSortedArrayII solution = new RemoveDuplicatesFromSortedArrayII();
        // 测试用例1
        int[] nums1 = {1,1,1,2,2,3};
        int result1 = solution.removeDuplicates(nums1);
        System.out.println("Test Case 1:");
        System.out.println("Expected Output: [1,1,2,2,3,_]");
        System.out.print("Actual Output: [");
        for (int i = 0; i < result1; i++) {
            System.out.print(nums1[i]);
            if (i < result1 - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        // 测试用例2
        int[] nums2 = {0,0,1,1,1,1,2,3,3};
        int result2 = solution.removeDuplicates(nums2);
        System.out.println("\nTest Case 2:");
        System.out.println("Expected Output: [0,0,1,1,2,3,3,_,_]");
        System.out.print("Actual Output: [");
        for (int i = 0; i < result2; i++) {
            System.out.print(nums2[i]);
            if (i < result2 - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

}
