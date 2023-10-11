package com.geekbo.training.leetcode.top150.hard;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 *
 * 采用贪心策略，逐行确定单词的排列，确保每一行的长度不超过maxWidth。根据单词之间的空格分配规则，分配额外的空格，最后将每一行添加到结果列表中。
 *
 * 解题思路：
 *
 * 从左到右遍历单词，确定每一行能容纳的单词。
 * 根据单词之间的空格分配规则，确定每一行中的空格数量。
 * 特殊处理最后一行或只有一个单词的情况。
 * 循环执行，直到处理完所有单词。
 * 算法复杂度：
 *
 * 时间复杂度：O(N)，其中N是单词数组的长度，因为我们遍历每个单词一次。
 * 空间复杂度：O(1)，除了结果列表外，我们没有使用额外的数据结构。
 *
 */
public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int start = 0;

        while (start < words.length) {
            int end = start;
            int lineLength = 0;

            // 找到一行中尽可能多的单词
            while (end < words.length && lineLength + words[end].length() + end - start <= maxWidth) {
                lineLength += words[end].length();
                end++;
            }

            StringBuilder line = new StringBuilder();
            int extraSpaces = maxWidth - lineLength;

            // 特殊处理最后一行或只有一个单词的情况
            if (end == words.length || end - start == 1) {
                for (int i = start; i < end; i++) {
                    line.append(words[i]);
                    if (i < end - 1) {
                        line.append(' ');
                        extraSpaces--;
                    }
                }
                while (extraSpaces > 0) {
                    line.append(' ');
                    extraSpaces--;
                }
            } else {
                int spacesBetweenWords = extraSpaces / (end - start - 1);
                int extraSpacesLeft = extraSpaces % (end - start - 1);

                for (int i = start; i < end; i++) {
                    line.append(words[i]);
                    if (i < end - 1) {
                        int spaces = spacesBetweenWords + (extraSpacesLeft-- > 0 ? 1 : 0);
                        for (int j = 0; j < spaces; j++) {
                            line.append(' ');
                        }
                    }
                }
            }

            result.add(line.toString());
            start = end;
        }

        return result;
    }

    public static void main(String[] args) {
        TextJustification textJustification = new TextJustification();
        String[] words1 = {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth1 = 16;
        System.out.println("Example 1:");
        List<String> result1 = textJustification.fullJustify(words1, maxWidth1);
        for (String line : result1) {
            System.out.println("\"" + line + "\"");
        }

        String[] words2 = {"What", "must", "be", "acknowledgment", "shall", "be"};
        int maxWidth2 = 16;
        System.out.println("\nExample 2:");
        List<String> result2 = textJustification.fullJustify(words2, maxWidth2);
        for (String line : result2) {
            System.out.println("\"" + line + "\"");
        }

        String[] words3 = {"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"};
        int maxWidth3 = 20;
        System.out.println("\nExample 3:");
        List<String> result3 = textJustification.fullJustify(words3, maxWidth3);
        for (String line : result3) {
            System.out.println("\"" + line + "\"");
        }
    }
}
