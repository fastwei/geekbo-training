package com.geekbo.training.leetcode.top150;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
 * <p>
 * Each letter in magazine can only be used once in ransomNote.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: ransomNote = "a", magazine = "b"
 * Output: false
 * Example 2:
 * <p>
 * Input: ransomNote = "aa", magazine = "ab"
 * Output: false
 * Example 3:
 * <p>
 * Input: ransomNote = "aa", magazine = "aab"
 * Output: true
 */
public class RansomNote {
    /**
     * 解题思路：
     * 我们可以使用两个哈希映射（HashMap）分别记录 ransomNote 和 magazine 中字符的频率。
     * 然后，对于 ransomNote 中的每个字符，检查它在 magazine 中的频率是否足够。
     * 如果所有字符的频率都足够，就返回 true；否则，返回 false。
     * <p>
     * 算法复杂度分析：
     * - 时间复杂度：遍历 ransomNote 和 magazine 分别需要 O(n) 和 O(m) 的时间，
     * 其中 n 和 m 分别是两个字符串的长度。构建哈希映射也需要 O(n) 和 O(m) 的时间。
     * 遍历哈希映射并比较字符频率需要 O(k) 的时间，其中 k 是字符集的大小（通常很小）。
     * 因此，总体时间复杂度为 O(n + m + k)。
     * - 空间复杂度：空间复杂度主要由两个哈希映射决定，它们分别存储字符的频率。因此，
     * 空间复杂度为 O(k)。
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        // 创建哈希映射以计算字符的频率
        Map<Character, Integer> ransomNoteMap = new HashMap<>();
        Map<Character, Integer> magazineMap = new HashMap<>();

        // 计算 ransomNote 中字符的频率
        for (char c : ransomNote.toCharArray()) {
            ransomNoteMap.put(c, ransomNoteMap.getOrDefault(c, 0) + 1);
        }

        // 计算 magazine 中字符的频率
        for (char c : magazine.toCharArray()) {
            magazineMap.put(c, magazineMap.getOrDefault(c, 0) + 1);
        }

        // 检查是否可以从 magazine 构建 ransomNote
        for (Map.Entry<Character, Integer> entry : ransomNoteMap.entrySet()) {
            char c = entry.getKey();
            int count = entry.getValue();

            // 如果 magazine 中没有足够的字符 c，或者频率不足以构建 ransomNote，返回 false
            if (!magazineMap.containsKey(c) || magazineMap.get(c) < count) {
                return false;
            }
        }

        // 如果成功遍历了 ransomNote 的所有字符，返回 true
        return true;
    }

    public static void main(String[] args) {
        RansomNote solution = new RansomNote();

        String ransomNote1 = "a";
        String magazine1 = "b";
        System.out.println(solution.canConstruct(ransomNote1, magazine1));  // 输出: false

        String ransomNote2 = "aa";
        String magazine2 = "ab";
        System.out.println(solution.canConstruct(ransomNote2, magazine2));  // 输出: false

        String ransomNote3 = "aa";
        String magazine3 = "aab";
        System.out.println(solution.canConstruct(ransomNote3, magazine3));  // 输出: true

        String ransomNote4 = "love";
        String magazine4 = "I love programming";
        System.out.println(solution.canConstruct(ransomNote4, magazine4));  // 输出: true

        String ransomNote5 = "xyz";
        String magazine5 = "abcdefg";
        System.out.println(solution.canConstruct(ransomNote5, magazine5));  // 输出: false

        String ransomNote6 = "abcdefg";
        String magazine6 = "abcdefg";
        System.out.println(solution.canConstruct(ransomNote6, magazine6));  // 输出: true
    }

}
