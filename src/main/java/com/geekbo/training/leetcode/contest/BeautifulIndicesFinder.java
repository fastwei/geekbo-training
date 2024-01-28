package com.geekbo.training.leetcode.contest;

import java.util.ArrayList;
import java.util.List;
//380.2
public class BeautifulIndicesFinder {
    /**
     * 找出所有美丽索引。
     *
     * @param s 输入的字符串。
     * @param a 要匹配的子字符串 a。
     * @param b 要匹配的子字符串 b。
     * @param k 索引距离的上限。
     * @return 满足条件的美丽索引列表。
     */
    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        List<Integer> aIndices = findIndices(s, a);
        List<Integer> bIndices = findIndices(s, b);
        List<Integer> result = new ArrayList<>();

        for (int i : aIndices) {
            // 使用双指针技术来找出满足条件的美丽索引
            for (int j : bIndices) {
                if (Math.abs(j - i) <= k) {
                    result.add(i);
                    break;
                }
            }
        }

        return result;
    }

    /**
     * 在字符串 s 中查找子字符串 sub 的所有出现位置。
     *
     * @param s   源字符串。
     * @param sub 要查找的子字符串。
     * @return 子字符串所有出现的索引列表。
     */
    private List<Integer> findIndices(String s, String sub) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i <= s.length() - sub.length(); i++) {
            if (s.substring(i, i + sub.length()).equals(sub)) {
                indices.add(i);
            }
        }
        return indices;
    }

    public static void main(String[] args) {
        BeautifulIndicesFinder finder = new BeautifulIndicesFinder();
        // 测试用例1
        System.out.println(finder.beautifulIndices("isawsquirrelnearmysquirrelhouseohmy", "my", "squirrel", 15));
        // 预期输出: [16, 33]

        // 测试用例2
        System.out.println(finder.beautifulIndices("abcd", "a", "a", 4));
        // 预期输出: [0]
    }
}
