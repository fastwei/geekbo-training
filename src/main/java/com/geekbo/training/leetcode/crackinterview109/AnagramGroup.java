package com.geekbo.training.leetcode.crackinterview109;

import java.util.*;

/**
 * 面试题 10.02. 变位词组
 * 中等
 * 编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
 * <p>
 * 注意：本题相对原题稍作修改
 * <p>
 * 示例:
 * <p>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 说明：
 * <p>
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 */
public class AnagramGroup {

    /**
     * 将变位词组合在一起
     * 解题思路：
     * <p>
     * 遍历字符串数组，对每个字符串进行排序，将排序后的字符串作为键，将原始字符串添加到对应的列表中。
     * 使用哈希表存储变位词组合，键为排序后的字符串，值为对应的列表。
     * 遍历哈希表的值，将所有列表添加到结果列表中。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：假设字符串数组的长度为n，对每个字符串进行排序的时间复杂度为O(klogk)，其中k为字符串的长度。总共需要遍历n个字符串，所以时间复杂度为O(nklogk)。
     * 空间复杂度：使用了哈希表和结果列表，哈希表的大小取决于不同的变位词数量，最坏情况下为n，结果列表的大小也为n。因此，空间复杂度为O(n)。
     *
     * @param strs 字符串数组
     * @return 变位词组合的列表
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        // 创建一个哈希表，用于存储变位词组合
        Map<String, List<String>> map = new HashMap<>();

        // 遍历字符串数组
        for (String str : strs) {
            // 将字符串转换为字符数组，并进行排序
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);

            // 将排序后的字符数组转换为字符串
            String sortedStr = new String(charArray);

            // 如果哈希表中不存在以排序后的字符串为键的列表，则创建一个新的列表，并将其添加到哈希表中
            if (!map.containsKey(sortedStr)) {
                map.put(sortedStr, new ArrayList<>());
            }

            // 将字符串添加到对应的列表中
            map.get(sortedStr).add(str);
        }

        // 将哈希表中的所有列表添加到结果列表中
        List<List<String>> result = new ArrayList<>(map.values());

        return result;
    }

    public static void main(String[] args) {
        AnagramGroup solution = new AnagramGroup();

        // 测试用例
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = solution.groupAnagrams(strs);

        // 预期输出:
        // [
        //   ["ate","eat","tea"],
        //   ["nat","tan"],
        //   ["bat"]
        // ]
        System.out.println(result);
    }
}
