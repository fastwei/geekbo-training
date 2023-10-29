package com.geekbo.training.template;

public class TwoPointerTemplate {

    public boolean twoPointerFunction(int[] nums, int target) {
        // 定义左右指针的初始位置
        int left = 0;
        int right = nums.length - 1;

        // 双指针遍历数组
        while (left <= right) {
            // 根据题目要求进行操作
            if (nums[left] + nums[right] == target) {
                return true;
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                right--;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        TwoPointerTemplate template = new TwoPointerTemplate();
        int[] nums = {1, 2, 3, 4, 5};
        int target = 9;
        boolean result = template.twoPointerFunction(nums, target);
        System.out.println(result);
    }
}