package com.geekbo.training.leetcode.premium100;

import java.util.*;

/**
 * 249. 移位字符串分组
 * 中等
 * 给定一个字符串，对该字符串可以进行 “移位” 的操作，也就是将字符串中每个字母都变为其在字母表中后续的字母，比如："abc" -> "bcd"。这样，我们可以持续进行 “移位” 操作，从而生成如下移位序列：
 * <p>
 * "abc" -> "bcd" -> ... -> "xyz"
 * 给定一个包含仅小写字母字符串的列表，将该列表中所有满足 “移位” 操作规律的组合进行分组并返回。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"]
 * 输出：
 * [
 * ["abc","bcd","xyz"],
 * ["az","ba"],
 * ["acef"],
 * ["a","z"]
 * ]
 * 解释：可以认为字母表首尾相接，所以 'z' 的后续为 'a'，所以 ["az","ba"] 也满足 “移位” 操作规律。
 */
class StringGrouping {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> groupMap = new HashMap<>();
        for (String string : strings) {
            int[] group = getGroup(string);
            String groupStr = Arrays.toString(group);
            List<String> groupList = groupMap.getOrDefault(groupStr, new ArrayList<>());
            groupList.add(string);
            groupMap.put(groupStr, groupList);
        }
        List<List<String>> groupStrings = new ArrayList<>(groupMap.values());
        return groupStrings;
    }

    private int[] getGroup(String string) {
        int length = string.length() - 1;
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            int difference = string.charAt(i + 1) - string.charAt(i);
            if (difference < 0) {
                difference += 26;
            }
            array[i] = difference;
        }
        return array;
    }
}