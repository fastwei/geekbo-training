package com.geekbo.training.leetcode.crackinterview109;

/**
 * 面试题 16.07. 最大数值
 * 简单
 * 编写一个方法，找出两个数字a和b中最大的那一个。不得使用if-else或其他比较运算符。
 * <p>
 * 示例：
 * <p>
 * 输入： a = 1, b = 2
 * 输出： 2
 */

class MaximumCompare {

    /**
     * 解题思路：
     * <p>
     * 题目要求找出两个数字a和b中最大的那一个，但是不能使用if-else或其他比较运算符。
     * 可以利用数学性质来解决这个问题。我们可以先计算a-b的值，然后根据这个值的正负来确定最大值。
     * 如果a-b大于等于0，则a是最大值；如果a-b小于0，则b是最大值。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 该算法只需要进行一次减法操作，所以时间复杂度为O(1)。
     * 空间复杂度为O(1)。
     *
     * @param a
     * @param b
     * @return
     */
    public int maximum(int a, int b) {
        long diff = (long) a - (long) b;
        int sign = (int) (diff >> 63) & 1;
        int max = a - sign * (int) diff;
        return max;
    }


    public static void main(String[] args) {
        MaximumCompare solution = new MaximumCompare();

        int a = 1;
        int b = 2;
        int result = solution.maximum(a, b);
        System.out.println("最大数值：" + result);
    }
}

