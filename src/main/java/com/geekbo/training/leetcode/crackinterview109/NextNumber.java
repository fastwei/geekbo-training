package com.geekbo.training.leetcode.crackinterview109;

/**
 * 面试题 05.04. 下一个数
 * 中等
 * 相关标签
 * 相关企业
 * <p>
 * 下一个数。给定一个正整数，找出与其二进制表达式中1的个数相同且大小最接近的那两个数（一个略大，一个略小）。
 * <p>
 * 示例1:
 * 输入：num = 2（或者0b10）
 * 输出：[4, 1] 或者（[0b100, 0b1]）
 * <p>
 * 示例2:
 * 输入：num = 1
 * 输出：[2, -1]
 * <p>
 * 提示:
 * num的范围在[1, 2147483647]之间；
 * 如果找不到前一个或者后一个满足条件的正数，那么输出 -1。
 */
public class NextNumber {

    public int[] findClosedNumbers(int num) {
        int[] res = new int[2];
        if (num <= 0 || num >= Integer.MAX_VALUE) {
            res[0] = -1;
            res[1] = -1;
        } else {
            res[0] = getNext(num);
            res[1] = getPrev(num);
        }
        return res;
    }

    // 获取比给定数字稍大的下一个数
    private int getNext(int n) {
        // 计算 c0 和 c1，用于找到最右边非拖尾0的下标 p
        int c = n;
        int c0 = 0;
        int c1 = 0;
        while (((c & 1) == 0) && (c != 0)) {
            c0++;
            c >>= 1;
        }
        while ((c & 1) == 1) {
            c1++;
            c >>= 1;
        }

        // 错误：若 n = 111111...000，那么就没有更大的数字
        // 如果 n 的二进制不存在可翻转的0，或者 n 就是0
        if (c0 + c1 == 31 || c0 + c1 == 0) {
            return -1;
        }

        int p = c0 + c1; // 前提：最右边，非拖尾0的位置
        n |= (1 << p); // 步骤1：翻转最右边，非拖尾0
        n &= ~((1 << p) - 1); // 步骤2：将 p 右方的所有位清零
        n |= (1 << (c1 - 1)) - 1; // 步骤3：在右方插入 (c1-1) 个1

        return n;
    }


    // 获取比给定数字稍小的上一个数
    private int getPrev(int n) {
        // 计算 c0 和 c1，用于找到最右边非拖尾1的下标 p
        int c = n;
        int c0 = 0;
        int c1 = 0;
        while ((c & 1) == 1) {
            c1++;
            c >>= 1;
        }
        if (c == 0) {
            return -1;
        }
        while (((c & 1) == 0) && (c != 0)) {
            c0++;
            c >>= 1;
        }

        int p = c0 + c1; // 前提：最右边，非拖尾1的位置
        n &= ((~0) << (p + 1)); // 步骤1：将从最右边非拖尾1开始的所有位清零
        int mask = (1 << (c1 + 1)) - 1; // 步骤2：创建一个长度为 (c1+1) 的掩码
        n |= mask << (c0 - 1); // 步骤3：在 p 的左边插入 (c1+1) 个1

        return n;
    }
}
