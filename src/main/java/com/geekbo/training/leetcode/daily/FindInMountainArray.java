package com.geekbo.training.leetcode.daily;

/**
 * (This problem is an interactive problem.)
 * <p>
 * You may recall that an array arr is a mountain array if and only if:
 * <p>
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target. If such an index does not exist, return -1.
 * <p>
 * You cannot access the mountain array directly. You may only access the array using a MountainArray interface:
 * <p>
 * MountainArray.get(k) returns the element of the array at index k (0-indexed).
 * MountainArray.length() returns the length of the array.
 * Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: array = [1,2,3,4,5,3,1], target = 3
 * Output: 2
 * Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.
 * Example 2:
 * <p>
 * Input: array = [0,1,2,4,2,1], target = 3
 * Output: -1
 * Explanation: 3 does not exist in the array, so we return -1.
 */

/**
 * MountainArray接口的实现类
 */
class MountainArrayImpl implements MountainArray {
    private int[] arr;

    public MountainArrayImpl(int[] arr) {
        this.arr = arr;
    }

    @Override
    public int get(int index) {
        return arr[index];
    }

    @Override
    public int length() {
        return arr.length;
    }
}

/**
 * MountainArray接口
 */
interface MountainArray {
    int get(int index);

    int length();
}

class FindInMountainArray {
    /**
     * 解题思路：
     * <p>
     * 由于无法直接访问山脉数组，我们需要使用MountainArray接口提供的方法来访问数组。
     * 首先，我们需要找到山顶元素的索引，也就是满足arr[i] > arr[i+1]的最大索引i。
     * 然后，我们可以在arr的前半部分和后半部分分别进行二分查找。
     * 在前半部分，我们可以使用标准的二分查找算法来查找目标元素target。
     * 在后半部分，我们可以使用逆向的二分查找算法来查找目标元素target。
     * 如果在前半部分找到了目标元素target，那么直接返回该索引。
     * 如果在后半部分找到了目标元素target，那么返回山脉数组的长度减去该索引再减一。
     * 如果都没有找到，那么返回-1。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(log n)，其中n是山脉数组的长度。需要进行两次二分查找。
     * 空间复杂度：O(1)。
     *
     * @param target
     * @param mountainArr
     * @return
     */
    public static int findInMountainArray(int target, MountainArray mountainArr) {
        int peakIndex = findPeakIndex(mountainArr);
        int left = binarySearch(target, mountainArr, 0, peakIndex, true);
        if (left != -1) {
            return left;
        }
        int right = binarySearch(target, mountainArr, peakIndex + 1, mountainArr.length() - 1, false);
        if (right != -1) {
            return right;
        }
        return -1;
    }

    private static int findPeakIndex(MountainArray mountainArr) {
        int left = 0;
        int right = mountainArr.length() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private static int binarySearch(int target, MountainArray mountainArr, int left, int right, boolean isAscending) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = mountainArr.get(mid);
            if (midValue == target) {
                return mid;
            } else if ((isAscending && midValue < target) || (!isAscending && midValue > target)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        MountainArray mountainArr1 = new MountainArrayImpl(new int[]{1, 2, 3, 4, 5, 3, 1});
        int target1 = 3;
        // Expected output: 2
        int result1 = FindInMountainArray.findInMountainArray(target1, mountainArr1);
        System.out.println(result1);

        MountainArray mountainArr2 = new MountainArrayImpl(new int[]{0, 1, 2, 4, 2, 1});
        int target2 = 3;
        // Expected output: -1
        int result2 = FindInMountainArray.findInMountainArray(target2, mountainArr2);
        System.out.println(result2);

    }



}

