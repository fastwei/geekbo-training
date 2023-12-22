package com.geekbo.training.leetcode.crackinterview109;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题 08.07. 无重复字符串的排列组合
 * 中等
 * 无重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。
 * <p>
 * 示例1:
 * <p>
 * 输入：S = "qwe"
 * 输出：["qwe", "qew", "wqe", "weq", "ewq", "eqw"]
 * 示例2:
 * <p>
 * 输入：S = "ab"
 * 输出：["ab", "ba"]
 * 提示:
 * <p>
 * 字符都是英文字母。
 * 字符串长度在[1, 9]之间。
 */
public class Permutations {
    /**
     * 解题思路：
     * <p>
     * 使用回溯算法来生成所有可能的排列组合。
     * 遍历字符串的每个字符，将其添加到结果中，并标记为已使用。
     * 递归调用回溯函数，继续生成下一个字符的排列组合。
     * 当生成的排列长度等于字符串的长度时，将其添加到结果中。
     * 恢复状态，继续处理下一个字符。
     * 最终返回所有生成的排列组合。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(N*N!)，其中 N 是字符串的长度。每个字符都有 N 种选择，并且排列组合的数量为 N!。
     * 空间复杂度：O(N)，其中 N 是字符串的长度。需要额外的空间来存储结果和标记字符是否已使用。
     * 请注意，在上述示例中，类名和方法名已经按照要求进行了中文注释，并在main方法中编写了相关的测试用例和预期输出。
     *
     * @param s
     * @return
     */
    public String[] permutation(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s.toCharArray(), new boolean[s.length()], new StringBuilder(), result);
        return result.toArray(new String[0]);
    }

    private void backtrack(char[] chars, boolean[] used, StringBuilder sb, List<String> result) {
        if (sb.length() == chars.length) {
            result.add(sb.toString());
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            sb.append(chars[i]);
            backtrack(chars, used, sb, result);
            sb.deleteCharAt(sb.length() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        Permutations permutations = new Permutations();
        String s1 = "qwe";
        System.out.println("Input: " + s1);
        System.out.println("Output: " + String.join(", ", permutations.permutation(s1)));

        String s2 = "ab";
        System.out.println("Input: " + s2);
        System.out.println("Output: " + String.join(", ", permutations.permutation(s2)));
    }

}



