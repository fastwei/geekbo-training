package com.geekbo.training.leetcode.daily;

/**
 * 168. Excel Sheet Column Title
 * Easy
 * Topics
 * Companies
 * Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.
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
 * Input: columnNumber = 1
 * Output: "A"
 * Example 2:
 * <p>
 * Input: columnNumber = 28
 * Output: "AB"
 * Example 3:
 * <p>
 * Input: columnNumber = 701
 * Output: "ZY"
 */
public class ExcelSheetColumnTitle {
    /**
     * 解题思路：
     * <p>
     * 首先，我们使用一个StringBuilder对象来存储结果。
     * 在每一步中，我们将columnNumber减1，以将其转换为0-based索引。
     * 然后，我们计算columnNumber除以26的余数，这个余数对应于结果中的最后一个字母。
     * 我们将这个字母插入到结果的开头。
     * 最后，我们将columnNumber更新为其除以26的商，以进行下一步的操作。
     * 重复上述步骤，直到columnNumber为0。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：对于任意输入，我们最多进行O(log(columnNumber))次循环，其中columnNumber是输入的列号。
     * 在每次循环中，我们进行O(1)的操作，如计算余数、将字母插入结果等。
     * 因此，总的时间复杂度为O(log(columnNumber))。
     * 空间复杂度：我们使用了一个StringBuilder对象来存储结果，最多需要存储O(log(columnNumber))个字符。
     * 因此，总的空间复杂度为O(log(columnNumber))。
     *
     * @param columnNumber
     * @return
     */
    public static String convertToTitle(int columnNumber) {
        StringBuilder title = new StringBuilder();

        while (columnNumber > 0) {
            // Convert the column number to a 0-based index
            columnNumber--;

            // Calculate the remainder (the last letter of the title)
            int remainder = columnNumber % 26;

            // Append the letter to the title
            title.insert(0, (char) ('A' + remainder));

            // Update the column number to the quotient
            columnNumber /= 26;
        }

        return title.toString();
    }

    public static void main(String[] args) {
        // Test cases
        int columnNumber = 1;
        System.out.println(convertToTitle(columnNumber));  // Expected output: "A"

        columnNumber = 28;
        System.out.println(convertToTitle(columnNumber));  // Expected output: "AB"

        columnNumber = 701;
        System.out.println(convertToTitle(columnNumber));  // Expected output: "ZY"
    }
}
