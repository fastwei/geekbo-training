package com.geekbo.training.leetcode.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 386. Lexicographical Numbers
 * Medium
 * <p>
 * Given an integer n, return all the numbers in the range [1, n] sorted in lexicographical order.
 * <p>
 * You must write an algorithm that runs in O(n) time and uses O(1) extra space.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 13
 * Output: [1,10,11,12,13,2,3,4,5,6,7,8,9]
 * Example 2:
 * <p>
 * Input: n = 2
 * Output: [1,2]
 */
public class LexicographicalNumbers {
    /**
     * 解题思路：
     * <p>
     * 首先，我们定义一个List来存储结果。
     * 然后，我们从1开始循环，每次将当前数添加到结果中。
     * 如果当前数的十倍仍然小于等于n，那么我们将当前数乘以10。
     * 否则，如果当前数的个位数不是9，并且当前数加1仍然小于等于n，那么我们将当前数加1。
     * 否则，我们将当前数除以10的十位数为9的部分去除，然后将当前数除以10再加1作为下一个数。
     * 最后，返回结果。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：由于我们需要遍历从1到n的所有数，所以时间复杂度为O(n)。
     * 空间复杂度：使用了一个List来存储结果，所以空间复杂度为O(n)。
     * <p>
     * 总结：
     * <p>
     * 遍历从1到n的所有数，按照字典序将它们添加到结果中。
     * 时间复杂度为O(n)，空间复杂度为O(n)。
     *
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();
        int curr = 1;

        for (int i = 1; i <= n; i++) {
            result.add(curr);
            if (curr * 10 <= n) {
                curr *= 10;
            } else if (curr % 10 != 9 && curr + 1 <= n) {
                curr++;
            } else {
                while ((curr / 10) % 10 == 9) {
                    curr /= 10;
                }
                curr = curr / 10 + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        LexicographicalNumbers solution = new LexicographicalNumbers();

        // 测试用例1
        int n1 = 13;
        List<Integer> result1 = solution.lexicalOrder(n1);
        System.out.println("测试用例1:");
        System.out.println(result1); // 预期输出: [1,10,11,12,13,2,3,4,5,6,7,8,9]

        // 测试用例2
        int n2 = 2;
        List<Integer> result2 = solution.lexicalOrder(n2);
        System.out.println("测试用例2:");
        System.out.println(result2); // 预期输出: [1,2]
    }
}
