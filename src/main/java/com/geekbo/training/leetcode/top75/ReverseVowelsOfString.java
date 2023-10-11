package com.geekbo.training.leetcode.top75;


/**
 *Array / String
 *
 *Given a string s, reverse only all the vowels in the string and return it.
 *
 * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "hello"
 * Output: "holle"
 *
 * Example 2:
 *
 * Input: s = "leetcode"
 * Output: "leotcede"
 *
 * 实现思路：
 *
 * 将字符串转换为字符数组，以便于操作。
 *
 * 使用左右两个指针，从字符串的两端向中间移动。
 *
 * 创建一个包含所有元音字母的集合。
 *
 * 开始遍历字符串，同时检查左右指针指向的字符是否为元音字母。
 *
 * 如果左右指针都指向元音字母，交换它们的位置，然后继续移动指针。
 *
 * 如果只有一个指针指向元音字母，移动另一个指针。
 *
 * 最终将字符数组转换回字符串，返回结果。
 *
 *
 * Array / String
 */
public class ReverseVowelsOfString {
    public static String reverseVowels(String s) {
        // 将字符串转换为字符数组，方便操作
        char[] chars = s.toCharArray();
        
        // 定义左右两个指针，分别从字符串的两端开始向中间移动
        int left = 0;
        int right = s.length() - 1;
        
        // 创建一个包含所有元音字母的集合
        String vowels = "aeiouAEIOU";
        
        // 开始遍历字符串
        while (left < right) {
            // 左指针指向的字符是元音字母，并且右指针指向的字符也是元音字母
            if (vowels.contains(chars[left] + "") && vowels.contains(chars[right] + "")) {
                // 交换左右指针指向的元音字母
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                
                // 移动指针，继续查找下一对元音字母
                left++;
                right--;
            } else if (vowels.contains(chars[left] + "")) {
                // 如果只有左指针指向的是元音字母，移动右指针
                right--;
            } else {
                // 如果只有右指针指向的是元音字母，移动左指针
                left++;
            }
        }
        
        // 将字符数组转换回字符串
        return new String(chars);
    }
    
    public static void main(String[] args) {
        // 测试用例
        String s1 = "hello";
        String result1 = reverseVowels(s1);
        System.out.println("Result 1: " + result1); // 输出 "holle"

        String s2 = "leetcode";
        String result2 = reverseVowels(s2);
        System.out.println("Result 2: " + result2); // 输出 "leotcede"
    }
}
