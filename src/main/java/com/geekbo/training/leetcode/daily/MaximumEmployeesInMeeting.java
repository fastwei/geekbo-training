package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

/**
 *
 * todo:还有些测试用例没通过
 *
 */
public class MaximumEmployeesInMeeting {
    /**
     *
     *
     * 解决方案使用动态规划来计算参加会议的最多员工数目。
     * 定义一个长度为n的数组dp，其中dp[i]表示以第i位员工为结尾的最多员工数目。
     * 初始化dp数组为1，因为每个员工本身都可以参加会议。
     * 然后遍历favorite数组，对于每个员工，如果他喜欢的员工已经参加了会议，那么他也可以参加会议，
     * 所以将dp[i]更新为dp[next] + 1，其中next是员工i喜欢的员工。最后，找到dp数组中的最大值作为结果。
     *
     * 算法复杂度分析： 时间复杂度：O(n)，其中n是员工的数量。 空间复杂度：O(n)。
     *
     * @param favorite
     * @return
     */
    public static int maxEmployees(int[] favorite) {
        int n = favorite.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // 初始化dp数组为1

        for (int i = 0; i < n; i++) {
            int next = favorite[i];
            if (favorite[next] == i) {
                dp[i] = 2; // 如果两个员工互相喜欢，则他们都可以参加会议
            } else {
                dp[i] = Math.max(dp[i], dp[next] + 1); // 如果员工i喜欢的员工已经参加了会议，那么员工i也可以参加会议
            }
        }

        int maxEmployees = 0;
        for (int count : dp) {
            maxEmployees = Math.max(maxEmployees, count);
        }

        return maxEmployees;
    }

    public static void main(String[] args) {
        int[] favorite1 = {2, 2, 1, 2};
        // Expected output: 3
        System.out.println(maxEmployees(favorite1));

        int[] favorite2 = {1, 2, 0};
        // Expected output: 3
        System.out.println(maxEmployees(favorite2));

        int[] favorite3 = {3, 0, 1, 4, 1};
        // Expected output: 4
        System.out.println(maxEmployees(favorite3));
    }
}