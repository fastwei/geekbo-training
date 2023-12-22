package com.geekbo.training.leetcode.crackinterview109;

import java.util.Arrays;

/**
 * 合并排序的数组
 */
public class MergeSortedArray {
    /**
     * 合并排序的数组
     * @param A 数组A，长度为m+n，其中前m个元素为有效元素
     * @param m 数组A的有效元素个数
     * @param B 数组B，长度为n
     * 时间复杂度：O(m+n)
     * 空间复杂度：O(1)
     */
    public static void merge(int[] A, int m, int[] B, int n) {
        int i = m - 1; // 指向数组A的末尾
        int j = n - 1; // 指向数组B的末尾
        int k = m + n - 1; // 指向数组A的最后一个空位

        while (i >= 0 && j >= 0) {
            if (A[i] > B[j]) {
                A[k] = A[i];
                i--;
            } else {
                A[k] = B[j];
                j--;
            }
            k--;
        }

        // 如果数组B还有剩余元素，将其依次放入数组A的前面
        while (j >= 0) {
            A[k] = B[j];
            j--;
            k--;
        }
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] B = {2, 5, 6};
        int n = 3;

        merge(A, m, B, n);

        System.out.println(Arrays.toString(A));
        // 输出: [1, 2, 2, 3, 5, 6]
    }
}