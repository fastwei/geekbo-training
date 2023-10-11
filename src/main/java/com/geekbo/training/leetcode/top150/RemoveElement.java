package com.geekbo.training.leetcode.top150;

/**
 * Array / String
 *
 *给定一个整数数组 nums 和一个整数 val，请原地删除数组中所有值等于 val 的元素。元素的删除后不需要保持原有顺序。然后返回数组中剩余元素的个数。
 *
 * 假设数组中剩余不等于 val 的元素个数为 k，为了通过测试，你需要完成以下步骤：
 *
 * 修改数组 nums，使得 nums 的前 k 个元素包含所有不等于 val 的元素。nums 中剩余的元素及其大小都不重要。
 * 返回 k。
 * 自定义判定器：
 *
 * 判定器将使用以下代码测试你的解决方案：
 *
 *解决思路：
 *
 * 初始化一个变量 k 为 0，用于表示不等于 val 的元素个数。
 *
 * 遍历数组 nums，对于每个元素：
 *
 * 如果当前元素不等于 val，将其赋值给 nums[k]，并将 k 自增。
 * 返回 k，即不等于 val 的元素个数。
 *
 * 时间复杂度： 该算法的时间复杂度为 O(n)，其中 n 是数组 nums 的长度。
 * 因为我们只遍历一次数组，将不等于 val 的元素移到数组的前面，所以时间复杂度是线性的。
 *
 * Array / String
 */
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int k = 0; // 初始化 k 为 0，表示不等于 val 的元素个数

        // 遍历数组 nums
        for (int i = 0; i < nums.length; i++) {
            // 如果当前元素不等于 val，将其赋值给 nums[k]
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++; // k 自增，表示不等于 val 的元素个数增加了
            }
        }

        return k; // 返回不等于 val 的元素个数 k
    }

    public static void main(String[] args) {
        RemoveElement solution = new RemoveElement();
        int[] nums = {3, 2, 2, 3};
        int val = 3;
        int result = solution.removeElement(nums, val);

        System.out.println("数组中不等于 " + val + " 的元素个数为：" + result);
        System.out.print("修改后的数组为：[");
        for (int i = 0; i < result; i++) {
            System.out.print(nums[i]);
            if (i < result - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
