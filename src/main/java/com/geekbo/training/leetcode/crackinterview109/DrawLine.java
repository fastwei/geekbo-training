package com.geekbo.training.leetcode.crackinterview109;

import java.util.Arrays;

/**
 *
 * 面试题 05.08. 绘制直线
 * 中等
 * 已知一个由像素点组成的单色屏幕，每行均有 w 个像素点，所有像素点初始为 0，左上角位置为 (0,0)。
 *
 * 现将每行的像素点按照「每 32 个像素点」为一组存放在一个 int 中，再依次存入长度为 length 的一维数组中。
 *
 * 我们将在屏幕上绘制一条从点 (x1,y) 到点 (x2,y) 的直线（即像素点修改为 1），请返回绘制过后的数组。
 *
 *
 *
 * 注意：
 *
 * 用例保证屏幕宽度 w 可被 32 整除（即一个 int 不会分布在两行上）
 *
 *
 * 示例1:
 *
 *  输入：length = 1, w = 32, x1 = 30, x2 = 31, y = 0
 *  输出：[3]
 *  解释：在第 0 行的第 30 位到第 31 位画一条直线，屏幕二进制形式表示为 [00000000000000000000000000000011]，因此返回 [3]
 * 示例2:
 *
 *  输入：length = 3, w = 96, x1 = 0, x2 = 95, y = 0
 *  输出：[-1, -1, -1]
 *  解释：由于二进制 11111111111111111111111111111111 的 int 类型代表 -1，因此返回 [-1,-1,-1]
 *
 *
 * 提示：
 *
 * 1 <= length <= 10^5
 * 1 <= w <= 3 * 10^5
 * 0 <= x1 <= x2 < w
 * 0 <= y <= 10
 */
public class DrawLine {
    public int[] drawLine(int length, int w, int x1, int x2, int y) {
        int[] ans = new int[length];
        int start = (y * w + x1) / 32; // Starting index of the range
        int end = (y * w + x2) / 32; // Ending index of the range

        // Set the range of elements to -1
        for (int i = start; i <= end; i++) {
            ans[i] = -1;
        }

        // Set the individual bits within the start and end elements
        ans[start] = ans[start] >>> x1 % 32;
        ans[end] = ans[end] & Integer.MIN_VALUE >> x2 % 32;

        return ans;
    }

    public static void main(String[] args) {
        DrawLine drawLine = new DrawLine();

        // Test case 1
        int length1 = 1;
        int w1 = 32;
        int x11 = 30;
        int x21 = 31;
        int y1 = 0;
        // Expected output: [3]
        int[] result1 = drawLine.drawLine(length1, w1, x11, x21, y1);
        System.out.println(Arrays.toString(result1));

        // Test case 2
        int length2 = 3;
        int w2 = 96;
        int x12 = 0;
        int x22 = 95;
        int y2 = 0;
        // Expected output: [-1, -1, -1]
        int[] result2 = drawLine.drawLine(length2, w2, x12, x22, y2);
        System.out.println(Arrays.toString(result2));
    }
}