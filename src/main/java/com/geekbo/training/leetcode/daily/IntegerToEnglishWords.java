package com.geekbo.training.leetcode.daily;

/**
 * Convert a non-negative integer num to its English words representation.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: num = 123
 * Output: "One Hundred Twenty Three"
 * Example 2:
 * <p>
 * Input: num = 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 * Example 3:
 * <p>
 * Input: num = 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * <p>
 * 将非负整数转换为英文单词表示。
 * <p>
 * 解题思路：
 * <p>
 * 首先，我们需要将数字按照千位进行分组，每组三个数字。例如，对于数字1234567，我们可以将其分为三组：1,234,567。
 * 然后，我们可以定义一个方法，将三位数字转换为英文单词表示。
 * 例如，对于数字234，我们可以将其转换为"Two Hundred Thirty Four"。
 * 最后，我们可以将每一组的英文单词表示连接起来，添加合适的单位（Thousand, Million, Billion）。
 * <p>
 * 算法复杂度分析：
 * <p>
 * 时间复杂度：O(log10(num))，其中num是输入数字的位数。
 * 空间复杂度：O(1)。
 */
public class IntegerToEnglishWords {
    private final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        int i = 0;
        String words = "";
        while (num > 0) {
            if (num % 1000 != 0) {
                words = helper(num % 1000) + THOUSANDS[i] + " " + words;
            }
            num /= 1000;
            i++;
        }
        return words.trim();
    }

    private String helper(int num) {
        if (num == 0) {
            return "";
        } else if (num < 20) {
            return LESS_THAN_20[num] + " ";
        } else if (num < 100) {
            return TENS[num / 10] + " " + helper(num % 10);
        } else {
            return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
        }
    }

    public static void main(String[] args) {
        IntegerToEnglishWords solution = new IntegerToEnglishWords();

        // 测试用例1
        int num1 = 123;
        String expected1 = "One Hundred Twenty Three";
        String result1 = solution.numberToWords(num1);
        System.out.println("测试用例1:");
        System.out.println("预期输出: " + expected1);
        System.out.println("实际输出: " + result1);

        // 测试用例2
        int num2 = 12345;
        String expected2 = "Twelve Thousand Three Hundred Forty Five";
        String result2 = solution.numberToWords(num2);
        System.out.println("测试用例2:");
        System.out.println("预期输出: " + expected2);
        System.out.println("实际输出: " + result2);

        // 测试用例3
        int num3 = 1234567;
        String expected3 = "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven";

        String result3 = solution.numberToWords(num3);
        System.out.println("测试用例3:");
        System.out.println("预期输出: " + expected3);
        System.out.println("实际输出: " + result3);
    }
}
