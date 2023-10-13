package com.geekbo.training.leetcode.top150;

import java.util.Arrays;


/**
 * Given two strings s and t, determine if they are isomorphic.
 * <p>
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 * <p>
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character, but a character may map to itself.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 * <p>
 * Input: s = "paper", t = "title"
 * Output: true
 * <p>
 * todo:加深理解IsomorphicStrings
 * egg 和 add 同构，就意味着下边的映射成立
 * e -> a
 * g -> d
 * 也就是将 egg 的 e 换成 a, g 换成 d, 就变成了 add
 * <p>
 * 同时倒过来也成立
 * a -> e
 * d -> g
 * 也就是将 add 的 a 换成 e, d 换成 g, 就变成了 egg
 * <p>
 * foo 和 bar 不同构，原因就是映射不唯一
 * o -> a
 * o -> r
 * 其中 o 映射到了两个字母
 */
public class IsomorphicStrings {
    /**
     * 解题思路：
     * <p>
     * 首先判断两个字符串的长度是否相同，如果不相同，则它们一定不是isomorphic的。
     * 创建两个数组sToT和tToS，分别用于记录s到t和t到s的映射关系。
     * 遍历字符串s和t的每个字符，分别记为c1和c2。
     * 如果s到t的映射关系已经建立，但映射的值不等于当前字符c2，则不满足isomorphic的条件，返回false。
     * 如果t到s的映射关系已经建立，但映射的值不等于当前字符c1，则不满足isomorphic的条件，返回false。
     * 否则，建立s到t和t到s的映射关系。
     * 如果遍历完所有字符后没有返回false，则说明两个字符串是isomorphic的，返回true。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：假设字符串s的长度为n。在遍历字符串s和t的过程中，我们需要分别访问每个字符，所以时间复杂度为O(n)。
     * 空间复杂度：我们使用了两个数组sToT和tToS来记录映射关系，数组的长度为常数级别，所以空间复杂度为O(1)。
     * 综上所述，该算法的时间复杂度为O(n)，空间复杂度为O(1)。
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        // 使用两个数组分别记录s到t和t到s的映射关系
        int[] sToT = new int[256];
        int[] tToS = new int[256];

        // 初始化数组的值为-1，表示还没有建立映射关系
        Arrays.fill(sToT, -1);
        Arrays.fill(tToS, -1);

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            // 如果s到t的映射关系已经建立，但映射的值不等于当前字符c2，则不满足isomorphic的条件
            if (sToT[c1] != -1 && sToT[c1] != c2) {
                return false;
            }
            // 如果t到s的映射关系已经建立，但映射的值不等于当前字符c1，则不满足isomorphic的条件
            if (tToS[c2] != -1 && tToS[c2] != c1) {
                return false;
            }

            // 建立s到t和t到s的映射关系
            sToT[c1] = c2;
            tToS[c2] = c1;
        }

        return true;
    }

    public static void main(String[] args) {
        IsomorphicStrings solution = new IsomorphicStrings();

        // 测试用例1
        String s1 = "egg";
        String t1 = "add";
        boolean expected1 = true;
        boolean result1 = solution.isIsomorphic(s1, t1);
        System.out.println(result1 == expected1); // true

        // 测试用例2
        String s2 = "foo";
        String t2 = "bar";
        boolean expected2 = false;
        boolean result2 = solution.isIsomorphic(s2, t2);
        System.out.println(result2 == expected2); // true

        // 测试用例3
        String s3 = "paper";
        String t3 = "title";
        boolean expected3 = true;
        boolean result3 = solution.isIsomorphic(s3, t3);
        System.out.println(result3 == expected3); // true
    }
}