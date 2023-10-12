package com.geekbo.training.leetcode.top75;

import java.util.ArrayList;
import java.util.List;


/**
 * 17. Letter Combinations of a Phone Number
 * <p>
 * Given a string containing digits from 2-9 inclusive,
 * return all possible letter combinations that the number could represent.
 * Return the answer in any order.
 * <p>
 * A mapping of digits to letters (just like on the telephone buttons) is given below.
 * Note that 1 does not map to any letters.
 * <p>
 * Example 1:
 * <p>
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * Example 2:
 * <p>
 * Input: digits = ""
 * Output: []
 * Example 3:
 * <p>
 * Input: digits = "2"
 * Output: ["a","b","c"]
 * <p>
 * 解题思路：
 * <p>
 * 使用回溯算法，从输入的数字字符串digits中逐个取出数字，并根据数字到字母的映射，构建所有可能的字母组合。
 * 在回溯过程中，维护一个当前组合的StringBuilder，不断添加字母，直到达到digits的长度，将当前组合加入结果列表。
 * 递归地尝试每个数字对应的字母。
 * 最后，返回结果列表。
 * 算法复杂度分析：
 * <p>
 * 时间复杂度：O(4^n)，其中n是输入数字字符串digits的长度，最坏情况下，每个数字对应4个字母。
 * 空间复杂度：O(n)，递归调用栈的深度最多为n。
 */
public class LetterCombinationsOfPhoneNumber {

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return result;
        }

        // 定义数字到字母的映射
        String[] letters = {
                "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
        };

        backtrack(result, digits, letters, 0, new StringBuilder());
        return result;
    }

    private void backtrack(List<String> result, String digits, String[] letters, int index, StringBuilder current) {
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        int digit = digits.charAt(index) - '2';
        String letter = letters[digit];

        for (int i = 0; i < letter.length(); i++) {
            current.append(letter.charAt(i));
            backtrack(result, digits, letters, index + 1, current);
            current.deleteCharAt(current.length() - 1);
        }
    }

    public static void main(String[] args) {
        LetterCombinationsOfPhoneNumber solution = new LetterCombinationsOfPhoneNumber();

        // 测试用例1
        String digits1 = "23";
        List<String> result1 = solution.letterCombinations(digits1);
        System.out.println(result1); // 预期输出: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

        // 测试用例2
        String digits2 = "";
        List<String> result2 = solution.letterCombinations(digits2);
        System.out.println(result2); // 预期输出: []

        // 测试用例3
        String digits3 = "2";
        List<String> result3 = solution.letterCombinations(digits3);
        System.out.println(result3); // 预期输出: ["a","b","c"]
    }
}
