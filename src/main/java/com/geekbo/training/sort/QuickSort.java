package com.geekbo.training.sort;

/**
 * 解题思路： 快速排序是一种分治算法。
 * 它选择一个元素作为分区点，将数组划分为左右两个部分，左边部分的元素小于等于分区点，右边部分的元素大于分区点。
 * 然后对左右两个部分分别进行递归排序，最后合并得到有序数组。
 * <p>
 * 算法复杂度：
 * <p>
 * 平均情况下，快速排序的时间复杂度为O(nlogn)，其中n为数组的大小。
 * 最坏情况下，快速排序的时间复杂度为O(n^2)，当数组已经有序或者逆序时。
 * 空间复杂度为O(logn)，主要用于递归调用时的 额外空间和调用栈。
 */
public class QuickSort {
    /**
     * 快速排序
     *
     * @param nums  待排序的数组
     * @param left  左边界
     * @param right 右边界
     */
    public void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivotIndex = partition(nums, left, right); // 获取分区点的索引
        quickSort(nums, left, pivotIndex - 1); // 对左半部分递归进行快速排序
        quickSort(nums, pivotIndex + 1, right); // 对右半部分递归进行快速排序
    }

    /**
     * 划分数组，返回分区点的索引
     *
     * @param nums  待划分的数组
     * @param left  左边界
     * @param right 右边界
     * @return 分区点的索引
     */
    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left]; // 选择左边界作为分区点
        int i = left, j = right;
        while (i < j) {
            // 从右往左找到第一个小于分区点的元素
            while (i < j && nums[j] >= pivot) {
                j--;
            }
            if (i < j) {
                nums[i] = nums[j]; // 将该元素放到左边
                i++;
            }
            // 从左往右找到第一个大于等于分区点的元素
            while (i < j && nums[i] < pivot) {
                i++;
            }
            if (i < j) {
                nums[j] = nums[i]; // 将该元素放到右边
                j--;
            }
        }
        nums[i] = pivot; // 将分区点放到正确的位置
        return i;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] nums = {5, 3, 8, 6, 2, 7, 1, 4};
        quickSort.quickSort(nums, 0, nums.length - 1);
        for (int num : nums) {
            System.out.print(num + " "); // 预期输出：1 2 3 4 5 6 7 8
        }
    }
}

