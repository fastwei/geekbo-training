package com.geekbo.training.leetcode.top75;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * You are given an array of strings products and a string searchWord.
 *
 * Design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.
 *
 * Return a list of lists of the suggested products after each character of searchWord is typed.
 *
 *
 *
 * Example 1:
 *
 * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * Output: [["mobile","moneypot","monitor"],["mobile","moneypot","monitor"],["mouse","mousepad"],["mouse","mousepad"],["mouse","mousepad"]]
 * Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"].
 * After typing m and mo all products match and we show user ["mobile","moneypot","monitor"].
 * After typing mou, mous and mouse the system suggests ["mouse","mousepad"].
 * Example 2:
 *
 * Input: products = ["havana"], searchWord = "havana"
 * Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 * Explanation: The only word "havana" will be always suggested while typing the search word.
 *
 *
 */
public class SearchSuggestionSystem {

    /**
     * 解题思路：
     * <p>
     * 首先，对产品名称进行字典序排序，以便后续的二分查找。
     * 对于每个输入字符，将其添加到前缀中，然后遍历产品列表，找到以当前前缀开始的产品。
     * 如果找到的产品数量不超过三个，将其添加到建议列表中。
     * 将建议列表添加到结果列表中。
     * 重复上述步骤，直到遍历完所有输入字符。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 对产品名称进行字典序排序的时间复杂度为O(nlogn)，其中n是产品数量。
     * 对于每个输入字符，需要遍历产品列表，时间复杂度为O(n)，其中n是产品数量。
     * 由于输入字符的数量为m，因此总的时间复杂度为O(mnlogn)。
     * 结果列表的空间复杂度为O(m)。
     *
     * @param products
     * @param searchWord
     * @return
     */
    public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products); // 对产品名称进行字典序排序
        List<List<String>> result = new ArrayList<>();
        StringBuilder prefix = new StringBuilder();

        for (char c : searchWord.toCharArray()) {
            prefix.append(c); // 每输入一个字符，将其添加到前缀中
            List<String> suggestions = new ArrayList<>();

            for (String product : products) {
                if (product.startsWith(prefix.toString())) {
                    suggestions.add(product);
                }
                if (suggestions.size() == 3) {
                    break;
                }
            }

            result.add(suggestions);
        }

        return result;
    }

    public static void main(String[] args) {
        // 测试用例 1
        String[] products1 = {"mobile", "mouse", "moneypot", "monitor", "mousepad"};
        String searchWord1 = "mouse";
        List<List<String>> result1 = suggestedProducts(products1, searchWord1);
        System.out.println(result1); // [["mobile","moneypot","monitor"],["mobile","moneypot","monitor"],["mouse","mousepad"],["mouse","mousepad"],["mouse","mousepad"]]

        // 测试用例 2
        String[] products2 = {"havana"};
        String searchWord2 = "havana";
        List<List<String>> result2 = suggestedProducts(products2, searchWord2);
        System.out.println(result2); // [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
    }
}