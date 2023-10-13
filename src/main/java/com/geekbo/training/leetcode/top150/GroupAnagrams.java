package com.geekbo.training.leetcode.top150;

import java.util.*;

/**
 *
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 *
 *
 *
 * Example 1:
 *
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * Example 2:
 *
 * Input: strs = [""]
 * Output: [[""]]
 * Example 3:
 *
 * Input: strs = ["a"]
 * Output: [["a"]]
 *
 */
public class GroupAnagrams {
    /**
     * 实现思路：
     * <p>
     * 遍历字符串数组strs，对于每个字符串，将其进行排序，得到一个排序后的字符串作为key。
     * 使用一个HashMap来记录排序后的字符串与原始字符串列表的映射关系。
     * 遍历完整个字符串数组后，将HashMap中的值转换为列表，作为最终的分组结果。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历整个字符串数组需要O(n)的时间，其中n为数组的长度。
     * 对于每个字符串，需要将其进行排序，排序的时间复杂度为O(klogk)，其中k为字符串的长度。
     * 因此，总体时间复杂度为O(n * klogk)。
     * 空间复杂度：使用了一个HashMap来存储排序后的字符串与原始字符串列表的映射关系，HashMap的大小不会超过n。
     * 因此，空间复杂度为O(n)。
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            // 将字符串进行排序，得到排序后的字符串作为key
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedStr = new String(chars);

            // 将排序后的字符串与原始字符串进行映射
            if (!map.containsKey(sortedStr)) {
                map.put(sortedStr, new ArrayList<>());
            }
            map.get(sortedStr).add(str);
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        GroupAnagrams solution = new GroupAnagrams();

        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> expected1 = new ArrayList<>();
        expected1.add(Arrays.asList("bat"));
        expected1.add(Arrays.asList("nat", "tan"));
        expected1.add(Arrays.asList("ate", "eat", "tea"));
        List<List<String>> result1 = solution.groupAnagrams(strs1);
        System.out.println(expected1.equals(result1)); // true

        String[] strs2 = {""};
        List<List<String>> expected2 = new ArrayList<>();
        expected2.add(Arrays.asList(""));
        List<List<String>> result2 = solution.groupAnagrams(strs2);
        System.out.println(expected2.equals(result2)); // true

        String[] strs3 = {"a"};
        List<List<String>> expected3 = new ArrayList<>();
        expected3.add(Arrays.asList("a"));
        List<List<String>> result3 = solution.groupAnagrams(strs3);
        System.out.println(expected3.equals(result3)); // true
    }
}