package com.geekbo.training.leetcode.top150;

import java.util.Arrays;

/**
 * Array / String
 *
 * 给定两个按非递减顺序排序的整数数组 nums1 和 nums2，以及两个整数 m 和 n，分别表示 nums1 和 nums2 中的元素数量。
 *
 * 将 nums1 和 nums2 合并成一个按非递减顺序排序的单一数组。
 *
 * 最终排序后的数组不应该通过函数返回，而是应存储在数组 nums1 中。为了实现这一点，nums1 的长度为 m + n，其中前 m 个元素表示应该合并的元素，而后面的 n 个元素被设置为 0，应该被忽略。nums2 的长度为 n。
 *
 * 这段代码的时间复杂度为O(m + n)，因为它只需要一次遍历两个数组的所有元素，而没有额外的嵌套循环。
 * 通过比较双指针指向的元素大小，将较大的元素依次放入合并后数组的末尾，然后不断移动指针，最终完成合并。
 *
 * Array / String
 */
public class MergeSortedArrays {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // 初始化指针，分别指向nums1和nums2的末尾
        int index1 = m - 1;
        int index2 = n - 1;
        // 初始化合并后数组的末尾位置
        int mergedIndex = m + n - 1;

        // 从后往前比较并合并两个数组的元素
        while (index1 >= 0 && index2 >= 0) {
            if (nums1[index1] > nums2[index2]) {
                // 如果nums1的元素较大，将其放入合并后数组的末尾
                nums1[mergedIndex] = nums1[index1];
                index1--;
            } else {
                // 如果nums2的元素较大，将其放入合并后数组的末尾
                nums1[mergedIndex] = nums2[index2];
                index2--;
            }
            mergedIndex--;
        }

        // 如果nums2中还有剩余元素，将其复制到合并后数组的前面位置
        while (index2 >= 0) {
            nums1[mergedIndex] = nums2[index2];
            index2--;
            mergedIndex--;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[6];
        nums1[0] = 1;
        nums1[1] = 2;
        nums1[2] = 3;
        int[] nums2 = {2, 5, 6};
        int m = 3;
        int n = 3;

        // 调用merge函数合并两个有序数组
        merge(nums1, m, nums2, n);

        // 打印合并后的数组
        System.out.println("Merged Array: " + Arrays.toString(nums1));
    }
}

