package com.geekbo.training.leetcode.binarysearch;

/**
 * Given an array of integers citations where citations[i] is the number of citations a researcher received
 * for their ith paper and citations is sorted in ascending order, return the researcher's h-index.
 * <p>
 * According to the definition of h-index on Wikipedia:
 * The h-index is defined as the maximum value of h
 * such that the given researcher has published at least h papers
 * that have each been cited at least h times.
 * <p>
 * You must write an algorithm that runs in logarithmic time.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: citations = [0,1,3,5,6]
 * Output: 3
 * Explanation: [0,1,3,5,6] means the researcher has 5 papers in total
 * and each of them had received 0, 1, 3, 5, 6 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations
 * each and the remaining two with no more than 3 citations each, their h-index is 3.
 * Example 2:
 * <p>
 * Input: citations = [1,2,100]
 * Output: 2
 */
public class HIndexII {
    /**
     * 解题思路：
     * <p>
     * 首先，我们定义一个变量n表示引用数组citations的长度。
     * 我们使用二分搜索来查找h值。我们初始化左边界left为0，右边界right为n-1。
     * 在每次迭代中，我们计算中间值mid，并计算当前mid对应的h值为n-mid。
     * 如果当前mid对应的h值等于引用次数citations[mid]，那么我们找到了h值，直接返回h值。
     * 如果当前mid对应的h值大于引用次数citations[mid]，说明h值在左侧，将right更新为mid-1。
     * 如果当前mid对应的h值小于引用次数citations[mid]，说明h值在右侧，将left更新为mid+1。
     * 循环结束后，left指向的就是h值，返回n-left即为h-index。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：二分搜索的时间复杂度为O(logn)，其中n是引用数组citations的长度。
     * 空间复杂度：算法的空间复杂度为O(1)，只使用了常数级别的额外空间。
     *
     * @param citations
     * @return
     */
    public static int hIndex(int[] citations) {
        int n = citations.length;
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int papers = n - mid; // 当前mid对应的h值

            // 如果当前mid对应的h值等于引用次数，直接返回h值
            if (citations[mid] == papers) {
                return papers;
            }

            // 如果当前mid对应的h值大于引用次数，说明h值在左侧，将right更新为mid - 1
            if (citations[mid] > papers) {
                right = mid - 1;
            }

            // 如果当前mid对应的h值小于引用次数，说明h值在右侧，将left更新为mid + 1
            if (citations[mid] < papers) {
                left = mid + 1;
            }
        }

        // 循环结束后，left指向的就是h值
        return n - left;
    }

    public static void main(String[] args) {
        int[] citations1 = {0, 1, 3, 5, 6};
        // 预期输出: 3
        System.out.println(hIndex(citations1));

        int[] citations2 = {1, 2, 100};
        // 预期输出: 2
        System.out.println(hIndex(citations2));
    }
}
