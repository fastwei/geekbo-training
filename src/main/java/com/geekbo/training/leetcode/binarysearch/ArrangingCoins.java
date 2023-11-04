package com.geekbo.training.leetcode.binarysearch;

/**
 * You have n coins and you want to build a staircase with these coins.
 * The staircase consists of k rows where the ith row has exactly i coins.
 * The last row of the staircase may be incomplete.
 * <p>
 * Given the integer n, return the number of complete rows of the staircase you will build.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 5
 * Output: 2
 * Explanation: Because the 3rd row is incomplete, we return 2.
 * Example 2:
 * <p>
 * <p>
 * Input: n = 8
 * Output: 3
 * Explanation: Because the 4th row is incomplete, we return 3.
 */
public class ArrangingCoins {
    /**
     * 优化算法：
     * <p>
     * 我们可以使用二分查找来优化算法，以减少时间复杂度。
     * 首先，我们将查找范围初始化为0到n。
     * 在每次迭代中，我们计算中间位置mid，并计算以mid为行数时所需的硬币数量curr。
     * 如果curr等于n，则找到了完整的行数，直接返回mid。
     * 如果curr小于n，则说明mid行不够，我们需要在右侧继续搜索，将起始位置start更新为mid + 1。
     * 如果curr大于n，则说明mid行过多，我们需要在左侧继续搜索，将结束位置end更新为mid - 1。
     * 当start大于end时，循环结束，此时end即为最后一个完整行的行数。
     * 最后，我们返回end作为完整行的数量。
     * 优化后的算法使用了二分查找，时间复杂度为O(log(n))，空间复杂度为O(1)。
     *
     * @param n
     * @return
     */

    public static int arrangeCoins(int n) {
        // Initialize the start and end pointers for binary search
        long start = 0;
        long end = n;

        // Perform binary search to find the number of complete rows
        while (start <= end) {
            long mid = start + (end - start) / 2;
            long curr = mid * (mid + 1) / 2;

            if (curr == n) {
                return (int) mid;
            } else if (curr < n) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        // Return the number of complete rows
        return (int) end;
    }

    /**
     * 解题思路：
     * <p>
     * 题目要求我们用给定的n个硬币构建一个阶梯，阶梯由k行组成，其中第i行有i个硬币。最后一行可能是不完整的。
     * 我们可以使用贪心算法来解决这个问题，从第一行开始逐行构建阶梯，直到剩余的硬币不足以构建下一行。
     * 我们使用一个变量completeRows来记录已经构建的完整行的数量。
     * 在每一行中，我们将completeRows增加1，并从总硬币数量n中减去当前行的硬币数量。
     * 当剩余的硬币数量不足以构建下一行时，循环结束。
     * 最后，我们返回completeRows即为构建的完整行的数量。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(sqrt(n))，其中n为总硬币数量。我们最多需要构建sqrt(n)行阶梯。
     * 空间复杂度：O(1)，我们只使用了常量级的额外空间。
     *
     * @param n
     * @return
     */
    public static int arrangeCoinsGreedy(int n) {
        // Initialize the number of complete rows to 0
        int completeRows = 0;

        // Use a while loop to build the staircase
        while (n >= completeRows + 1) {
            // Increment the number of complete rows
            completeRows++;

            // Subtract the number of coins used to build the complete row
            n -= completeRows;
        }

        // Return the number of complete rows
        return completeRows;
    }

    public static void main(String[] args) {
        // Test cases
        int n1 = 5;
        // We can build a complete row with 1 coin and another complete row with 2 coins.
        // The remaining 2 coins are not enough to build another complete row.
        // So the number of complete rows is 2.
        // Expected output: 2
        System.out.println(arrangeCoins(n1));

        int n2 = 8;
        // We can build a complete row with 1 coin, another complete row with 2 coins,
        // and a third complete row with 3 coins. The remaining 2 coins are not enough to build another complete row.
        // So the number of complete rows is 3.
        // Expected output: 3
        System.out.println(arrangeCoins(n2));
    }
}