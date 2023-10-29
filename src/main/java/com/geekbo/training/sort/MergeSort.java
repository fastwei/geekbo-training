package com.geekbo.training.sort;

/**
 * 解题思路： 归并排序是一种分治算法。
 * 它将数组分成两个部分，分别对左右两个部分进行递归排序，然后将两个有序的部分合并成一个有序的数组。
 * <p>
 * 算法复杂度：
 * <p>
 * 归并排序的时间复杂度为O(nlogn)，其中n为数组的大小。
 * 归并排序的空间复杂度为O(n)，主要用于临时数组的存储。
 */
public class MergeSort {
    /**
     * 归并排序
     *
     * @param nums  待排序的数组
     * @param left  左边界
     * @param right 右边界
     */
    public void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2; // 计算中间位置
        mergeSort(nums, left, mid); // 对左半部分递归进行归并排序
        mergeSort(nums, mid + 1, right); // 对右半部分递归进行归并排序
        merge(nums, left, mid, right); // 合并两个有序数组
    }

    /**
     * 合并两个有序数组
     *
     * @param nums  待合并的数组
     * @param left  左边界
     * @param mid   中间位置
     * @param right 右边界
     */
    private void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1]; // 临时数组用于存储合并结果
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++]; // 将左边数组的元素放入临时数组
            } else {
                temp[k++] = nums[j++]; // 将右边数组的元素放入临时数组
            }
        }
        while (i <= mid) {
            temp[k++] = nums[i++]; // 将剩余的左边数组元素放入临时数组
        }
        while (j <= right) {
            temp[k++] = nums[j++]; // 将剩余的右边数组元素放入临时数组
        }
        System.arraycopy(temp, 0, nums, left, temp.length); // 将临时数组复制回原数组的对应位置
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] nums = {5, 3, 8, 6, 2, 7, 1, 4};
        mergeSort.mergeSort(nums, 0, nums.length - 1);
        for (int num : nums) {
            System.out.print(num + " "); // 预期输出：1 2 3 4 5 6 7 8
        }
    }
}



