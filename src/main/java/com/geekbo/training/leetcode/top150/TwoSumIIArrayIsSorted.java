package com.geekbo.training.leetcode.top150;

import java.util.Arrays;

public class TwoSumIIArrayIsSorted {

    /**
     * 给定一个已按升序排列的整数数组 numbers，查找两个数字的索引，使它们的和等于特定目标数字。
     * 返回这两个数字的索引 [index1, index2]，其中 1 <= index1 < index2 <= numbers.length。
     * 假设测试数据中只有一组满足条件的解。
     * 算法必须仅使用常量额外空间。
     *
     * 示例：
     * 输入: numbers = [2,7,11,15], target = 9
     * 输出: [1,2]
     * 解释: 2 和 7 的和为 9。因此，index1 = 1，index2 = 2。返回 [1, 2]。
     *
     * 解题思路：
     * 1. 使用双指针方法，一个指向数组开头，一个指向数组末尾。
     * 2. 比较两个指针指向的元素之和与目标值的大小。
     * 3. 如果和等于目标值，返回两个指针的索引。
     * 4. 如果和小于目标值，将左指针向右移动一位。
     * 5. 如果和大于目标值，将右指针向左移动一位。
     *
     * 算法复杂度分析：
     * - 时间复杂度：O(n)，其中 n 是数组的长度。
     * - 空间复杂度：O(1)。
     *
     * @param numbers 已排序的整数数组
     * @param target 目标值
     * @return 包含两个索引的整数数组 [index1, index2]
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                return new int[]{left + 1, right + 1}; // 返回索引，加1是因为索引是从1开始的
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[]{-1, -1}; // 如果未找到解，返回一个无效的值
    }
    public static void main(String[] args) {
        TwoSumIIArrayIsSorted solution = new TwoSumIIArrayIsSorted();
        int[] numbers1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] result1 = solution.twoSum(numbers1, target1);
        System.out.println(Arrays.toString(result1)); // 预期输出：[1, 2]

        int[] numbers2 = {2, 3, 4};
        int target2 = 6;
        int[] result2 = solution.twoSum(numbers2, target2);
        System.out.println(Arrays.toString(result2)); // 预期输出：[1, 3]

        int[] numbers3 = {-1, 0};
        int target3 = -1;
        int[] result3 = solution.twoSum(numbers3, target3);
        System.out.println(Arrays.toString(result3)); // 预期输出：[1, 2]
    }

}
