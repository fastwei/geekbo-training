package com.geekbo.training.leetcode.top75;

/**
 * 题目描述：猜数字游戏。系统随机选择一个数字，玩家猜测这个数字。每次猜错后，系统会告诉玩家猜的数字是偏大还是偏小，直到玩家猜中为止。
 * 玩家通过调用 int guess(int num) 函数来进行猜测，函数返回三种结果：
 * -1：猜的数字偏大（num > pick）。
 * 1：猜的数字偏小（num < pick）。
 * 0：猜中了（num == pick）。
 * 返回系统随机选择的数字。
 * 
 * 解题思路：
 * 利用二分查找来逼近答案。初始化左边界为1，右边界为n，然后不断猜测中间值mid，
 * 根据返回值调整左右边界，直到猜中为止。
 * 
 * 算法复杂度分析：
 * - 时间复杂度：O(log n)，因为采用二分查找的方法。
 * - 空间复杂度：O(1)，只需要常数空间。
 *
 * todo:guest处理
 */



public class GuessNumber {

    public static int num=0;
    public int guessNumber(int n) {
        int left = 1;
        int right = n;

        while (left <= right) {
            int mid = left + (right - left) / 2; // 计算中间点以避免溢出

            int result = guess(mid); // 调用预定义的猜测函数

            if (result == 0) {
                return mid; // 如果猜中了，返回答案
            } else if (result == -1) {
                right = mid - 1; // 如果猜的数偏大，缩小右边界
            } else {
                left = mid + 1; // 如果猜的数偏小，增大左边界
            }
        }

        return -1; // 如果没有找到答案，返回-1或其他适当的值
    }
    
    // 预定义的猜测函数，需要实现
    public int guess(int num) {
        // 实现 guess 函数
        return 0;
    }

    // 测试用例
    public static void main(String[] args) {
        GuessNumber solution = new GuessNumber();
        
        // 测试用例1
        int n1 = 10;
        int pick1 = 6;
        solution.guess(6);
        int output1 = solution.guessNumber(n1);
        System.out.println("Output 1: " + output1); // 应输出6
        
        // 测试用例2
        int n2 = 1;
        int pick2 = 1;
        solution.guess(1);
        int output2 = solution.guessNumber(n2);
        System.out.println("Output 2: " + output2); // 应输出1
        
        // 测试用例3
        int n3 = 2;
        int pick3 = 1;
        solution.guess(1);
        int output3 = solution.guessNumber(n3);
        System.out.println("Output 3: " + output3); // 应输出1
    }
}
