package com.geekbo.training.leetcode.daily;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * Given an array of unique integers, arr, where each integer arr[i] is strictly greater than 1.
 *
 * We make a binary tree using these integers, and each number may be used for any number of times. Each non-leaf node's value should be equal to the product of the values of its children.
 *
 * Return the number of binary trees we can make. The answer may be too large so return the answer modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,4]
 * Output: 3
 * Explanation: We can make these trees: [2], [4], [4, 2, 2]
 * Example 2:
 *
 * Input: arr = [2,4,5,10]
 * Output: 7
 * Explanation: We can make these trees: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].
 *
 * todo:加深理解
 */
public class BinaryTreesWithFactors {

    /**
     * 解题思路：
     * <p>
     * 首先对数组进行排序，以便后续的动态规划计算。
     * 使用哈希表存储每个数及其索引，以便快速查找。
     * 定义状态数组dp，其中dp[i]表示以arr[i]为根节点的二叉树个数。
     * 初始时，将dp[i]初始化为1，表示只有根节点。
     * 动态规划求解二叉树个数，遍历数组中的每个数arr[i]，再遍历其之前的数arr[j]，
     * 如果arr[i]能够整除arr[j]，则说明可以构成一棵二叉树。
     * 如果arr[i]/arr[j]在数组中存在，则说明可以将arr[i]作为根节点，arr[j]作为左子树的根节点，
     * arr[i]/arr[j]作为右子树的根节点，此时可以组合出dp[j] * dp[arr[i]/arr[j]]个二叉树。
     * 将所有可以组合出的二叉树个数累加到dp[i]中。
     * 最后将dp数组中的所有元素相加，即为最终的结果。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n^2)，其中n是数组的长度。需要遍历两次数组，每次遍历的时间复 杂度为O(n)，其中n是数组的长度。
     * <p>
     * 空间复杂度：O(n)，需要使用一个哈希表和一个状态数组来存储中间结果。
     * 在给定的代码中，我们首先对数组进行排序，然后使用哈希表存储每个数及其索引。
     * 接下来，我们定义一个状态数组dp，并将其初始化为1，表示只有根节点。
     * 然后，我们使用动态规划的方式遍历数组中的每个数arr[i]，再遍历其之前的数arr[j]，
     * 如果arr[i]能够整除arr[j]，则说明可以构成一棵二叉树。
     * 如果arr[i]/arr[j]在哈希表中存在，则说明可以将arr[i]作为根节点，arr[j]作为左子树的根节点，
     * arr[i]/arr[j]作为右子树的根节点，
     * 此时可以组合出dp[j] * dp[arr[i]/arr[j]]个二叉树。将所有可以组合出的二叉树个数累加到dp[i]中。
     * 最后，将dp数组中的所有元素相加，即为最终的结果。
     *
     * @param arr
     * @return
     */
    public int numFactoredBinaryTrees(int[] arr) {
        // 对数组进行排序
        Arrays.sort(arr);

        // 使用哈希表存储每个数及其索引
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }

        // 定义状态数组
        long[] dp = new long[arr.length];

        // 动态规划求解二叉树个数
        long count = 0;
        for (int i = 0; i < arr.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] % arr[j] == 0) {
                    int right = arr[i] / arr[j];
                    if (map.containsKey(right)) {
                        dp[i] += dp[j] * dp[map.get(right)];
                    }
                }
            }
            count += dp[i];
        }

        // 返回结果取模
        return (int) (count % (int) (1e9 + 7));
    }

    public static void main(String[] args) {
        BinaryTreesWithFactors solution = new BinaryTreesWithFactors();

        // Test Case 1
        int[] arr1 = {2, 4};
        int expected1 = 3;
        int result1 = solution.numFactoredBinaryTrees(arr1);
        System.out.println(result1 == expected1); // true

        // Test Case 2
        int[] arr2 = {2, 4, 5, 10};
        int expected2 = 7;
        int result2 = solution.numFactoredBinaryTrees(arr2);
        System.out.println(result2 == expected2); // true
    }
}
