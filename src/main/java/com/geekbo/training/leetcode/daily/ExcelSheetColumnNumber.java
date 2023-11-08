package com.geekbo.training.leetcode.daily;

/**
 * 171. Excel Sheet Column Number
 * Easy
 * Given a string columnTitle that represents the column title as appears in an Excel sheet,
 * return its corresponding column number.
 * <p>
 * For example:
 * <p>
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: columnTitle = "A"
 * Output: 1
 * Example 2:
 * <p>
 * Input: columnTitle = "AB"
 * Output: 28
 * Example 3:
 * <p>
 * Input: columnTitle = "ZY"
 * Output: 701
 */
public class ExcelSheetColumnNumber {
    /**
     * 解题思路：
     * <p>
     * 首先，我们初始化一个columnNumber变量为0，用于存储结果。
     * 对于每个字符，我们计算其对应的数字值，即字符的ASCII码减去'A'的ASCII码加1。
     * 然后，我们将columnNumber乘以26，再加上当前字符的数字值。
     * 重复上述步骤，直到遍历完所有字符。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：对于任意输入，我们最多进行O(n)次循环，其中n是columnTitle的长度。
     * 在每次循环中，我们进行O(1)的操作，如计算字符的数字值、更新columnNumber等。因此，总的时间复杂度为O(n)。
     * 空间复杂度：我们只使用了常量级别的额外空间，因此空间复杂度为O(1)。
     *
     * @param columnTitle
     * @return
     */
    public static int titleToNumber(String columnTitle) {
        int columnNumber = 0;

        for (int i = 0; i < columnTitle.length(); i++) {
            // Get the current character
            char c = columnTitle.charAt(i);

            // Calculate the value of the current digit
            int digitValue = c - 'A' + 1;

            // Multiply the column number by 26 and add the digit value
            columnNumber = columnNumber * 26 + digitValue;
        }

        return columnNumber;
    }

    public static void main(String[] args) {
        // Test cases
        String columnTitle = "A";
        System.out.println(titleToNumber(columnTitle));  // Expected output: 1

        columnTitle = "AB";
        System.out.println(titleToNumber(columnTitle));  // Expected output: 28

        columnTitle = "ZY";
        System.out.println(titleToNumber(columnTitle));  // Expected output: 701
    }
}
