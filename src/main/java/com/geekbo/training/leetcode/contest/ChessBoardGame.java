package com.geekbo.training.leetcode.contest;
//379.2
public class ChessBoardGame {


    // 方法：计算捕获黑后所需的最少移动次数
    public static int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {
        // 检查车是否可以直接捕获后
        if (a == e || b == f) {
            return 1;
        }

        // 检查象是否可以直接捕获后
        if (Math.abs(c - e) == Math.abs(d - f)) {
            return 1;
        }

        // 检查车是否可以在两步内捕获后（移动到同一行或列）
        if (a != e && b != f) {
            return 2;
        }

        // 在其他情况下，车和象都无法在两步内捕获后
        return 3;
    }

    public static void main(String[] args) {
        // 测试用例1
        System.out.println("Test Case 1: " + minMovesToCaptureTheQueen(1, 1, 8, 8, 2, 3)); // 预期输出：2
        // 测试用例2
        System.out.println("Test Case 2: " + minMovesToCaptureTheQueen(5, 3, 3, 4, 5, 2)); // 预期输出：1
        // 测试用例3
        System.out.println("Test Case 3: " + minMovesToCaptureTheQueen(4, 3, 3, 4, 5, 2)); // 预期输出：2
    }
}
