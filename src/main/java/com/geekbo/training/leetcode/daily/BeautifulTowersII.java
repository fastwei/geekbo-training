package com.geekbo.training.leetcode.daily;

import java.util.List;
import java.util.Stack;

/**
 *
 * 2866. Beautiful Towers II
 * Solved
 * You are given a 0-indexed array maxHeights of n integers.
 *
 * You are tasked with building n towers in the coordinate line. The ith tower is built at coordinate i and has a height of heights[i].
 *
 * A configuration of towers is beautiful if the following conditions hold:
 *
 * 1 <= heights[i] <= maxHeights[i]
 * heights is a mountain array.
 * Array heights is a mountain if there exists an index i such that:
 *
 * For all 0 < j <= i, heights[j - 1] <= heights[j]
 * For all i <= k < n - 1, heights[k + 1] <= heights[k]
 * Return the maximum possible sum of heights of a beautiful configuration of towers.
 *
 *
 *
 * Example 1:
 *
 * Input: maxHeights = [5,3,4,1,1]
 * Output: 13
 * Explanation: One beautiful configuration with a maximum sum is heights = [5,3,3,1,1]. This configuration is beautiful since:
 * - 1 <= heights[i] <= maxHeights[i]
 * - heights is a mountain of peak i = 0.
 * It can be shown that there exists no other beautiful configuration with a sum of heights greater than 13.
 * Example 2:
 *
 * Input: maxHeights = [6,5,3,9,2,7]
 * Output: 22
 * Explanation: One beautiful configuration with a maximum sum is heights = [3,3,3,9,2,2]. This configuration is beautiful since:
 * - 1 <= heights[i] <= maxHeights[i]
 * - heights is a mountain of peak i = 3.
 * It can be shown that there exists no other beautiful configuration with a sum of heights greater than 22.
 * Example 3:
 *
 * Input: maxHeights = [3,2,5,5,2,3]
 * Output: 18
 * Explanation: One beautiful configuration with a maximum sum is heights = [2,2,5,5,2,2]. This configuration is beautiful since:
 * - 1 <= heights[i] <= maxHeights[i]
 * - heights is a mountain of peak i = 2.
 * Note that, for this configuration, i = 3 can also be considered a peak.
 * It can be shown that there exists no other beautiful configuration with a sum of heights greater than 18.
 *
 *
 * Constraints:
 *
 * 1 <= n == maxHeights <= 105
 * 1 <= maxHeights[i] <= 109
 *
 */
public class BeautifulTowersII {
    /**
     * 计算美丽塔的最大高度和
     * 解题思路： 本题可以使用单调栈和前缀和的思想来解决。
     * <p>
     * 首先，我们使用单调栈来找到每个位置左边的最大高度和右边的最大高度。
     * 栈中保存的是递增的索引，当遇到一个比栈顶元素小的索引时，表示找到了栈顶元素的右边界，可以计算出栈顶元素的右边最大高度。
     * 同理，当遇到一个比栈顶元素大的索引时，表示找到了栈顶元素的左边界，可以计算出栈顶元素的左边最大高度。
     * 在计算左边最大高度时，我们使用一个变量cur来记录当前塔的高度和，每次计算时，将当前塔的高度乘以宽度（即当前位置与栈顶元素的距离），然后累加到cur中。
     * 当遇到一个小于栈顶元素的索引时，表示当前塔的右边界已经确定，我们需要将cur减去那些被弹出的塔的高度和。
     * <p>
     * 计算左边最大高度和时，我们使用一个数组left来保存每个位置的左边最大高度和。
     * <p>
     * 接下来，我们使用同样的方法计算右边的最大高度和。
     * 不过在计算右边最大高度和时，我们需要注意计算的宽度应该取负值，因为我们是从右往左计算的。
     * <p>
     * 最后，我们遍历每个位置，计算美丽塔的最大高度和。
     * 美丽塔的高度必须满足1 <= heights[i] <= maxHeights[i]，所以我们选择左边最大高度和和右边最大高度和中较小的一个作为当前位置的高度。
     * 然后，将左边最大高度和、右边最大高度和和当前位置高度相加，得到美丽塔的高度和，并更新最大高度和的值。
     * <p>
     * 算法复杂度分析：
     * 该算法使用了单调栈来计算左边和右边的最大高度和，所以时间复杂度为O(n)，其中n为maxHeights数组的长度。
     * 同时，使用了一个额外的数组来保存左边的最大高度和，所以空间复杂度为O(n)。
     *
     * @param maxHeights 最大高度数组
     * @return 最大高度和
     */
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();

        long[] left = new long[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        long res = 0, cur = 0;

        // 计算左边的最大高度和
        for (int i = 0; i < n; i++) {
            while (stack.size() > 1 && maxHeights.get(stack.peek()) > maxHeights.get(i)) {
                int j = stack.pop();
                cur -= 1L * (j - stack.peek()) * maxHeights.get(j);
            }
            cur += 1L * (i - stack.peek()) * maxHeights.get(i);
            stack.push(i);
            left[i] = cur;
        }

        stack.clear();
        stack.push(n);
        cur = 0;

        // 计算右边的最大高度和，并计算最大高度和
        for (int i = n - 1; i >= 0; i--) {
            while (stack.size() > 1 && maxHeights.get(stack.peek()) > maxHeights.get(i)) {
                int j = stack.pop();
                cur -= 1L * -(j - stack.peek()) * maxHeights.get(j);
            }
            cur += 1L * -(i - stack.peek()) * maxHeights.get(i);
            stack.push(i);
            res = Math.max(res, left[i] + cur - maxHeights.get(i));
        }

        return res;
    }

    public static void main(String[] args) {
        // 测试用例
        List<Integer> maxHeights1 = List.of(5, 3, 4, 1, 1);
        // 预期输出：13
        System.out.println(new BeautifulTowersII().maximumSumOfHeights(maxHeights1));

        List<Integer> maxHeights2 = List.of(6, 5, 3, 9, 2, 7);
        // 预期输出：22
        System.out.println(new BeautifulTowersII().maximumSumOfHeights(maxHeights2));

        List<Integer> maxHeights3 = List.of(3, 2, 5, 5, 2, 3);
        // 预期输出：18
        System.out.println(new BeautifulTowersII().maximumSumOfHeights(maxHeights3));
    }
}
