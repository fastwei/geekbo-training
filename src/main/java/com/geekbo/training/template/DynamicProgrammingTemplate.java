package com.geekbo.training.template;

public class DynamicProgrammingTemplate {

    public int dynamicProgrammingFunction(int[] nums) {
        // 创建dp数组
        int[] dp = new int[nums.length];

        // 初始化dp数组
        dp[0] = nums[0];

        // 状态转移方程
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }

        // 返回最终结果
        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        DynamicProgrammingTemplate template = new DynamicProgrammingTemplate();
        int[] nums = {1, -2, 3, -4, 5};
        int result = template.dynamicProgrammingFunction(nums);
        System.out.println(result);
    }
}