package com.geekbo.training.leetcode.binarysearch;

/**
 * 278. First Bad Version
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 *
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which returns whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 5, bad = 4
 * Output: 4
 * Explanation:
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * Then 4 is the first bad version.
 * Example 2:
 *
 * Input: n = 1, bad = 1
 * Output: 1
 *
 */
public class FirstBadVersion {
    /**
     * 解题思路：
     * <p>
     * 由于版本是按照顺序递增的，可以使用二分查找来找到第一个坏版本。
     * 定义左边界left为1，右边界right为n，循环条件为left <= right。
     * 在每次循环中，找到中间版本mid，调用isBadVersion(mid)判断mid是否为坏版本。
     * 如果mid是坏版本，那么说明第一个坏版本在mid或者mid的左边，将right更新为mid-1。
     * 如果mid不是坏版本，那么说明第一个坏版本在mid的右边，将left更新为mid+1。
     * 循环结束后，返回left作为第一个坏版本的索引。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(log n)，其中n是版本的数量。每次二分查找都将版本数量缩小一半。
     * 空间复杂度：O(1)。
     */
    public static int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * isBadVersion方法用于判断某个版本是否是坏版本
     *
     * @param version 待判断的版本
     * @return 如果version是坏版本，返回true；否则，返回false
     */
    private static boolean isBadVersion(int version) {
        // 实现根据版本号判断是否为坏版本的逻辑
        return false;
    }

    /**
     * 以下为测试用例和预期结果
     */
    public static void main(String[] args) {
        int n1 = 5;
        int bad1 = 4;
        // Expected output: 4
        int result1 = firstBadVersion(n1);
        System.out.println(result1);

        int n2 = 1;
        int bad2 = 1;
        // Expected output: 1
        int result2 = firstBadVersion(n2);
        System.out.println(result2);
    }
}