package com.geekbo.training.leetcode.top150;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every
 * substring
 * of the partition is a
 * palindrome
 * . Return all possible palindrome partitioning of s.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 * Example 2:
 * <p>
 * Input: s = "a"
 * Output: [["a"]]
 */
public class PalindromePartition {
    /**
     * 解题思路： 这道题可以使用回溯算法来解决。
     * 我们可以将字符串s分割成若干个回文子串，然后将这些子串组成一个分割方案。
     * <p>
     * 我们可以使用回溯算法来生成所有可能的分割方案。
     * 从字符串的起始位置开始，我们依次遍历字符串的每个位置，判断从当前位置到字符串末尾的子串是否是回文串。
     * 如果是回文串，则将这个子串加入到当前的分割方案中，并继续递归处理剩下的子串。
     * 如果不是回文串，则继续尝试下一个位置。
     * <p>
     * 在回溯的过程中，如果我们已经得到了一个分割方案，
     * 即当前位置已经到达字符串末尾，我们将当前的分割方案加入到结果列表中，然后返回。
     * <p>
     * 为了判断一个子串是否是回文串，我们可以使用双指针的方法。
     * 我们使用两个指针start和end，分别指向子串的起始位置和结束位置，
     * 然后依次比较两个指针指向的字符是否相等，直到两个指针相遇或者找到了不相等的字符。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n * 2^n)，其中n是字符串的长度。
     * 在最坏的情况下，字符串的每个位置都可以选择分割或者不分割，所以可能的分割方案的个数是2^n。
     * 对于每个分割方案，我们需要判断每个子串是否是回文串，这需要O(n)的时间。所以总的时间复杂度是O(n * 2^n)。
     * <p>
     * 空间复杂度：O(n)，其中n是字符串的长度。
     * 在回溯的过程中，我们需要使用O(n)的额外空间来保存当前的分割方案和判断子串是否是回文串。
     * 此外，最坏的情况下，可能的分割方案的个数是2^n，所以结果列表的空间复杂度是O(2^n)。
     * 综上所述，总的空间复杂度是O(n + 2^n)。
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        backtrack(s, 0, path, result);
        return result;
    }

    private void backtrack(String s, int start, List<String> path, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s, start, i)) {
                path.add(s.substring(start, i + 1));
                backtrack(s, i + 1, path, result);
                path.remove(path.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePartition palindromePartition = new PalindromePartition();

        // Test Case 1
        String s1 = "aab";
        List<List<String>> result1 = palindromePartition.partition(s1);
        System.out.println(result1);  // Expected output: [["a","a","b"],["aa","b"]]

        // Test Case 2
        String s2 = "a";
        List<List<String>> result2 = palindromePartition.partition(s2);
        System.out.println(result2);  // Expected output: [["a"]]
    }
}
