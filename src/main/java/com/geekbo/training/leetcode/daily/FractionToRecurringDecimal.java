package com.geekbo.training.leetcode.daily;

import java.util.HashMap;
import java.util.Map;

/**
 * 166. Fraction to Recurring Decimal
 * Medium
 * <p>
 * Given two integers representing the numerator and denominator of a fraction,
 * return the fraction in string format.
 * <p>
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * <p>
 * If multiple answers are possible, return any of them.
 * <p>
 * It is guaranteed that the length of the answer string is less than 104 for all the given inputs.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: numerator = 1, denominator = 2
 * Output: "0.5"
 * Example 2:
 * <p>
 * Input: numerator = 2, denominator = 1
 * Output: "2"
 * Example 3:
 * <p>
 * Input: numerator = 4, denominator = 333
 * Output: "0.(012)"
 */
public class FractionToRecurringDecimal {
    /**
     * 解题思路：
     * <p>
     * 首先，我们构建一个StringBuilder对象来存储结果。
     * 处理结果的符号，如果分子和分母符号不一致，则结果为负数，需要在结果前面添加负号。
     * 将分子和分母转换为长整型，以处理溢出的情况。
     * 处理整数部分，将整数部分添加到结果中。
     * 检查分数是否是一个整数，如果分子对分母取余等于0，说明分数是一个整数，直接返回结果。
     * 处理小数部分，将小数点添加到结果中。
     * 使用一个HashMap来存储余数和它的位置。
     * 当余数不等于0时，进行循环：
     * 如果余数已经存在于HashMap中，说明出现了循环小数，需要在循环开始的位置插入'('，在循环结束的位置插入')'，然后跳出循环。
     * 将余数和当前结果的长度存储到HashMap中。
     * 将余数乘以10，然后将商添加到结果中。
     * 更新余数为余数对分母取余。
     * 算法复杂度分析：
     * 时间复杂度：对于任意输入，我们最多进行O(k)次循环，其中k是结果字符串的长度。
     * 在每次循环中，我们需要进行O(1)的操作，如将余数乘以10、将商添加到结果中等。因此，总的时间复杂度为O(k)。
     * 空间复杂度：我们使用了一个HashMap来存储余数和它的位置，最多需要存储O(k)个元素，其中k是结果字符串的长度。
     * 因此，总的空间复杂度为O(k)。
     * 请注意：
     * <p>
     * 为了简化代码，我们假设输入的分子和分母都在int类型的范围内，并且结果的长度不超过104。
     * 在实际应用中，可能需要进行更多的边界检查和处理。
     *
     * @param numerator
     * @param denominator
     * @return
     */
    public static String fractionToDecimal(int numerator, int denominator) {
        // Handle the case of zero numerator
        if (numerator == 0) {
            return "0";
        }

        StringBuilder decimal = new StringBuilder();

        // Handle the sign of the result
        if (numerator < 0 ^ denominator < 0) {
            decimal.append("-");
        }

        // Convert the numerator and denominator to long to handle overflow
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        // Append the integer part
        decimal.append(num / den);

        // Check if the fraction is a whole number
        if (num % den == 0) {
            return decimal.toString();
        }

        decimal.append(".");

        Map<Long, Integer> remainderMap = new HashMap<>();
        long remainder = num % den;

        // Keep track of the remainder and its position
        while (remainder != 0) {
            // If the remainder repeats, insert '(' at the start position
            if (remainderMap.containsKey(remainder)) {
                decimal.insert(remainderMap.get(remainder), "(");
                decimal.append(")");
                break;
            }

            // Store the remainder and its position
            remainderMap.put(remainder, decimal.length());

            // Calculate the next remainder by multiplying it by 10
            remainder *= 10;

            // Append the next digit to the result
            decimal.append(remainder / den);

            // Update the remainder
            remainder %= den;
        }

        return decimal.toString();
    }

    public static void main(String[] args) {
        // Test cases
        int numerator = 1;
        int denominator = 2;
        System.out.println(fractionToDecimal(numerator, denominator));  // Expected output: "0.5"

        numerator = 2;
        denominator = 1;
        System.out.println(fractionToDecimal(numerator, denominator));  // Expected output: "2"

        numerator = 4;
        denominator = 333;
        System.out.println(fractionToDecimal(numerator, denominator));  // Expected output: "0.(012)"
    }
}

