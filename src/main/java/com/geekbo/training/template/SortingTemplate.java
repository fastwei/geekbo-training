package com.geekbo.training.template;

import java.util.Arrays;

public class SortingTemplate {

    public void sort(int[] nums) {
        // 调用具体的排序算法
        // 比如：quickSort(nums, 0, nums.length - 1);
        // 或者：mergeSort(nums, 0, nums.length - 1);
        // 或者其他排序算法的实现
    }

    public static void main(String[] args) {
        SortingTemplate sortingTemplate = new SortingTemplate();
        int[] nums = {5, 2, 9, 1, 3, 6};
        sortingTemplate.sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}