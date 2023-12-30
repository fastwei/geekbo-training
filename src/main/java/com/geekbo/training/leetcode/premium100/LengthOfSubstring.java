package com.geekbo.training.leetcode.premium100;

/**
 *
 * 1100. 长度为 K 的无重复字符子串
 * 中等
 * 给你一个字符串 S，找出所有长度为 K 且不含重复字符的子串，请你返回全部满足要求的子串的 数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：S = "havefunonleetcode", K = 5
 * 输出：6
 * 解释：
 * 这里有 6 个满足题意的子串，分别是：'havef','avefu','vefun','efuno','etcod','tcode'。
 * 示例 2：
 *
 * 输入：S = "home", K = 5
 * 输出：0
 * 解释：
 * 注意：K 可能会大于 S 的长度。在这种情况下，就无法找到任何长度为 K 的子串。
 *
 *
 * 提示：
 *
 * 1 <= S.length <= 10^4
 * S 中的所有字符均为小写英文字母
 * 1 <= K <= 10^4
 */
public class LengthOfSubstring {

    public int numKLenSubstrNoRepeats(String s, int k) {
        // 复用第一种方法中的条件
        // 对于k > 26，不存在只有唯一字符的子字符串
        if (k > 26)
            return 0;

        int answer = 0;
        int n = s.length();

        // 初始化左、右指针
        int left = 0, right = 0;
        // 初始化频率数组
        int freq[] = new int[26];

        while (right < n) {

            // 在频率数组中添加当前字符
            freq[s.charAt(right) - 'a']++;

            // 如果当前字符在频率数组中出现多次
            // 继续收缩窗口并从频率数组中删除字符，直到当前字符的频率变为1。
            while (freq[s.charAt(right) - 'a'] > 1) {
                freq[s.charAt(left) - 'a']--;
                left++;
            }


            // 检查当前唯一子字符串的长度是否等于k
            if (right - left + 1 == k) {
                answer++;

                // 收缩窗口并从频率数组中移除最左边的字符
                freq[s.charAt(left) - 'a']--;
                left++;
            }

            // 拓展窗口
            right++;
        }

        return answer;
    }

    /**
     * 解题思路：
     * 使用滑动窗口来解决问题。
     * 定义一个窗口，窗口内最多只包含K个不同的字符。
     * 使用哈希表来存储窗口内每个字符的出现次数。
     * 右指针向右移动，将当前字符加入窗口，并更新字符的出现次数。
     * 当窗口内不同字符的个数超过K个时，左指针向右移动，直到窗口内不同字符的个数不超过K个。
     * 每次移动右指针和左指针时，更新满足要求的子串的个数。
     * 最后返回满足要求的子串的个数。
     *
     * 算法复杂度分析：
     * 时间复杂度：O(n)，其中 n 是字符串 S 的长度。最坏情况下，左指针和右指针各遍历字符串一次。
     * 空间复杂度：O(1)，哈希表的大小最多为 26。
     * todo:还有测试用例通不过
     * @param S 字符串
     * @param K 子串的长度
     * @return 满足要求的子串的个数
     */
    public static int lengthOfSubstring(String S, int K) {
        int result = 0;
        int left = 0;
        int right = 0;
        int count = 0;
        int[] freq = new int[26];

        while (right < S.length()) {
            if (freq[S.charAt(right) - 'a'] == 0) {
                count++;
            }
            freq[S.charAt(right) - 'a']++;
            right++;

            if (count == K) {
                result++;
            }

            while (count > K) {
                freq[S.charAt(left) - 'a']--;
                if (freq[S.charAt(left) - 'a'] == 0) {
                    count--;
                }
                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String s1 = "havefunonleetcode";
        int k1 = 5;
        // 6
        System.out.println(lengthOfSubstring(s1, k1));

        String s2 = "home";
        int k2 = 5;
        // 0
        System.out.println(lengthOfSubstring(s2, k2));
    }
}