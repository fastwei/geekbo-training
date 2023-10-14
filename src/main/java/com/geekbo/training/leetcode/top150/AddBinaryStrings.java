package com.geekbo.training.leetcode.top150;

/**
 *
 * Given two binary strings a and b, return their sum as a binary string.
 *
 *
 *
 * Example 1:
 *
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 *
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 *
 */
public class AddBinaryStrings {

    /**
     * 解题思路：
     * <p>
     * 使用StringBuilder来存储结果，从右往左逐位进行相加操作。
     * 使用两个指针i和j分别指向字符串a和b的最后一位字符。
     * 使用一个变量carry来记录进位。初始时carry为0。
     * 在每一位上，将carry与a的当前位和b的当前位相加，得到sum。
     * sum对2取余，得到当前位的值，将其添加到StringBuilder中。
     * sum除以2，得到进位的值，更新carry。
     * 继续向前移动指针，直到两个字符串都遍历完。
     * 最后，如果carry不为0，则将其添加到StringBuilder中。
     * 将StringBuilder进行翻转，得到最终的结果。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：遍历两个字符串需要O(max(a.length, b.length))的时间。
     * 空间复杂度：使用了StringBuilder来存储结果，空间复杂度为O(max(a.length, b.length))。
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;

        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0) {
                sum += a.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }
            sb.append(sum % 2);
            carry = sum / 2;
        }

        if (carry != 0) {
            sb.append(carry);
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        AddBinaryStrings solution = new AddBinaryStrings();

        // Test Case 1
        String a1 = "11";
        String b1 = "1";
        String result1 = solution.addBinary(a1, b1);
        String expected1 = "100";
        System.out.println(result1.equals(expected1)); // true

        // Test Case 2
        String a2 = "1010";
        String b2 = "1011";
        String result2 = solution.addBinary(a2, b2);
        String expected2 = "10101";
        System.out.println(result2.equals(expected2)); // true
    }
}