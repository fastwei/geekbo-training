package com.geekbo.training.leetcode.top75;

/**
 * Array / String
 *
 *You are given two strings word1 and word2.
 * Merge the strings by adding letters in alternating order, starting with word1.
 * If a string is longer than the other, append the additional letters onto the end of the merged string.
 *
 *
 *解决思路：
 *
 * 使用 StringBuilder 来构建合并后的字符串。
 * 遍历两个输入字符串，依次从每个字符串中取一个字符添加到结果中，直到其中一个字符串用完。
 * 如果其中一个字符串有剩余字符，将其全部添加到结果中。
 * 返回合并后的字符串。
 * 时间复杂度分析：
 *
 * 遍历两个输入字符串一次，时间复杂度为 O(max(len1, len2))，其中 len1 和 len2 分别是两个输入字符串的长度。
 *
 *Array / String
 */
public class MergeStringsAlternately {
    public static String mergeAlternately(String word1, String word2) {
        StringBuilder result = new StringBuilder();
        int len1 = word1.length();
        int len2 = word2.length();
        int i = 0;
        
        // 依次添加字符，直到两个字符串都用完为止
        while (i < len1 && i < len2) {
            result.append(word1.charAt(i));
            result.append(word2.charAt(i));
            i++;
        }
        
        // 如果word1有剩余字符，将其添加到结果中
        while (i < len1) {
            result.append(word1.charAt(i));
            i++;
        }
        
        // 如果word2有剩余字符，将其添加到结果中
        while (i < len2) {
            result.append(word2.charAt(i));
            i++;
        }
        
        return result.toString();
    }
    
    public static void main(String[] args) {
        // 测试用例
        String word1 = "abc";
        String word2 = "defgh";
        
        System.out.println("Merged Alternately: " + mergeAlternately(word1, word2)); // 输出: "adbecfgh"
    }
}
