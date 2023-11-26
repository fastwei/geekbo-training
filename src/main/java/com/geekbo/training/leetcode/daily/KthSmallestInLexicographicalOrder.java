package com.geekbo.training.leetcode.daily;

/**
 * 440. K-th Smallest in Lexicographical Order
 * Hard
 * Given two integers n and k, return the kth lexicographically smallest integer in the range [1, n].
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 13, k = 2
 * Output: 10
 * Explanation: The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.
 * Example 2:
 * <p>
 * Input: n = 1, k = 1
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= n <= 109
 */
public class KthSmallestInLexicographicalOrder {
    /**
     * 解题思路： 这个问题可以使用深度优先搜索（DFS）的方法来生成范围[1, n]中按字典顺序排列的最小整数。我们将跟踪生成的整数的计数，并在达到第k个整数时停止。
     * <p>
     * 具体步骤如下：
     * <p>
     * 初始化当前数字curr为1，将k减1（索引从0开始）。
     * 在每次迭代中，通过calculateSteps函数计算从curr到curr+1需要的步数。
     * 如果步数小于等于k，则将curr增加1，将k减去步数。
     * 如果步数大于k，则将curr乘以10，即进入字典顺序的下一层，并将k减1。
     * 继续这个过程直到k减为0。
     * 最终的curr的值就是范围[1, n]中的第k小的整数，将其作为结果返回。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：这个解决方案的时间复杂度是O(log n)，因为到达第k个整数所需的步数是n的对数级别的。
     * 空间复杂度：空间复杂度为O(1)，因为我们没有使用额外的数据结构。
     * <p>
     * 总结：
     * <p>
     * 使用深度优先搜索（DFS）的思路，按照字典顺序生成范围[1, n]中的最小整数。
     * 时间复杂度为O(log n)，空间复杂度为O(1)。
     *
     * @param n
     * @param k
     * @return
     */
    public int findKthNumber(int n, int k) {
        int curr = 1;
        k--;

        while (k > 0) {
            int steps = calculateSteps(n, curr, curr + 1);

            if (steps <= k) {
                curr++;
                k -= steps;
            } else {
                curr *= 10;
                k--;
            }
        }

        return curr;
    }

    private int calculateSteps(int n, long curr, long next) {
        int steps = 0;

        while (curr <= n) {
            steps += Math.min(n + 1, next) - curr;
            curr *= 10;
            next *= 10;
        }

        return steps;
    }

    public static void main(String[] args) {
        KthSmallestInLexicographicalOrder solution = new KthSmallestInLexicographicalOrder();

        // Test case 1
        int n1 = 13;
        int k1 = 2;
        int result1 = solution.findKthNumber(n1, k1);
        System.out.println("Test case 1:");
        System.out.println(result1); // Expected output: 10

        // Test case 2
        int n2 = 1;
        int k2 = 1;
        int result2 = solution.findKthNumber(n2, k2);
        System.out.println("Test case 2:");
        System.out.println(result2); // Expected output: 1
    }
}