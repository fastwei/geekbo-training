package com.geekbo.training.leetcode.top150;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {


    /**
     * 解决思路：
     * 1. 首先检查两个字符串的长度，如果不相等，则它们不可能是字谜，返回false。
     * 2. 使用HashMap统计字符串s中每个字符的出现次数。
     * 3. 遍历字符串t，对于每个字符，从HashMap中减去相应的计数，如果计数变为负数，则返回false。
     * 4. 如果成功遍历字符串t并且HashMap中的所有计数都为0，则表示t是s的字谜，返回true。
     *
     * 时间复杂度：O(n)，其中n是字符串s的长度。
     * 空间复杂度：O(1)，因为只使用了固定大小的HashMap，不会随着输入的增加而变化。
     */

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        // 使用HashMap统计字符串s中每个字符的出现次数
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        // 遍历字符串t，对于每个字符，从HashMap中减去相应的计数
        for (char c : t.toCharArray()) {
            if (!charCount.containsKey(c) || charCount.get(c) <= 0) {
                return false;
            }
            charCount.put(c, charCount.get(c) - 1);
        }

        // 如果HashMap中的所有计数都为0，则表示t是s的字谜
        return true;
    }

    /**
     * 判断两个字符串是否是异位词（Anagram）。
     *
     * 解题思路：
     * 1. 首先检查两个字符串的长度是否相等，如果不相等，则不可能是异位词。
     * 2. 创建一个大小为26的整数数组，用于统计每个小写字母的出现次数。
     * 3. 遍历字符串s，对于每个字符，将其出现次数加1。
     * 4. 遍历字符串t，对于每个字符，将其出现次数减1。
     * 5. 如果最终整数数组中的所有元素都为0，则说明两个字符串是异位词。
     *
     * 算法复杂度：
     * - 时间复杂度：O(n)，其中n是字符串s的长度。
     * - 空间复杂度：O(1)，因为整数数组的大小是固定的，与字符串长度无关。
     *
     * @param s 第一个字符串
     * @param t 第二个字符串
     * @return 如果是异位词返回true，否则返回false
     */
    public static boolean isAnagramGood(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] count = new int[26];

        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        for (char ch : t.toCharArray()) {
            count[ch - 'a']--;
        }

        for (int i : count) {
            if (i != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // 测试用例
        String s1 = "anagram";
        String t1 = "nagaram";
        System.out.println(isAnagram(s1, t1)); // 输出：true

        String s2 = "rat";
        String t2 = "car";
        System.out.println(isAnagram(s2, t2)); // 输出：false
    }
}
