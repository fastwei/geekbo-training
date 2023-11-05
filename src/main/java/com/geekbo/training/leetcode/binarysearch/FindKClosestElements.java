package com.geekbo.training.leetcode.binarysearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted integer array arr, two integers k and x,
 * return the k closest integers to x in the array. The result should also be sorted in ascending order.
 * <p>
 * An integer a is closer to x than an integer b if:
 * <p>
 * |a - x| < |b - x|, or
 * |a - x| == |b - x| and a < b
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
 * Output: [1,2,3,4]
 * Example 2:
 * <p>
 * Input: arr = [1,2,3,4,5], k = 4, x = -1
 * Output: [1,2,3,4]
 */
class FindKClosestElements {
    /**
     * 解题思路： 我们使用二分查找算法来确定最接近x的k个元素的起始位置。
     * 初始时，左指针left指向数组的第一个元素，右指针right指向数组的最后一个元素减去k。
     * <p>
     * 在每一次循环中，我们计算中间位置mid，然后比较arr[mid]与arr[mid + k]与x的距离。
     * 如果arr[mid]距离x比arr[mid + k]距离x更远，那么最接近x的k个元素的起始位置必然在[mid+1, right]之间；
     * 否则，最接近x的k个元素的起始位置必然在[left, mid]之间。
     * <p>
     * 最后，将起始位置left到left + k - 1的元素加入结果列表中，并返回。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：二分查找的时间复杂度为O(log(n-k))，其中n为数组的长度。将元素加入结果列表的时间复杂度为O(k)。
     * 因此，总时间复杂度为O(log(n-k) + k)。
     * 空间复杂度：结果列表的空间复杂度为O(k)。
     *
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - 1;

        // 使用二分查找确定最接近x的元素的位置
        while (right - left >= k) {
            int leftDiff = Math.abs(arr[left] - x);
            int rightDiff = Math.abs(arr[right] - x);
            if (leftDiff > rightDiff) {
                left++;
            } else {
                right--;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            result.add(arr[i]);
        }

        return result;
    }

    /**
     * 解题思路： 我们使用双指针来确定最接近x的k个元素的范围。
     * 初始时，左指针left指向数组的第一个元素，右指针right指向数组的最后一个元素。
     * <p>
     * 在每一次循环中，我们比较arr[left]和arr[right]与x的距离。
     * 如果arr[left]距离x更远，则将left右移一位；否则，将right左移一位。
     * 这样，每次循环我们都能够排除一个元素，直到剩余的元素个数为k。
     * <p>
     * 最后，将左指针left到右指针right之间的元素加入结果列表中，并返回。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：双指针的移动次数为O(n-k)，其中n为数组的长度。将元素加入结果列表的时间复杂度为O(k)。
     * 因此，总时间复杂度为O(n-k+k) = O(n)。
     * 空间复杂度：结果列表的空间复杂度为O(k)。
     *
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - 1;

        // 使用双指针来确定最接近x的k个元素的范围
        while (right - left + 1 > k) {
            // 如果arr[left]距离x更远，则将left右移一位
            if (Math.abs(arr[left] - x) > Math.abs(arr[right] - x)) {
                left++;
            } else {
                right--;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            result.add(arr[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        FindKClosestElements findKClosestElements = new FindKClosestElements();

        // 测试用例
        int[] arr1 = {1, 2, 3, 4, 5};
        int k1 = 4;
        int x1 = 3;
        // 预期输出: [1, 2, 3, 4]
        System.out.println(findKClosestElements.findClosestElements(arr1, k1, x1));

        int[] arr2 = {1, 2, 3, 4, 5};
        int k2 = 4;
        int x2 = -1;
        // 预期输出: [1, 2, 3, 4]
        System.out.println(findKClosestElements.findClosestElements(arr2, k2, x2));
    }
}

