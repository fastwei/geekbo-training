package com.geekbo.training.leetcode.binarysearch;

/**
 *
 * An array arr is a mountain if the following properties hold:
 * <p>
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given a mountain array arr, return the index i such that arr[0] < arr[1] < ... < arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1].
 * <p>
 * You must solve it in O(log(arr.length)) time complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [0,1,0]
 * Output: 1
 * Example 2:
 * <p>
 * Input: arr = [0,2,1,0]
 * Output: 1
 * Example 3:
 * <p>
 * Input: arr = [0,10,5,2]
 * Output: 1
 */
public class MountainArray {
    /**
     * 解题思路： 我们可以使用二分查找来解决这个问题。
     * <p>
     * 根据题目的要求，峰值元素必须满足arr[i] > arr[i+1]，并且arr[i] > arr[i-1]。
     * 我们可以通过比较中间元素arr[mid]与其相邻的右侧元素arr[mid+1]的大小来确定峰值元素的位置。
     * <p>
     * 如果arr[mid] > arr[mid+1]，则说明峰值在左侧，我们将搜索范围缩小到[left, mid]。
     * 反之，如果arr[mid] <= arr[mid+1]，则说明峰值在右侧，我们将搜索范围缩小到[mid+1, right]。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：每次迭代中，我们将搜索范围缩小一半，所以总共的时间复杂度为O(log(n))，其中n为数组的长度。
     * 空间复杂度：我们只使用了常数级别的额外空间，因此空间复杂度为O(1)。
     *
     * @param arr
     * @return
     */
    public static int peakIndexInMountainArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // 如果中间元素大于其相邻的右侧元素，则峰值在左侧
            if (arr[mid] > arr[mid + 1]) {
                right = mid;
            } else {
                // 否则，峰值在右侧
                left = mid + 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        // 测试用例
        int[] arr1 = {0, 1, 0};
        // 预期输出: 1
        System.out.println(peakIndexInMountainArray(arr1));

        int[] arr2 = {0, 2, 1, 0};
        // 预期输出: 1
        System.out.println(peakIndexInMountainArray(arr2));

        int[] arr3 = {0, 10, 5, 2};
        // 预期输出: 1
        System.out.println(peakIndexInMountainArray(arr3));
    }
}
