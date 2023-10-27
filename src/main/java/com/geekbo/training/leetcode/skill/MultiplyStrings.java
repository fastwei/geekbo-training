package com.geekbo.training.leetcode.skill;

/**
 * Given two non-negative integers num1 and num2 represented as strings,
 * return the product of num1 and num2, also represented as a string.
 * <p>
 * Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * Example 2:
 * <p>
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 */
public class MultiplyStrings {
    /**
     * 解题思路：
     * 我们可以使用竖式乘法的方法来计算两个字符串表示的整数的乘积。
     * 1. 创建一个数组result，用于存储乘积的每一位。
     * 2. 从右往左遍历num1的每一位，再从右往左遍历num2的每一位，将它们的乘积加到result中的对应位置上。
     * 3. 遍历result数组，将每一位的数字转换为字符并拼接到结果字符串中。
     * 4. 如果结果字符串的长度为0，则返回"0"，否则返回结果字符串。
     * <p>
     * 算法复杂度分析：
     * - 时间复杂度：两个字符串的长度分别为m和n，乘法运算需要O(m*n)的时间复杂度。
     * - 空间复杂度：使用了一个大小为m+n的数组来存储乘积的每一位，所以空间复杂度为O(m+n)。
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        int m = num1.length(); // num1的长度
        int n = num2.length(); // num2的长度

        int[] result = new int[m + n]; // 存储乘积的数组

        // 逐位相乘
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); // 当前位的乘积
                int p1 = i + j; // 乘积的高位
                int p2 = i + j + 1; // 乘积的低位
                int sum = mul + result[p2]; // 当前位的和

                result[p1] += sum / 10; // 进位
                result[p2] = sum % 10; // 低位

            }
        }

        // 构造结果字符串
        StringBuilder sb = new StringBuilder();
        for (int num : result) {
            if (sb.length() != 0 || num != 0) {
                sb.append(num);
            }
        }

        // 处理结果为0的情况
        if (sb.length() == 0) {
            return "0";
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        MultiplyStrings multiplyStrings = new MultiplyStrings();

        // Test Case 1
        String num1 = "2";
        String num2 = "3";
        // 预期输出为"6"
        System.out.println(multiplyStrings.multiply(num1, num2));

        // Test Case 2
        num1 = "123";
        num2 = "456";
        // 预期输出为"56088"
        System.out.println(multiplyStrings.multiply(num1, num2));
    }
}

