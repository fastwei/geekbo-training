package com.geekbo.training.leetcode.crackinterview109;

/**
 * 面试题 05.02. 二进制数转字符串
 * 中等
 * 二进制数转字符串。给定一个介于0和1之间的实数（如0.72），类型为double，打印它的二进制表达式。如果该数字无法精确地用32位以内的二进制表示，则打印“ERROR”。
 *
 * 示例1:
 *
 *  输入：0.625
 *  输出："0.101"
 * 示例2:
 *
 *  输入：0.1
 *  输出："ERROR"
 *  提示：0.1无法被二进制准确表示
 *
 *
 * 提示：
 *
 * 32位包括输出中的 "0." 这两位。
 * 题目保证输入用例的小数位数最多只有 6 位
 *
 */
public class BinaryToString {
    /**
     * 将给定实数转换为二进制字符串表示
     *解题思路：
     *
     * 首先判断给定的实数是否在0和1之间，如果不是则返回"ERROR"。
     * 创建一个StringBuilder对象，用于构建二进制字符串表示。
     * 循环将实数乘以2，如果结果大于等于1，则将"1"添加到StringBuilder中，并将实数减去1，否则将"0"添加到StringBuilder中。
     * 当实数为0时，表示转换完毕，返回StringBuilder中的字符串表示。
     * 如果StringBuilder的长度超过32位，则返回"ERROR"。
     *
     * 算法复杂度分析：
     *
     * 时间复杂度：O(log(num))，其中num是给定的实数。每次循环将实数乘以2，因此循环的次数取决于num的大小。
     * 空间复杂度：O(1)，只需要使用常数级别的额外空间。
     * @param num 给定的实数
     * @return 实数的二进制字符串表示，如果无法精确表示则返回"ERROR"
     */
    public static String printBin(double num) {
        if (num >= 1 || num <= 0) {
            return "ERROR";
        }

        StringBuilder sb = new StringBuilder("0.");
        while (num > 0) {
            if (sb.length() >= 32) {
                return "ERROR";
            }
            double r = num * 2;
            if (r >= 1) {
                sb.append("1");
                num = r - 1;
            } else {
                sb.append("0");
                num = r;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        // 测试用例1
        double num1 = 0.625;
        String expected1 = "0.101";
        String result1 = printBin(num1);
        System.out.println(result1.equals(expected1)); // true

        // 测试用例2
        double num2 = 0.1;
        String expected2 = "ERROR";
        String result2 = printBin(num2);
        System.out.println(result2.equals(expected2)); // true
    }
}
