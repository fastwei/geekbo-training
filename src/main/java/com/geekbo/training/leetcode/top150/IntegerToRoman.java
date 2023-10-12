package com.geekbo.training.leetcode.top150;

/**
 * 通过比较整数和罗马数字字符的值，逐步构建罗马数字表示。
 * <p>
 * 算法的时间复杂度为O(13)（常数时间），因为我们只有13个不同的罗马数字字符。
 */
public class IntegerToRoman {
    public String intToRoman(int num) {
        // 定义罗马数字字符和对应的值
        String[] romanChars = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder roman = new StringBuilder();
        int index = 0;

        while (num > 0) {
            // 检查当前值是否大于或等于当前罗马数字字符对应的值
            if (num >= values[index]) {
                // 将当前罗马数字字符添加到结果中
                roman.append(romanChars[index]);
                // 减去对应的值
                num -= values[index];
            } else {
                // 如果当前值小于当前罗马数字字符对应的值，继续检查下一个字符
                index++;
            }
        }

        return roman.toString();
    }

    public static void main(String[] args) {
        IntegerToRoman integerToRoman = new IntegerToRoman();

        int num1 = 3;
        String result1 = integerToRoman.intToRoman(num1);
        System.out.println("Example 1: " + result1); // 输出 "III"

        int num2 = 58;
        String result2 = integerToRoman.intToRoman(num2);
        System.out.println("Example 2: " + result2); // 输出 "LVIII"

        int num3 = 1994;
        String result3 = integerToRoman.intToRoman(num3);
        System.out.println("Example 3: " + result3); // 输出 "MCMXCIV"
    }
}


