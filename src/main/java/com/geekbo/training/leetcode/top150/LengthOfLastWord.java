package com.geekbo.training.leetcode.top150;

/**
 * 58. Length of Last Word
 * 首先去除字符串末尾的空格，然后从字符串末尾开始逐字符向前遍历，直到遇到空格或字符串开始位置。
 * 在遍历过程中累计字符的数量，最后返回最后一个单词的长度。
 *
 */
public class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        // 去除字符串末尾的空格
        s = s.trim();
        int length = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == ' ') {
                // 遇到空格表示单词结束
                break;
            }
            length++;
        }

        return length;
    }


    // 测试用例
    public static void main(String[] args) {
        LengthOfLastWord lengthOfLastWord = new LengthOfLastWord();

        String s1 = "Hello World";
        int result1 = lengthOfLastWord.lengthOfLastWord(s1);
        System.out.println("Example 1: " + result1); // 输出 5

        String s2 = "   fly me   to   the moon  ";
        int result2 = lengthOfLastWord.lengthOfLastWord(s2);
        System.out.println("Example 2: " + result2); // 输出 4

        String s3 = "luffy is still joyboy";
        int result3 = lengthOfLastWord.lengthOfLastWord(s3);
        System.out.println("Example 3: " + result3); // 输出 6
    }
}
