package com.geekbo.training.leetcode.daily;

/**
 * 1394. Find Lucky Integer in an Array
 * Easy
 * Given an array of integers arr, a lucky integer is an integer that has a frequency in the array equal to its value.
 * <p>
 * Return the largest lucky integer in the array. If there is no lucky integer return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [2,2,3,4]
 * Output: 2
 * Explanation: The only lucky number in the array is 2 because frequency[2] == 2.
 * Example 2:
 * <p>
 * Input: arr = [1,2,2,3,3,3]
 * Output: 3
 * Explanation: 1, 2 and 3 are all lucky numbers, return the largest of them.
 * Example 3:
 * <p>
 * Input: arr = [2,2,2,3,3]
 * Output: -1
 * Explanation: There are no lucky numbers in the array.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 500
 * 1 <= arr[i] <= 500
 */
class MaximumStudentsTakingExam {

    private Integer[][] f;
    private int n;
    private int[] ss;
    /**
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(2^m * n * (2^n))，其中m是矩阵的行数，n是矩阵的列数。因为我们使用动态规划来计算每个状态的最优解，需要遍历m行、n列的座位安排情况，以及2^n个座位状态的组合。
     * 空间复杂度：O(2^m * n)，其中m是矩阵的行数，n是矩阵的列数。因为我们使用动态规划数组f来存储每个状态的最优解。
     */
    public int maxStudents(char[][] seats) {
        int m = seats.length;
        n = seats[0].length;
        ss = new int[m];
        f = new Integer[1 << n][m];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (seats[i][j] == '.') {
                    ss[i] |= 1 << j;
                }
            }
        }
        return dfs(ss[0], 0);
    }

    private int dfs(int seat, int i) {
        if (f[seat][i] != null) {
            return f[seat][i];
        }
        int ans = 0;
        for (int mask = 0; mask < 1 << n; ++mask) {
            if ((seat | mask) != seat || (mask & (mask << 1)) != 0) {
                continue;
            }
            int cnt = Integer.bitCount(mask);
            if (i == ss.length - 1) {
                ans = Math.max(ans, cnt);
            } else {
                int nxt = ss[i + 1];
                nxt &= ~(mask << 1);
                nxt &= ~(mask >> 1);
                ans = Math.max(ans, cnt + dfs(nxt, i + 1));
            }
        }
        return f[seat][i] = ans;
    }
}