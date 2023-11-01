package com.geekbo.training.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1964. Find the Longest Valid Obstacle Course at Each Position
 * You want to build some obstacle courses.
 * You are given a 0-indexed integer array obstacles of length n, where obstacles[i] describes the height of the ith obstacle.
 * <p>
 * For every index i between 0 and n - 1 (inclusive), find the length of the longest obstacle course in obstacles such that:
 * <p>
 * You choose any number of obstacles between 0 and i inclusive.
 * You must include the ith obstacle in the course.
 * You must put the chosen obstacles in the same order as they appear in obstacles.
 * Every obstacle (except the first) is taller than or the same height as the obstacle immediately before it.
 * Return an array ans of length n, where ans[i] is the length of the longest obstacle course for index i as described above.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: obstacles = [1,2,3,2]
 * Output: [1,2,3,3]
 * Explanation: The longest valid obstacle course at each position is:
 * - i = 0: [1], [1] has length 1.
 * - i = 1: [1,2], [1,2] has length 2.
 * - i = 2: [1,2,3], [1,2,3] has length 3.
 * - i = 3: [1,2,3,2], [1,2,2] has length 3.
 * Example 2:
 * <p>
 * Input: obstacles = [2,2,1]
 * Output: [1,2,1]
 * Explanation: The longest valid obstacle course at each position is:
 * - i = 0: [2], [2] has length 1.
 * - i = 1: [2,2], [2,2] has length 2.
 * - i = 2: [2,2,1], [1] has length 1.
 * Example 3:
 * <p>
 * Input: obstacles = [3,1,5,6,4,2]
 * Output: [1,1,2,3,2,2]
 * Explanation: The longest valid obstacle course at each position is:
 * - i = 0: [3], [3] has length 1.
 * - i = 1: [3,1], [1] has length 1.
 * - i = 2: [3,1,5], [3,5] has length 2. [1,5] is also valid.
 * - i = 3: [3,1,5,6], [3,5,6] has length 3. [1,5,6] is also valid.
 * - i = 4: [3,1,5,6,4], [3,4] has length 2. [1,4] is also valid.
 * - i = 5: [3,1,5,6,4,2], [1,2] has length 2.
 * <p>
 */
public class LongestObstacleCourse {
    /**
     * 解题思路：
     * <p>
     * 使用动态规划来解决此问题。定义一个长度为n的数组dp，其中dp[i]表示以第i个障碍物为结尾的最长障碍物路径的长度。
     * 初始化dp数组为1，因为每个障碍物本身都构成一个有效的障碍物路径。
     * 遍历数组obstacles，对于每个障碍物obstacles[i]，找到它前面所有高度小于等于obstacles[i]的障碍物，
     * 计算它们的dp值加1，并更新dp[i]为最大的dp值。
     * 最后返回dp数组作为结果。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n^2)，其中n是障碍物的数量。在最坏情况下，每个障碍物都需要与前面的所有障碍物进行比较。
     * 空间复杂度：O(n)。
     *
     * @param obstacles
     * @return
     */
    public static int[] longestObstacleCourseAtEachPosition2(int[] obstacles) {
        int n = obstacles.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // 初始化dp数组为1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (obstacles[i] >= obstacles[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp;
    }

    /**
     * 优化解决方案使用了二分查找来减少内层循环的比较次数。
     * 我们维护一个递增的序列 sequence，其中 sequence[i] 表示长度为 i+1 的障碍物路径的最后一个障碍物。
     * 在遍历障碍物数组时，我们使用二分查找在 sequence 中找到第一个大于等于当前障碍物的位置 index，
     * 然后将当前障碍物放在 sequence[index] 的位置。
     * 这样，我们可以通过 index+1 得到以当前障碍物为结尾的最长障碍物路径的长度，同时保持 sequence 为递增序列。
     * 最后返回 dp 数组作为结果。
     * <p>
     * 算法复杂度分析：
     * 时间复杂度：O(nlogn)，其中 n 是障碍物的数量。
     * 在最坏情况下，每个障碍物都需要进行二分查找。
     * 空间复杂度：O(n)。
     *
     * @param obstacles
     * @return
     */
    public static int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int n = obstacles.length;
        int[] dp = new int[n];
        List<Integer> sequence = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int obstacle = obstacles[i];
            int index = binarySearch(sequence, obstacle);
            if (index == sequence.size()) {
                sequence.add(obstacle);
            } else {
                sequence.set(index, obstacle);
            }
            dp[i] = index + 1;
        }

        return dp;
    }

    private static int binarySearch(List<Integer> sequence, int target) {
        int left = 0, right = sequence.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (sequence.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * 以下为测试用例和预期结果
     */
    public static void main(String[] args) {
        int[] obstacles1 = {1, 2, 3, 2};
        // Expected output: [1, 2, 3, 3]
        int[] result1 = longestObstacleCourseAtEachPosition(obstacles1);
        System.out.println(Arrays.toString(result1));

        int[] obstacles2 = {2, 2, 1};
        // Expected output: [1, 2, 1]
        int[] result2 = longestObstacleCourseAtEachPosition(obstacles2);
        System.out.println(Arrays.toString(result2));

        int[] obstacles3 = {3, 1, 5, 6, 4, 2};
        // Expected output: [1, 1, 2, 3, 2, 2]
        int[] result3 = longestObstacleCourseAtEachPosition(obstacles3);
        System.out.println(Arrays.toString(result3));
    }
}