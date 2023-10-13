package com.geekbo.training.leetcode.top75;

/**
 * Minimum Flips to Make a OR b Equal to c
 * Given 3 positives numbers a, b and c.
 * Return the minimum flips required in some bits of a and b to make ( a OR b == c ). (bitwise OR operation).
 * Flip operation consists of change any single bit 1 to 0 or change the bit 0 to 1 in their binary representation.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: a = 2, b = 6, c = 5
 * Output: 3
 * Explanation: After flips a = 1 , b = 4 , c = 5 such that (a OR b == c)
 * Example 2:
 * <p>
 * Input: a = 4, b = 2, c = 7
 * Output: 1
 * Example 3:
 * <p>
 * Input: a = 1, b = 2, c = 3
 * Output: 0
 */
public class MinimumFlips {
    /**
     * 实现思路：
     * <p>
     * 遍历32位二进制数，从低位到高位逐位处理。
     * 对于每一位，分别获取a、b和c的对应位的值，记为bitA、bitB和bitC。
     * 如果bitC等于0，表示第i位应该为0，根据题目要求进行判断和翻转操作。
     * 如果bitA和bitB都为1，需要进行两次翻转。
     * 如果bitA或bitB有一个为1，需要进行一次翻转。
     * 如果bitC等于1，表示第i位应该为1，根据题目要求进行判断和翻转操作。
     * 如果bitA和bitB都为0，需要进行一次翻转。
     * 返回翻转次数的累加和作为结果。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历32位二进制数，时间复杂度为O(32) = O(1)。无论输入的整数大小如何，都只需要进行32次循环，因此时间复杂度是常数级别的。
     * 空间复杂度：除了输入的三个整数外，不需要使用额外的空间，所以空间复杂度是O(1)，是常数级别的。
     *
     * todo:需理解这些bit的各种操作和技巧
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int minFlips(int a, int b, int c) {
        int flips = 0;
        // 遍历32位二进制数
        for (int i = 0; i < 32; i++) {
            // 获取a，b和c的第i位
            int bitA = (a >> i) & 1;
            int bitB = (b >> i) & 1;
            int bitC = (c >> i) & 1;

            // 如果bitC等于0，表示第i位应该为0
            if (bitC == 0) {
                // 如果bitA和bitB都为1，需要进行两次翻转
                if (bitA == 1 && bitB == 1) {
                    flips += 2;
                }
                // 如果bitA或bitB有一个为1，需要进行一次翻转
                else if (bitA == 1 || bitB == 1) {
                    flips++;
                }
            }
            // 如果bitC等于1，表示第i位应该为1
            else {
                // 如果bitA和bitB都为0，需要进行一次翻转
                if (bitA == 0 && bitB == 0) {
                    flips++;
                }
            }
        }

        return flips;
    }

    public static void main(String[] args) {
        MinimumFlips minimumFlips = new MinimumFlips();

        // 测试用例1
        int a1 = 2, b1 = 6, c1 = 5;
        int result1 = minimumFlips.minFlips(a1, b1, c1);
        System.out.println(result1); // 输出: 3

        // 测试用例2
        int a2 = 4, b2 = 2, c2 = 7;
        int result2 = minimumFlips.minFlips(a2, b2, c2);
        System.out.println(result2); // 输出: 1

        // 测试用例3
        int a3 = 1, b3 = 2, c3 = 3;
        int result3 = minimumFlips.minFlips(a3, b3, c3);
        System.out.println(result3); // 输出: 0
    }
}