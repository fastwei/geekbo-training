package com.geekbo.training.leetcode.premium;

/**
 * 161. 相隔为 1 的编辑距离
 * 中等
 * 给定两个字符串 s 和 t ，如果它们的编辑距离为 1 ，则返回 true ，否则返回 false 。
 * <p>
 * 字符串 s 和字符串 t 之间满足编辑距离等于 1 有三种可能的情形：
 * <p>
 * 往 s 中插入 恰好一个 字符得到 t
 * 从 s 中删除 恰好一个 字符得到 t
 * 在 s 中用 一个不同的字符 替换 恰好一个 字符得到 t
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: s = "ab", t = "acb"
 * 输出: true
 * 解释: 可以将 'c' 插入字符串 s 来得到 t。
 * 示例 2:
 * <p>
 * 输入: s = "cab", t = "ad"
 * 输出: false
 * 解释: 无法通过 1 步操作使 s 变为 t。
 * <p>
 * <p>
 * 提示:
 * <p>
 * 0 <= s.length, t.length <= 104
 * s 和 t 由小写字母，大写字母和数字组成
 */
public class OneEditDistance {
    public boolean isOneEditDistance(String s, String t) {
        int ns = s.length();
        int nt = t.length();

        // 确保 s 比 t 短。
        if (ns > nt)
            return isOneEditDistance(t, s);

        // 如果长度差异大于1，则字符串不是一个编辑距离。

        if (nt - ns > 1)
            return false;

        for (int i = 0; i < ns; i++)
            if (s.charAt(i) != t.charAt(i))
                // 如果字符串具有相同的长度
                if (ns == nt)
                    return s.substring(i + 1).equals(t.substring(i + 1));
                    // 如果字符串具有不同的长度
                else
                    return s.substring(i).equals(t.substring(i + 1));

        // 如果在 ns 距离上没有差异，则仅当 t 有多一个字符时，字符串才有一次编辑。
        return (ns + 1 == nt);
    }

    public static void main(String[] args) {
        // 测试用例1
        String s1 = "ab";
        String t1 = "acb";
        // 预期输出：true
        System.out.println(new OneEditDistance().isOneEditDistance(s1, t1));

        // 测试用例2
        String s2 = "cab";
        String t2 = "ad";
        // 预期输出：false
        System.out.println(new OneEditDistance().isOneEditDistance(s2, t2));
    }
}
