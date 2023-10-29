package com.geekbo.training.sort;

import java.util.Arrays;

/**
 * 解题思路：
 * 选择排序：每次从未排序的部分选择最小（或最大）的元素放到已排序部分的末尾。
 * 插入排序：将待排序元素插入到已排序数组的正确位置，保持已排序部分始终有序。
 * 冒泡排序：依次比较相邻的两个元素，如果顺序不正确则交换，使得较大（或较小）的元素逐渐移动到数组的末尾。
 * <p>
 * 算法复杂度：
 * <p>
 * 选择排序的时间复杂度为O(n^2)，其中n为数组的大小。
 * 插入排序的时间复杂度为O(n^2)，其中n为数组的大小。
 * 冒泡排序的时间复杂度为O(n^2)，其中n为数组的大小。
 */
public class SortingAlgorithms {
    /**
     * 选择排序
     *
     * @param nums 待排序的数组
     */
    public void selectionSort(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            swap(nums, i, minIndex);
        }
    }

    /**
     * 插入排序
     *
     * @param nums 待排序的数组
     */
    public void insertionSort(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int key = nums[i];
            int j = i - 1;
            while (j >= 0 && nums[j] > key) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = key;
        }
    }

    /**
     * 冒泡排序
     *
     * @param nums 待排序的数组
     */
    public void bubbleSort(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    /**
     * 交换数组中两个元素的位置
     *
     * @param nums 数组
     * @param i    第一个元素的索引
     * @param j    第二个元素的索引
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        SortingAlgorithms sortingAlgorithms = new SortingAlgorithms();
        int[] nums = {5, 3, 8, 6, 2, 7, 1, 4};

        // 选择排序
        sortingAlgorithms.selectionSort(nums.clone()); // 克隆数组，避免原始数据被修改
        System.out.println("选择排序结果：" + Arrays.toString(nums)); // 预期输出：[1, 2, 3, 4, 5, 6, 7, 8]

        // 插入排序
        sortingAlgorithms.insertionSort(nums.clone());
        System.out.println("插入排序结果：" + Arrays.toString(nums)); // 预期输出：[1, 2, 3, 4, 5, 6, 7, 8]

        // 冒泡排序
        sortingAlgorithms.bubbleSort(nums.clone());
        System.out.println("冒泡排序结果：" + Arrays.toString(nums)); // 预期输出：[1, 2, 3, 4, 5, 6, 7, 8]
    }
}


