package com.geekbo.training.leetcode.daily;

/**
 * Find The Original Array of Prefix Xor
 * Medium
 * Topics
 * Companies
 * Hint
 * You are given an integer array pref of size n. Find and return the array arr of size n that satisfies:
 * <p>
 * pref[i] = arr[0] ^ arr[1] ^ ... ^ arr[i].
 * Note that ^ denotes the bitwise-xor operation.
 * <p>
 * It can be proven that the answer is unique.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: pref = [5,2,0,3,1]
 * Output: [5,7,2,3,2]
 * Explanation: From the array [5,7,2,3,2] we have the following:
 * - pref[0] = 5.
 * - pref[1] = 5 ^ 7 = 2.
 * - pref[2] = 5 ^ 7 ^ 2 = 0.
 * - pref[3] = 5 ^ 7 ^ 2 ^ 3 = 3.
 * - pref[4] = 5 ^ 7 ^ 2 ^ 3 ^ 2 = 1.
 * Example 2:
 * <p>
 * Input: pref = [13]
 * Output: [13]
 * Explanation: We have pref[0] = arr[0] = 13.
 */
public class FindTheOriginalArrayOfPrefixXor {
    /**
     * 解题思路：
     * <p>
     * 给定一个大小为n的整数数组pref，要求找到一个大小为n的数组arr，
     * 使得pref[i] = arr[0] ^ arr[1] ^ ... ^ arr[i]，其中^表示按位异或运算。
     * 通过观察可以发现，arr[i] = pref[i] ^ arr[i-1]。
     * 初始化arr[0]为pref[0]。
     * 从i = 1开始遍历数组pref，根据上面的公式计算arr[i]。
     * 返回数组arr。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n)，其中n是数组pref的长度。需要遍历整个数组pref一次。
     * 空间复杂度：O(n)，需要创建一个大小为n的数组arr来存储结果。
     *
     * @param pref
     * @return
     */
    public static int[] findArray(int[] pref) {
        int[] result = new int[pref.length];
        result[0] = pref[0];
        for(int i = 1; i < pref.length; i++) {
            result[i] = pref[i] ^ pref[i-1];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] pref1 = {5, 2, 0, 3, 1};
        // Expected output: [5, 7, 2, 3, 2]
        int[] arr1 = findArray(pref1);
        for (int num : arr1) {
            System.out.print(num + " ");
        }
        System.out.println();

        int[] pref2 = {13};
        // Expected output: [13]
        int[] arr2 = findArray(pref2);
        for (int num : arr2) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}


