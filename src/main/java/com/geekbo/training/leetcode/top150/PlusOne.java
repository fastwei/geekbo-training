package com.geekbo.training.leetcode.top150;

/**
 * 66. Plus One
 *
 * Math
 *
 * 题目描述：
 * 给定一个用整数数组表示的大整数，数组中的每个元素表示整数的每一位数字。大整数是从最高有效位到最低有效位按从左到右的顺序排列的，不包含任何前导零。
 * 将大整数加一，并返回结果的整数数组表示。
 *
 * 示例：
 * 输入: digits = [1,2,3]
 * 输出: [1,2,4]
 * 解释: 数组表示整数123。加一后得到123 + 1 = 124，结果为[1,2,4]。
 *
 * 解题思路：
 * 从数组的最低有效位（即最后一位）开始，逐位进行加一操作，同时维护一个进位carry。如果当前位加一后不产生进位，直接更新该位，然后返回结果。
 * 如果当前位加一后产生了进位，将carry设置为1，继续向前进位，直到不再产生进位或遍历完数组。
 * 如果最高位产生了进位，需要在数组最前面插入一个1，表示整数增加了一位。
 *
 * 算法复杂度分析：
 * - 时间复杂度：O(n)，其中n为数组的长度，最坏情况下需要遍历整个数组。
 * - 空间复杂度：O(1)，除了结果数组外，只需要常量级的额外空间。
 */

public class PlusOne {
    public int[] plusOne(int[] digits) {
        // 获取数组长度
        int n = digits.length;
        int carry = 1; // 进位初始值设为1，表示要进行加一操作

        // 从数组的最低有效位开始遍历
        for (int i = n - 1; i >= 0; i--) {
            int sum = digits[i] + carry; // 当前位加上进位的值
            digits[i] = sum % 10; // 更新当前位的值
            carry = sum / 10; // 计算新的进位
        }

        // 如果最高位还有进位，需要在数组前面插入一个1
        if (carry > 0) {
            int[] result = new int[n + 1];
            result[0] = 1;
            System.arraycopy(digits, 0, result, 1, n); // 将原数组复制到结果数组的后面
            return result;
        }

        return digits;
    }

    public static void main(String[] args) {
        PlusOne solution = new PlusOne();

        // 测试用例1
        int[] digits1 = {1, 2, 3};
        int[] result1 = solution.plusOne(digits1);
        System.out.print("Example 1: ");
        for (int digit : result1) {
            System.out.print(digit);
        }
        System.out.println(); // 预期输出：124

        // 测试用例2
        int[] digits2 = {4, 3, 2, 1};
        int[] result2 = solution.plusOne(digits2);
        System.out.print("Example 2: ");
        for (int digit : result2) {
            System.out.print(digit);
        }
        System.out.println(); // 预期输出：4322

        // 测试用例3
        int[] digits3 = {9};
        int[] result3 = solution.plusOne(digits3);
        System.out.print("Example 3: ");
        for (int digit : result3) {
            System.out.print(digit);
        }
        System.out.println(); // 预期输出：10
    }
}
