package com.geekbo.training.leetcode.daily;

import java.util.*;

/**
 * 面试题 08.08. 有重复字符串的排列组合
 * 中等
 * 有重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合。
 * <p>
 * 示例1:
 * <p>
 * 输入：S = "qqe"
 * 输出：["eqq","qeq","qqe"]
 * 示例2:
 * <p>
 * 输入：S = "ab"
 * 输出：["ab", "ba"]
 * 提示:
 * <p>
 * 字符都是英文字母。
 * 字符串长度在[1, 9]之间。
 */
public class StringPermutations {
    /**
     * 计算某字符串的所有排列组合
     * 解题思路：
     * <p>
     * 使用回溯算法来生成所有排列组合。
     * 将字符串转换为字符数组，方便进行操作。
     * 创建一个辅助函数，用于生成所有排列组合。这个函数将接收一个当前排列的字符数组、一个标记数组用于记录字符是否已经被使用、一个结果集合用于保存所有排列组合的结果。
     * 在辅助函数中，首先判断当前排列的字符数组是否已经包含了所有字符，如果是，则将当前排列转化为字符串并添加到结果集合中。
     * 否则，遍历字符数组，对于每个字符，如果该字符未被使用且之前没有出现过，则将其添加到当前排列的字符数组中，并将标记数组对应位置的值设置为已使用。
     * 然后递归调用辅助函数，继续生成字符数组的排列组合
     * <p>
     * 算法的时间复杂度分析：
     * <p>
     * 在回溯算法中，我们需要遍历所有的排列组合。对于每个字符位置，我们有n种选择，其中n是字符串的长度。因此，生成所有排列组合的时间复杂度为O(n!)。
     * 在每个递归调用中，我们需要将当前字符添加到当前排列中，并检查是否已经使用过。这需要花费O(1)的时间。因此，总体的时间复杂度为O(n * n!)。
     * 算法的空间复杂度分析：
     * <p>
     * 在每个递归调用中，我们使用了一个额外的标记数组来记录字符是否已经被使用，一个集合来记录之前出现过的字符，以及一个当前排列的字符列表。这些额外的空间都随着递归的深度增加，最多达到字符串长度n的大小。因此，空间复杂度为O(n)。
     * 总结：
     * <p>
     * 时间复杂度为O(n * n!)，空间复杂度为O(n)。这个算法可以在给定的时间限制内处理字符串长度为1到9的情况。
     *
     * @param S 给定的字符串
     * @return 所有排列组合的结果数组
     */
    public String[] permutation(String S) {
        char[] chars = S.toCharArray();
        List<String> result = new ArrayList<>();
        boolean[] used = new boolean[chars.length];
        backtrack(chars, used, new ArrayList<Character>(), result);
        return result.toArray(new String[0]);
    }

    /**
     * 回溯算法生成所有排列组合
     *
     * @param chars   字符数组
     * @param used    标记数组，记录字符是否已经被使用
     * @param current 当前排列的字符列表
     * @param result  结果集合，保存所有排列组合的结果
     */
    private void backtrack(char[] chars, boolean[] used, List<Character> current, List<String> result) {
        if (current.size() == chars.length) {
            StringBuilder sb = new StringBuilder();
            for (char c : current) {
                sb.append(c);
            }
            result.add(sb.toString());
            return;
        }

        Set<Character> seen = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            if (used[i] || seen.contains(chars[i])) {
                continue;
            }
            seen.add(chars[i]);
            current.add(chars[i]);
            used[i] = true;
            backtrack(chars, used, current, result);
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        StringPermutations stringPermutations = new StringPermutations();
        // 测试用例1
        String S1 = "qqe";
        String[] result1 = stringPermutations.permutation(S1);
        System.out.println("Test Case 1:");
        System.out.println("Input: " + S1);
        System.out.println("Expected Output: [eqq, qeq, qqe]");
        System.out.println("Output: " + Arrays.toString(result1));
        System.out.println();

        // 测试用例2
        String S2 = "ab";
        String[] result2 = stringPermutations.permutation(S2);
        System.out.println("Test Case 2:");
        System.out.println("Input: " + S2);
        System.out.println("Expected Output: [ab, ba]");
        System.out.println("Output: " + Arrays.toString(result2));
    }
}



