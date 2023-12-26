package com.geekbo.training.leetcode.premium;

/**
 * 1427. 字符串的左右移
 * 简单
 * 给定一个包含小写英文字母的字符串 s 以及一个矩阵 shift，其中 shift[i] = [direction, amount]：
 * direction 可以为 0 （表示左移）或 1 （表示右移）。
 * amount 表示 s 左右移的位数。
 * 左移 1 位表示移除 s 的第一个字符，并将该字符插入到 s 的结尾。
 * 类似地，右移 1 位表示移除 s 的最后一个字符，并将该字符插入到 s 的开头。
 * 对这个字符串进行所有操作后，返回最终结果。
 * <p>
 * 示例 1：
 * 输入：s = "abc", shift = [[0,1],[1,2]]
 * 输出："cab"
 * 解释：
 * [0,1] 表示左移 1 位。 "abc" -> "bca"
 * [1,2] 表示右移 2 位。 "bca" -> "cab"
 * <p>
 * 示例 2：
 * 输入：s = "abcdefg", shift = [[1,1],[1,1],[0,2],[1,3]]
 * 输出："efgabcd"
 * 解释：
 * [1,1] 表示右移 1 位。 "abcdefg" -> "gabcdef"
 * [1,1] 表示右移 1 位。 "gabcdef" -> "fgabcde"
 * [0,2] 表示左移 2 位。 "fgabcde" -> "abcdefg"
 * [1,3] 表示右移 3 位。 "abcdefg" -> "efgabcd"
 * <p>
 * 提示：
 * 1 <= s.length <= 100
 * s 只包含小写英文字母
 * 1 <= shift.length <= 100
 * shift[i].length == 2
 * 0 <= shift[i][0] <= 1
 * 0 <= shift[i][1] <= 100
 */
public class StringShift {
    /**
     * 字符串移位
     * 解题思路：
     * 1. 首先，我们需要计算总的左移和右移次数。
     * 2. 然后，我们需要确定实际需要进行移位的次数，即左移和右移次数的差值的绝对值除以字符串的长度。
     * 3. 最后，根据左移和右移的次数进行移位操作，返回移位后的字符串。
     * <p>
     * 算法复杂度分析：
     * - 时间复杂度：O(n)，其中 n 为字符串的长度。
     * 需要遍历移位矩阵来计算总的左移和右移次数，同时根据左移和右移次数进行移位操作。
     * - 空间复杂度：O(1)。只需要常数个额外的空间来存储左移和右移次数以及移位后的字符串。
     *
     * @param s     给定的字符串
     * @param shift 移位矩阵
     * @return 移位后的字符串
     */
    public String stringShift(String s, int[][] shift) {
        int leftShift = 0;
        int rightShift = 0;

        // 计算总的左移和右移次数
        for (int[] sh : shift) {
            if (sh[0] == 0) {
                leftShift += sh[1];
            } else {
                rightShift += sh[1];
            }
        }

        // 需要进行移位的次数
        int shiftCount = Math.abs(leftShift - rightShift) % s.length();

        // 根据左移和右移的次数进行移位操作
        if (leftShift > rightShift) {
            return s.substring(shiftCount) + s.substring(0, shiftCount);
        } else if (leftShift < rightShift) {
            return s.substring(s.length() - shiftCount) + s.substring(0, s.length() - shiftCount);
        } else {
            return s;
        }
    }

    public static void main(String[] args) {
        String s = "abcdefg";
        int[][] shift = {{1,

                1}, {1, 1}, {0, 2}, {1, 3}};
        StringShift stringShift = new StringShift();
        String result = stringShift.stringShift(s, shift);
        System.out.println(result);
    }
}


