package com.geekbo.training.leetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1306. Jump Game III
 * Medium
 * Given an array of non-negative integers arr,
 * you are initially positioned at start index of the array.
 * When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach any index with value 0.
 * <p>
 * Notice that you can not jump outside of the array at any time.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [4,2,3,0,3,1,2], start = 5
 * Output: true
 * Explanation:
 * All possible ways to reach at index 3 with value 0 are:
 * index 5 -> index 4 -> index 1 -> index 3
 * index 5 -> index 6 -> index 4 -> index 1 -> index 3
 * Example 2:
 * <p>
 * Input: arr = [4,2,3,0,3,1,2], start = 0
 * Output: true
 * Explanation:
 * One possible way to reach at index 3 with value 0 is:
 * index 0 -> index 4 -> index 1 -> index 3
 * Example 3:
 * <p>
 * Input: arr = [3,0,2,1,2], start = 2
 * Output: false
 * Explanation: There is no way to reach at index 1 with value 0.
 */
class JumpGameIII {
    /**
     * 解题思路： 这道题可以使用广度优先搜索来解决。
     * 我们使用队列来存储待访问的索引，初始时将起始索引加入队列。
     * 然后，不断从队列中取出索引进行遍历，对于当前索引进行判断，如果当前索引对应的元素值为0，则说明可以到达目标位置，返回true。
     * 否则，根据当前索引可以跳转到的左右两个索引，将未访问过的索引加入队列。当队列为空时，说明无法到达目标位置，返回false。
     * <p>
     * 算法复杂度分析： 假设数组的长度为n。
     * <p>
     * 在最坏的情况下，需要遍历所有的元素，时间复杂度为O(n)。
     * 使用队列存储待访问的索引，空间复杂度为O(n)。 综上所述，算法的总时间复杂度为O(n)，总空间复杂度为O(n)。
     *
     * @param arr
     * @param start
     * @return
     */
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int currIndex = queue.poll();
            visited[currIndex] = true;

            if (arr[currIndex] == 0) {
                return true;
            }

            int leftIndex = currIndex - arr[currIndex];
            int rightIndex = currIndex + arr[currIndex];

            if (leftIndex >= 0 && !visited[leftIndex]) {
                queue.offer(leftIndex);
            }

            if (rightIndex < n && !visited[rightIndex]) {
                queue.offer(rightIndex);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        JumpGameIII jumpGameIII = new JumpGameIII();

        // 测试用例1
        int[] arr1 = {4, 2, 3, 0, 3, 1, 2};
        int start1 = 5;
        boolean expected1 = true;
        boolean result1 = jumpGameIII.canReach(arr1, start1);
        System.out.println("测试用例1:");
        System.out.println("预期输出: " + expected1);
        System.out.println("实际输出: " + result1);

        // 测试用例2
        int[] arr2 = {4, 2, 3, 0, 3, 1, 2};
        int start2 = 0;
        boolean expected2 = true;
        boolean result2 = jumpGameIII.canReach(arr2, start2);
        System.out.println("测试用例2:");
        System.out.println("预期输出: " + expected2);
        System.out.println("实际输出: " + result2);

        // 测试用例3
        int[] arr3 = {3, 0, 2, 1, 2};
        int start3 = 2;
        boolean expected3 = false;
        boolean result3 = jumpGameIII.canReach(arr3, start3);
        System.out.println("测试用例3:");
        System.out.println("预期输出: " + expected3);
        System.out.println("实际输出: " + result3);
    }
}
