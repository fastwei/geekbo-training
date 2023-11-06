package com.geekbo.training.leetcode.dp;

/**
 * 2466. Count Ways To Build Good Strings
 * Medium
 * Topics
 * Companies
 * Hint
 * Given the integers zero, one, low, and high, we can construct a string by starting with an empty string,
 * and then at each step perform either of the following:
 * <p>
 * Append the character '0' zero times.
 * Append the character '1' one times.
 * This can be performed any number of times.
 * <p>
 * A good string is a string constructed by the above process having a length between low and high (inclusive).
 * <p>
 * Return the number of different good strings that can be constructed satisfying these properties.
 * Since the answer can be large, return it modulo 109 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: low = 3, high = 3, zero = 1, one = 1
 * Output: 8
 * Explanation:
 * One possible valid good string is "011".
 * It can be constructed as follows: "" -> "0" -> "01" -> "011".
 * All binary strings from "000" to "111" are good strings in this example.
 * Example 2:
 * <p>
 * Input: low = 2, high = 3, zero = 1, one = 2
 * Output: 5
 * Explanation: The good strings are "00", "11", "000", "110", and "011".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= low <= high <= 105
 * 1 <= zero, one <= low
 * <p>
 * todo:还有错误
 */
public class CountWaysToBuildGoodStrings {
    /**
     * 我们定义一个一维数组dp，其中dp[i]表示长度为i的good string的数量。初始化dp[0]为1，因为空字符串也是一个good string。
     * <p>
     * 然后，我们从长度为1开始遍历到长度为high。对于每个长度，我们根据题目给定的zero和one的限制，更新dp数组。
     * <p>
     * 具体地，对于长度为end的good string，如果end大于等于zero，我们可以在dp[end-zero]的基础上构建出更长的good string，因此我们将dp[end]增加dp[end-zero]。同样地，如果end大于等于one，我们可以在dp[end-one]的基础上构建出更长的good string，因此我们将dp[end]增加dp[end-one]。
     * <p>
     * 最后，我们从low遍历到high，累加dp数组中长度在范围内的good string的数量，得到最终的答案。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：对于每个长度end，我们需要更新dp[end]，因此时间复杂度为O(high)。
     * 空间复杂度：我们使用一个数组dp来保存中间结果，因此空间复杂度为O(high)。
     * 请注意，这里的算法复杂度是基于给定的high值来计算的，如果high的值非常大，则算法的效率可能会降低。
     *
     * @param low
     * @param high
     * @param zero
     * @param one
     * @return
     */
    public int countGoodStrings(int low, int high, int zero, int one) {
        int[] dp = new int[high + 1];
        dp[0] = 1;
        int mod = 1000000007;

        for (int end = 1; end <= high; ++end) {
            if (end >= zero) {
                dp[end] += dp[end - zero];
                dp[end] %= mod;
            }
            if (end >= one) {
                dp[end] += dp[end - one];
                dp[end] %= mod;
            }
        }

        int answer = 0;
        for (int i = low; i <= high; ++i) {
            answer += dp[i];
            answer %= mod;
        }

        return answer;
    }

    public static void main(String[] args) {
        // 创建解法对象
        CountWaysToBuildGoodStrings solution = new CountWaysToBuildGoodStrings();

        // 创建测试用例1
        // 预期输出: 8
        int low1 = 3;
        int high1 = 3;
        int zero1 = 1;
        int one1 = 1;
        int result1 = solution.countGoodStrings(low1, high1, zero1, one1);
        System.out.println(result1);

        // 创建测试用例2
        // 预期输出: 5
        int low2 = 2;
        int high2
                = 3;
        int zero2 = 1;
        int one2 = 2;
        int result2 = solution.countGoodStrings(low2, high2, zero2, one2);
        System.out.println(result2);
    }


}