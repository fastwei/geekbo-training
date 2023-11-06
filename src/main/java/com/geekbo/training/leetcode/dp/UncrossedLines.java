package com.geekbo.training.leetcode.dp;

/**
 * 1035. Uncrossed Lines
 * Medium
 * You are given two integer arrays nums1 and nums2. We write the integers of nums1 and nums2 (in the order they are given) on two separate horizontal lines.
 * <p>
 * We may draw connecting lines: a straight line connecting two numbers nums1[i] and nums2[j] such that:
 * <p>
 * nums1[i] == nums2[j], and
 * the line we draw does not intersect any other connecting (non-horizontal) line.
 * Note that a connecting line cannot intersect even at the endpoints (i.e., each number can only belong to one connecting line).
 * <p>
 * Return the maximum number of connecting lines we can draw in this way.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums1 = [1,4,2], nums2 = [1,2,4]
 * Output: 2
 * Explanation: We can draw 2 uncrossed lines as in the diagram.
 * We cannot draw 3 uncrossed lines, because the line from nums1[1] = 4 to nums2[2] = 4
 * will intersect the line from nums1[2]=2 to nums2[1]=2.
 * Example 2:
 * <p>
 * Input: nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
 * Output: 3
 * Example 3:
 * <p>
 * Input: nums1 = [1,3,7,1,7,5], nums2 = [1,9,2,5,1]
 * Output: 2
 */
public class UncrossedLines {
    /**
     * 计算可以连接的最大线条数
     * 解题思路：
     * <p>
     * 首先，我们使用一个二维数组 dp，其中 dp[i][j] 表示以 nums1[i] 和 nums2[j] 结尾的最大连接线条数。
     * 然后，我们遍历数组 nums1 和 nums2，对于每个元素 nums1[i] 和 nums2[j]，
     * 如果它们相等，那么我们可以连接一条线条，此时 dp[i][j] 的值为 dp[i - 1][j - 1] + 1；
     * 如果它们不相等，那么我们不能连接线条，此时 dp[i][j] 的值为 Math.max(dp[i - 1][j], dp[i][j - 1])。
     * 最后，我们找到 dp 数组中的最大值，即为可以连接的最大线条数。
     * <p>
     * 算法复杂度分析
     * <p>
     * 初始化二维数组 dp 的时间复杂度为 O(m * n)，其中 m 和 n 分别是数组 nums1 和 nums2 的长度。
     * 遍历数组 nums1 和 nums2 的时间复杂度为 O(m * n)。
     * 因此，总的时间复杂度为 O(m * n)。
     *
     * @param nums1 第一个数组
     * @param nums2 第二个数组
     * @return 可以连接的最大线条数
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        // 创建一个二维数组，用于存储以 nums1[i] 和 nums2[j] 结尾的最大连接线条数
        int[][] dp = new int[m + 1][n + 1];

        // 遍历数组，计算以每个元素为结尾的最大连接线条数
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        // 创建测试用例
        int[] nums1 = {1, 4, 2};
        int[] nums2 = {1, 2, 4};

        // 创建解法对象
        UncrossedLines uncrossedLines = new UncrossedLines();

        // 测试用例1
        // 预期输出: 2
        System.out.println(uncrossedLines.maxUncrossedLines(nums1, nums2));

        // 创建测试用例
        int[] nums3 = {2, 5, 1, 2, 5};
        int[] nums4 = {10, 5, 2, 1, 5, 2};

        // 测试用例2
        // 预期输出: 3
        System.out.println(uncrossedLines.maxUncrossedLines(nums3, nums4));

        // 创建测试用例
        int[] nums5 = {1, 3, 7, 1, 7, 5};
        int[] nums6 = {1, 9, 2, 5, 1};

        // 测试用例3
        // 预期输出: 2
        System.out.println(uncrossedLines.maxUncrossedLines(nums5, nums6));
    }
}
