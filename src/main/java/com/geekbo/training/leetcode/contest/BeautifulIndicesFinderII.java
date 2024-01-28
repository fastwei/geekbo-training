package com.geekbo.training.leetcode.contest;

import java.util.ArrayList;
import java.util.List;
//380.4
public class BeautifulIndicesFinderII {
    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        List<Integer> result = new ArrayList<>();
        int[] nearestBLeft = new int[s.length()];
        int[] nearestBRight = new int[s.length()];
        int lastB = -s.length();

        // 从左到右预处理，记录每个位置最近的b匹配位置
        for (int i = 0; i < s.length(); i++) {
            if (i >= b.length() - 1 && s.substring(i - b.length() + 1, i + 1).equals(b)) {
                lastB = i - b.length() + 1;
            }
            nearestBLeft[i] = lastB;
        }

        lastB = 2 * s.length();
        // 从右到左预处理，记录每个位置最近的b匹配位置
        for (int i = s.length() - 1; i >= 0; i--) {
            if (i + b.length() <= s.length() && s.substring(i, i + b.length()).equals(b)) {
                lastB = i;
            }
            nearestBRight[i] = lastB;
        }

        // 遍历字符串s，寻找匹配的a
        for (int i = 0; i <= s.length() - a.length(); i++) {
            if (s.substring(i, i + a.length()).equals(a)) {
                if ((i - nearestBLeft[i] <= k && nearestBLeft[i] != -s.length()) ||
                        (nearestBRight[Math.min(i + a.length() - 1, s.length() - 1)] - i <= k && nearestBRight[i] != 2 * s.length())) {
                    result.add(i);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        BeautifulIndicesFinder finder = new BeautifulIndicesFinder();

        // 测试用例1
        String s1 = "isawsquirrelnearmysquirrelhouseohmy";
        String a1 = "my";
        String b1 = "squirrel";
        int k1 = 15;
        System.out.println("Expected: [16, 33], Actual: " + finder.beautifulIndices(s1, a1, b1, k1));

        // 测试用例2
        String s2 = "abcd";
        String a2 = "a";
        String b2 = "a";
        int k2 = 4;
        System.out.println("Expected: [0], Actual: " + finder.beautifulIndices(s2, a2, b2, k2));

        // 测试用例3
        String s3 = "jajrfw";
        String a3 = "rf";
        String b3 = "j";
        int k3 = 3;
        System.out.println("Expected: [3], Actual: " + finder.beautifulIndices(s3, a3, b3, k3));


        // 测试用例4
        String s4 = "ithhi";
        String a4 = "t";
        String b4 = "hhi";
        int k4 = 1;
        System.out.println("Expected: [1], Actual: " + finder.beautifulIndices(s4, a4, b4, k4));
    }
}
