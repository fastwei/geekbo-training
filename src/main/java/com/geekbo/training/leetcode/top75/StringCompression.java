package com.geekbo.training.leetcode.top75;

/**
 * Array / String
 * 题目描述：
 * 给定一个字符数组chars，使用以下算法进行压缩：
 * 从一个空字符串s开始。对于chars中的每组连续重复字符：
 * - 如果组的长度为1，则将字符追加到s中。
 * - 否则，将字符后跟组的长度追加到s中。
 * 压缩后的字符串s不应该单独返回，而是存储在输入字符数组chars中。注意，长度为10或更长的组将拆分为多个字符。
 * 完成对输入数组的修改后，返回新数组的长度。
 *
 * 算法要求只能使用常数额外的空间。
 *
 * 示例：
 * 输入: chars = ["a","a","b","b","c","c","c"]
 * 输出: 返回6，输入数组的前6个字符应为: ["a","2","b","2","c","3"]
 * 解释: 连续重复字符的组是 "aa"、"bb" 和 "ccc"。压缩为 "a2b2c3"。
 *
 * 输入: chars = ["a"]
 * 输出: 返回1，输入数组的第一个字符应为: ["a"]
 * 解释: 唯一的组是 "a"，因为它是单个字符，保持未压缩状态。
 *
 * 输入: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * 输出: 返回4，输入数组的前4个字符应为: ["a","b","1","2"]。
 * 解释: 连续重复字符的组是 "a" 和 "bbbbbbbbbbbb"。压缩为 "ab12"。
 *
 * 解题思路：
 * 遍历字符数组，使用双指针来记录连续重复字符的起始和结束位置。同时维护一个变量count来记录连续重复字符的数量。
 * 当遇到不连续的字符时，将字符存入chars数组，并更新count。
 * 最后，根据count的值，将数字字符存入chars数组，实现压缩。
 * 返回新数组的长度即可。
 *
 * 算法复杂度分析：
 * - 时间复杂度：O(n)，其中n为字符数组的长度，需要遍历整个数组一次。
 * - 空间复杂度：O(1)，只需要常数级的额外空间。
 */

public class StringCompression {
    public int compress(char[] chars) {
        int n = chars.length; // 数组长度
        int writeIdx = 0; // 写入位置的索引
        int left = 0; // 连续重复字符的起始位置

        for (int readIdx = 0; readIdx < n; readIdx++) {
            // 如果遇到不连续的字符或到达数组末尾
            if (readIdx == n - 1 || chars[readIdx] != chars[readIdx + 1]) {
                chars[writeIdx++] = chars[readIdx]; // 存储当前字符
                int count = readIdx - left + 1; // 连续重复字符的数量
                if (count > 1) {
                    // 如果数量大于1，需要将数字字符存入数组
                    char[] countChars = String.valueOf(count).toCharArray();
                    for (char c : countChars) {
                        chars[writeIdx++] = c;
                    }
                }
                left = readIdx + 1; // 更新连续重复字符的起始位置
            }
        }

        return writeIdx;
    }

    public static void main(String[] args) {
        StringCompression solution = new StringCompression();

        // 测试用例1
        char[] chars1 = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        int result1 = solution.compress(chars1);
        System.out.print("Example 1: [");
        for (int i = 0; i < result1; i++) {
            System.out.print("'" + chars1[i] + "'");
            if (i < result1 - 1) {
                System.out.print(",");
            }
        }
        System.out.println("], 返回长度: " + result1); // 预期输出：[ 'a', '2', 'b', '2', 'c', '3' ], 返回长度: 6

        // 测试用例2
        char[] chars2 = {'a'};
        int result2 = solution.compress(chars2);
        System.out.print("Example 2: [");
        for (int i = 0; i < result2; i++) {
            System.out.print("'" + chars2[i] + "'");
            if (i < result2 - 1) {
                System.out.print(",");
            }
        }
        System.out.println("], 返回长度: " + result2); // 预期输出：[ 'a' ], 返回长度: 1

        // 测试用例3
        char[] chars3 = {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
        int result3 = solution.compress(chars3);
        System.out.print("Example 3: [");
        for (int i = 0; i < result3; i++) {
            System.out.print("'" + chars3[i] + "'");
            if (i < result3 - 1) {
                System.out.print(",");
            }
        }
        System.out.println("], 返回长度: " + result3); // 预期输出：[ 'a', 'b', '1', '2' ], 返回长度: 4
    }
}
