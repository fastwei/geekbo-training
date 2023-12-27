package com.geekbo.training.leetcode.premium100;

/**
 * 1055. 形成字符串的最短路径
 * 中等
 * 对于任何字符串，我们可以通过删除其中一些字符（也可能不删除）来构造该字符串的 子序列 。(例如，“ace” 是 “abcde” 的子序列，而 “aec” 不是)。
 * <p>
 * 给定源字符串 source 和目标字符串 target，返回 源字符串 source 中能通过串联形成目标字符串 target 的 子序列 的最小数量 。如果无法通过串联源字符串中的子序列来构造目标字符串，则返回 -1。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：source = "abc", target = "abcbc"
 * 输出：2
 * 解释：目标字符串 "abcbc" 可以由 "abc" 和 "bc" 形成，它们都是源字符串 "abc" 的子序列。
 * 示例 2：
 * <p>
 * 输入：source = "abc", target = "acdbc"
 * 输出：-1
 * 解释：由于目标字符串中包含字符 "d"，所以无法由源字符串的子序列构建目标字符串。
 * 示例 3：
 * <p>
 * 输入：source = "xyz", target = "xzyxz"
 * 输出：3
 * 解释：目标字符串可以按如下方式构建： "xz" + "y" + "xz"。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= source.length, target.length <= 1000
 * source 和 target 仅包含英文小写字母。
 */
public class ConstructTargetString {
    public static int shortestWay(String source, String target) {

        // 标记源的所有字符的布尔数组
        boolean[] sourceChars = new boolean[26];
        for (char c : source.toCharArray()) {
            sourceChars[c - 'a'] = true;
        }

        // 检查源文件中是否存在目标文件的所有字符
        // 如果没有任何字符，返回 -1
        for (char c : target.toCharArray()) {
            if (!sourceChars[c - 'a']) {
                return -1;
            }
        }

        // 串联源，直到目标是串联字符串的子序列
        String concatenatedSource = source;
        int count = 1;
        while (!isSubsequence(target, concatenatedSource)) {
            concatenatedSource += source;
            count++;
        }

        // 完成的串联数
        return count;
    }

    // 检查 toCheck 是否是 inString 的子序列
    public static boolean isSubsequence(String toCheck, String inString) {
        int i = 0, j = 0;
        while (i < toCheck.length() && j < inString.length()) {
            if (toCheck.charAt(i) == inString.charAt(j)) {
                i++;
            }
            j++;
        }

        return i == toCheck.length();
    }

    public static void main(String[] args) {
        // 测试用例
        String source1 = "abc";
        String target1 = "abcbc";
        // 预期输出：2
        System.out.println(shortestWay(source1, target1));

        String source2 = "abc";
        String target2 = "acdbc";
        // 预期输出：-1
        System.out.println(shortestWay(source2, target2));

        String source3 = "xyz";
        String target3 = "xzyxz";
        // 预期输出：3
        System.out.println(shortestWay(source3, target3));
    }
}