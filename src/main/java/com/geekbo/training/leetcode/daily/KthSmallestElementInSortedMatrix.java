package com.geekbo.training.leetcode.daily;

import java.util.PriorityQueue;

/**
 * 378. Kth Smallest Element in a Sorted Matrix
 * Medium
 * Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.
 * <p>
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * <p>
 * You must find a solution with a memory complexity better than O(n2).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * Output: 13
 * Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
 * Example 2:
 * <p>
 * Input: matrix = [[-5]], k = 1
 * Output: -5
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 300
 * -109 <= matrix[i][j] <= 109
 * All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
 * 1 <= k <= n2
 * <p>
 * <p>
 * Follow up:
 * <p>
 * Could you solve the problem with a constant memory (i.e., O(1) memory complexity)?
 * Could you solve the problem in O(n) time complexity? The solution may be too advanced for an interview but you may find reading this paper fun.
 */
class KthSmallestElementInSortedMatrix {

    /**
     * 解题思路：
     * <p>
     * 使用二分查找的方法，在矩阵元素的范围内进行查找。
     * 选择一个中间值mid，统计矩阵中小于等于mid的元素的个数count。
     * 如果count小于k，说明第k个最小元素在mid的右侧，更新low为mid + 1。
     * 如果count大于等于k，说明第k个最小元素在mid的左侧或者等于mid，更新high为mid。
     * 重复上述步骤，直到low和high相等，此时low即为第k个最小元素。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：通过二分查找，每次查找的时间复杂度为O(log(maxValue - minValue))，每次统计小于等于mid的元素个数的时间复杂度为O(n)，因此总时间复杂度为O(nlog(maxValue - minValue))。在这个问题中，maxValue和minValue分别为矩阵的最大值和最小值，在题目给定的范围内。
     * 空间复杂度：仅使用常量额外空间，因此空间复杂度为O(1)。
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int low = matrix[0][0];
        int high = matrix[n - 1][n - 1];

        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = countLessOrEqual(matrix, mid);

            if (count < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    private int countLessOrEqual(int[][] matrix, int target) {
        int count = 0;
        int n = matrix.length;
        int i = n - 1;
        int j = 0;

        while (i >= 0 && j < n) {
            if (matrix[i][j] <= target) {
                count += i + 1;
                j++;
            } else {
                i--;
            }
        }

        return count;
    }

    /**
     * 解题思路：
     * <p>
     * 首先，将矩阵中的所有元素放入一个优先队列中，优先队列会自动按照元素的大小进行排序。
     * 弹出前k-1个元素，这样队列的头部就是第k个最小元素。
     * 返回第k个最小元素。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：将矩阵中的所有元素放入优先队列的时间复杂度为O(n^2log(n^2))，弹出前k-1个元素的时间复杂度为O(klog(n^2))，因此总时间复杂度为O(n^2log(n^2)+klog(n^2))。
     * 空间复杂度：除了存储矩阵的空间外，还需要额外的空间存储优先队列，因此空间复杂度为O(n^2)。
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallestQueue(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 将矩阵中的元素都放入优先队列
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pq.offer(matrix[i][j]);
            }
        }

        // 弹出前k-1个元素
        for (int i = 0; i < k - 1; i++) {
            pq.poll();
        }

        // 返回第k个最小元素
        return pq.poll();
    }

    public static void main(String[] args) {
        KthSmallestElementInSortedMatrix solution = new KthSmallestElementInSortedMatrix();

        // 测试用例1
        int[][] matrix1 = {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        int k1 = 8;
        int result1 = solution.kthSmallest(matrix1, k1);
        System.out.println("Test Case 1:");
        System.out.println("Expected: 13");
        System.out.println("Actual: " + result1);
        System.out.println();

        // 测试用例2
        int[][] matrix2 = {{-5}};
        int k2 = 1;
        int result2 = solution.kthSmallest(matrix2, k2);
        System.out.println("Test Case 2:");
        System.out.println("Expected: -5");
        System.out.println("Actual: " + result2);
        System.out.println();
    }
}

