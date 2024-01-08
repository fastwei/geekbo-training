package com.geekbo.training.leetcode.contest;
//379.2
public class ChessGame {

    /**
     * 计算捕获皇后的最小移动次数
     * @param a 皇后的横坐标
     * @param b 皇后的纵坐标
     * @param c 目标点的横坐标
     * @param d 目标点的纵坐标
     * @param e 障碍物的横坐标
     * @param f 障碍物的纵坐标
     * @return 最小移动次数
     */
    public static int  minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {
        // 如果皇后和目标点在同一行或同一列上
        if(a == e || b == f) {
            // 如果皇后和障碍物在同一行且目标点在障碍物的两侧
            if(a == e && a == c && ((d-b) * (d-f) < 0)) return 2;
            // 如果皇后和障碍物在同一列且目标点在障碍物的两侧
            if(b == f && b == d && ((c-a) * (c-e) < 0)) return 2;
            return 1;
        }
        // 如果皇后和目标点在对角线上
        if(Math.abs(c - e) == Math.abs(d - f)) {
            // 如果皇后和障碍物在同一对角线且目标点在障碍物的两侧
            if(Math.abs(c - a) == Math.abs(d - b) && ((b-f) * (b-d) < 0))  return 2;
            return 1;
        }
        return 2;
    }

    // main 方法用于测试
    public static void main(String[] args) {
        // 测试用例1
        System.out.println("Test Case 1: " + minMovesToCaptureTheQueen(1, 1, 8, 8, 2, 3)); // 预期输出：2

        // 测试用例2
        System.out.println("Test Case 2: " + minMovesToCaptureTheQueen(5, 3, 3, 4, 5, 2)); // 预期输出：1

        // 测试用例3
        System.out.println("Test Case 3: " + minMovesToCaptureTheQueen(4, 3, 3, 4, 5, 2)); // 预期输出：2
    }
}