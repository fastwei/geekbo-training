package com.geekbo.training.leetcode.daily;

/**
 * 1526. Minimum Number of Increments on Subarrays to Form a Target Array
 * Hard
 * Topics
 * Companies
 * Hint
 * You are given an integer array target.
 * You have an integer array initial of the same size as target with all elements initially zeros.
 * <p>
 * In one operation you can choose any subarray from initial and increment each value by one.
 * <p>
 * Return the minimum number of operations to form a target array from initial.
 * <p>
 * The test cases are generated so that the answer fits in a 32-bit integer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: target = [1,2,3,2,1]
 * Output: 3
 * Explanation: We need at least 3 operations to form the target array from the initial array.
 * [0,0,0,0,0] increment 1 from index 0 to 4 (inclusive).
 * [1,1,1,1,1] increment 1 from index 1 to 3 (inclusive).
 * [1,2,2,2,1] increment 1 at index 2.
 * [1,2,3,2,1] target array is formed.
 * Example 2:
 * <p>
 * Input: target = [3,1,1,2]
 * Output: 4
 * Explanation: [0,0,0,0] -> [1,1,1,1] -> [1,1,1,2] -> [2,1,1,2] -> [3,1,1,2]
 * Example 3:
 * <p>
 * Input: target = [3,1,5,4,2]
 * Output: 7
 * Explanation: [0,0,0,0,0] -> [1,1,1,1,1] -> [2,1,1,1,1] -> [3,1,1,1,1]
 * -> [3,1,2,2,2] -> [3,1,3,3,2] -> [3,1,4,4,2] -> [3,1,5,4,2].
 */
public class MinimumNumberIncrements {
    /**
     * 计算形成目标数组所需的最小操作数
     * 解题思路：
     * <p>
     * 遍历目标数组，如果当前元素大于前一个元素，则将差值加到结果中。
     * 初始时，将结果设置为第一个元素的值，因为我们至少需要将第一个元素的值增加到目标值。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n)，其中 n 是目标数组的长度。我们遍历一次目标数组。
     * 空间复杂度：O(1)，
     *
     * @param target 目标数组
     * @return 最小操作数
     */
    public static int minNumberOperations(int[] target) {
        int n = target.length;
        int result = target[0]; // 初始化结果为第一个元素的值

        // 遍历数组，如果当前元素大于前一个元素，则将差值加到结果中
        for (int i = 1; i < n; i++) {
            if (target[i] > target[i - 1]) {
                result += target[i] - target[i - 1];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] target1 = {1, 2, 3, 2, 1};
        // 需要至少3次操作才能形成目标数组：[0,0,0,0,0] -> [1,1,1,1,1] -> [1,2,2,2,1] -> [1,2,3,2,1]
        int expected1 = 3;

        int[] target2 = {3, 1, 1, 2};
        // 需要至少4次操作才能形成目标数组：[0,0,0,0] -> [1,1,1,1] -> [1,1,1,2] -> [2,1,1,2] -> [3,1,1,2]
        int expected2 = 4;

        int[] target3 = {3, 1, 5, 4, 2};
        // 需要至少7次操作才能形成目标数组：[0,0,0,0,0] -> [1,1,1,1,1] -> [2,1,1,1,1] -> [3,1,1,1,1] -> [3,1,2,2,2] -> [3,1,3,3,2] -> [3,1,4,4,2] -> [3,1,5,4,2]
        int expected3 = 7;

        // 测试用例1
        int result1 = minNumberOperations(target1);
        System.out.println(result1); // 输出：3
        System.out.println(result1 == expected1); // true

        // 测试用例2
        int result2 = minNumberOperations(target2);
        System.out.println(result2); // 输出：4
        System.out.println(result2 == expected2); // true

        // 测试用例3
        int result3 = minNumberOperations(target3);
        System.out.println(result3); // 输出：7
        System.out.println(result3 == expected3); // true
    }
}
