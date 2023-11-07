package com.geekbo.training.leetcode.daily;

import java.util.HashSet;
import java.util.Set;

public class CountVowelStrings {

    public static int vowelStrings(String[] words, int left, int right) {
        Set<Character> vowels = new HashSet<Character>() {{
            add('a');
            add('e');
            add('i');
            add('o');
            add('u');
        }};
        int ans = 0;
        for (int i = left; i <= right; ++i) {
            String word = words[i];
            if (vowels.contains(word.charAt(0)) && vowels.contains(word.charAt(word.length() - 1))) {
                ++ans;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        // 测试用例1
        String[] words1 = {"are", "amy", "u"};
        int left1 = 0, right1 = 2;
        // 预期输出：2
        System.out.println(vowelStrings(words1, left1, right1));

        // 测试用例2
        String[] words2 = {"hey", "aeo", "mu", "ooo", "artro"};
        int left2 = 1, right2 = 4;
        // 预期输出：3
        System.out.println(vowelStrings(words2, left2, right2));
    }
}
