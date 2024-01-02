package com.geekbo.training.leetcode.premium100;


import java.util.HashMap;

public class SingleRowKeyboard {
    /**
     * 解题思路：
     * <p>
     * 首先，我们可以使用一个HashMap来存储键盘上每个字符对应的索引位置。
     * 然后，我们遍历字符串中的每个字符，计算手指从当前位置到目标字符位置的移动时间，并将目标字符位置更新为当前位置。
     * 最后，累加所有的移动时间，即为最终结果。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历字符串需要O(n)的时间，其中n为字符串的长度。同时，HashMap的操作时间复杂度为O(1)。因此，整体的时间复杂度为O(n)。
     * 空间复杂度：HashMap需要存储键盘上每个字符的索引，因此需要O(26)的空间，即O(1)。因此，整体的空间复杂度为O(1)。
     *
     * @param keyboard
     * @param word
     * @return
     */
    public static int calculateTime(String keyboard, String word) {
        // 创建HashMap来存储键盘上每个字符对应的索引位置
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < keyboard.length(); i++) {
            map.put(keyboard.charAt(i), i);
        }

        int totalTime = 0;
        int currIndex = 0;

        // 遍历字符串中的每个字符，计算移动时间
        for (char ch : word.toCharArray()) {
            int targetIndex = map.get(ch);
            totalTime += Math.abs(targetIndex - currIndex);
            currIndex = targetIndex;
        }

        return totalTime;
    }

    public static void main(String[] args) {
        // 测试用例
        String keyboard = "abcdefghijklmnopqrstuvwxyz";
        String word = "cba";
        // 预期输出: 4
        System.out.println(calculateTime(keyboard, word));

        keyboard = "pqrstuvwxyzabcdefghijklmno";
        word = "leetcode";
        // 预期输出: 73
        System.out.println(calculateTime(keyboard, word));
    }
}