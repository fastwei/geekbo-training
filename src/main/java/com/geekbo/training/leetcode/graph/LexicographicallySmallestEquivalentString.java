package com.geekbo.training.leetcode.graph;

/**
 * 1061. Lexicographically Smallest Equivalent String
 * Medium
 * You are given two strings of the same length s1 and s2 and a string baseStr.
 * <p>
 * We say s1[i] and s2[i] are equivalent characters.
 * <p>
 * For example, if s1 = "abc" and s2 = "cde", then we have 'a' == 'c', 'b' == 'd', and 'c' == 'e'.
 * Equivalent characters follow the usual rules of any equivalence relation:
 * <p>
 * Reflexivity: 'a' == 'a'.
 * Symmetry: 'a' == 'b' implies 'b' == 'a'.
 * Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'.
 * For example, given the equivalency information from s1 = "abc" and s2 = "cde", "acd" and "aab"
 * are equivalent strings of baseStr = "eed", and "aab" is the lexicographically smallest equivalent string of baseStr.
 * <p>
 * Return the lexicographically smallest equivalent string of baseStr by using the equivalency information from s1 and s2.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s1 = "parker", s2 = "morris", baseStr = "parser"
 * Output: "makkek"
 * Explanation: Based on the equivalency information in s1 and s2, we can group their characters as [m,p], [a,o], [k,r,s], [e,i].
 * The characters in each group are equivalent and sorted in lexicographical order.
 * So the answer is "makkek".
 * Example 2:
 * <p>
 * Input: s1 = "hello", s2 = "world", baseStr = "hold"
 * Output: "hdld"
 * Explanation: Based on the equivalency information in s1 and s2, we can group their characters as [h,w], [d,e,o], [l,r].
 * So only the second letter 'o' in baseStr is changed to 'd', the answer is "hdld".
 * Example 3:
 * <p>
 * Input: s1 = "leetcode", s2 = "programs", baseStr = "sourcecode"
 * Output: "aauaaaaada"
 * Explanation: We group the equivalent characters in s1 and s2 as [a,o,e,r,s,c], [l,p], [g,t] and [d,m],
 * thus all letters in baseStr except 'u' and 'd' are transformed to 'a', the answer is "aauaaaaada".
 */
public class LexicographicallySmallestEquivalentString {

    /**
     * 寻找基于等价关系的字符串的字典序最小等价字符串
     * 解题思路：
     * <p>
     * 首先，我们需要使用并查集来处理等价关系。创建一个大小为26的parent数组，用来记录字符的父节点。
     * 然后，遍历字符串s1和s2的每个字符，根据它们的等价关系，更新parent数组。
     * 接下来，根据parent数组构造最小等价字符串。遍历baseStr的每个字符，找到它在parent数组中的根节点，并将根节点的字符添加到结果字符串中。
     * 最后，返回结果字符串。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 算法的时间复杂度取决于字符串的长度，假设字符串的长度为n。
     * <p>
     * 遍历s1和s2的每个字符，更新parent数组的时间复杂度为O(n)。
     * 根据parent数组构造最小等价字符串的时间复杂度也为O(n)。
     * 因此，总的时间复杂度为O(n)。
     * 算法的空间复杂度为O(1)，因为只使用了常数大小的额外空间。
     *
     * @param s1      字符串s1
     * @param s2      字符串s2
     * @param baseStr 基准字符串
     * @return 字典序最小等价字符串
     */
    public static String smallestEquivalentString(String s1, String s2, String baseStr) {
        int[] parent = new int[26];
        // 初始化parent数组
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }
        // 根据s1和s2的等价关系，更新parent数组
        for (int i = 0; i < s1.length(); i++) {
            int parentS1 = find(parent, s1.charAt(i) - 'a');
            int parentS2 = find(parent, s2.charAt(i) - 'a');
            if (parentS1 < parentS2) {
                parent[parentS2] = parentS1;
            } else {
                parent[parentS1] = parentS2;
            }
        }
        StringBuilder sb = new StringBuilder();
        // 根据parent数组，构造最小等价字符串
        for (char c : baseStr.toCharArray()) {
            int parentC = find(parent, c - 'a');
            sb.append((char) (parentC + 'a'));
        }
        return sb.toString();
    }

    /**
     * 查找元素的根节点
     *
     * @param parent parent数组
     * @param index  元素索引
     * @return 根节点
     */
    private static int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }

    public static void main(String[] args) {
        // 测试用例1
        String s1 = "parker";
        String s2 = "morris";
        String baseStr = "parser";
        // 预期输出："makkek"
        System.out.println(smallestEquivalentString(s1, s2, baseStr));

        // 测试用例2
        String s3 = "hello";
        String s4 = "world";
        String baseStr2 = "hold";
        // 预期输出："hdld"
        System.out.println(smallestEquivalentString(s3, s4, baseStr2));

        // 测试用例3
        String s5 = "leetcode";
        String s6 = "programs";
        String baseStr3 = "sourcecode";
        // 预期输出："aauaaaaada"
        System.out.println(smallestEquivalentString(s5, s6, baseStr3));
    }
}


