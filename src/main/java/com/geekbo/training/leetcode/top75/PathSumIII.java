package com.geekbo.training.leetcode.top75;


import java.util.HashMap;
import java.util.Map;

/**
 * 437. Path Sum III
 * Given the root of a binary tree and an integer targetSum,
 * return the number of paths where the sum of the values along the path equals targetSum.
 * <p>
 * The path does not need to start or end at the root or a leaf,
 * but it must go downwards (i.e., traveling only from parent nodes to child nodes).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * Output: 3
 * Explanation: The paths that sum to 8 are shown.
 * Example 2:
 * <p>
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: 3
 * <p>
 * <p>
 * 解题思路：
 * <p>
 * 使用递归方法遍历二叉树，对于每个节点，分别计算包含该节点的路径和等于targetSum的数量。
 * 在递归过程中，对于每个节点，分别检查包含该节点的路径中是否有满足条件的路径，同时递归遍历左子树和右子树。
 * 最终返回总的路径数量。
 * 算法复杂度分析：
 * <p>
 * 时间复杂度：O(N^2)，其中N是二叉树中的节点数量。
 * 空间复杂度：O(H)，其中H是二叉树的高度。
 * 为了进一步优化算法性能，考虑使用以下方法：
 * <p>
 * Memoization（记忆化）：在递归解法中，使用一个哈希表来存储已经计算过的子问题的结果，避免重复计算，从而减少时间复杂度。
 * <p>
 * 迭代解法：考虑使用循环（迭代）来替代递归，因为递归可能会导致堆栈溢出，尤其在树非常深的情况下。你可以使用栈或队列来迭代遍历树的节点。
 * <p>
 * 剪枝：在遍历树的过程中，如果已经明显知道某个分支不会满足条件，可以剪掉这个分支，减少不必要的计算。
 * <p>
 * 并行化：如果你的应用允许并行计算，可以考虑使用多线程或并发处理来加速计算过程。
 * <p>
 * 空间复杂度优化：优化算法的同时也要考虑空间复杂度。尽量减少不必要的内存消耗。
 * <p>
 * 算法改进：如果存在更高效的算法，可以考虑采用新的算法来解决问题，而不是对现有算法进行微小的优化。
 *
 * 对于给定的测试用例[1000000000,1000000000,null,294967296,null,1000000000,null,1000000000,null,1000000000]，以及之前的算法，会出现栈溢出的问题，因为这个二叉树非常大。
 *
 * 为了解决这个问题，可以使用递归+缓存的方法来优化算法，避免重复计算。
 *
 * 该树非常大，会导致整数溢出的问题。在 Java 中，整数的范围是有限的，不支持处理如此大的整数。因此，需要采用其他方法来处理此情况，例如使用 long 类型来存储前缀和。
 */
public class PathSumIII {

    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0L, 1); // 初始状态，前缀和为0的路径有1条
        return countPathsWithSum(root, 0L, targetSum, prefixSumCount);
    }

    private int countPathsWithSum(TreeNode node, long currentSum, int targetSum, Map<Long, Integer> prefixSumCount) {
        if (node == null) {
            return 0;
        }

        // 计算当前节点的前缀和
        currentSum += node.val;

        // 计算以当前节点为终点的路径数
        int count = prefixSumCount.getOrDefault(currentSum - targetSum, 0);

        // 更新前缀和计数
        prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0) + 1);

        // 递归处理左子树和右子树
        count += countPathsWithSum(node.left, currentSum, targetSum, prefixSumCount);
        count += countPathsWithSum(node.right, currentSum, targetSum, prefixSumCount);

        // 恢复前缀和计数，回溯到上一层
        prefixSumCount.put(currentSum, prefixSumCount.get(currentSum) - 1);

        return count;
    }

    public static void main(String[] args) {
        PathSumIII solution = new PathSumIII();

        // 构建测试树
        TreeNode root1 = new TreeNode(10);
        root1.left = new TreeNode(5);
        root1.right = new TreeNode(-3);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(2);
        root1.right.right = new TreeNode(11);
        root1.left.left.left = new TreeNode(3);
        root1.left.left.right = new TreeNode(-2);
        root1.left.right.right = new TreeNode(1);

        int targetSum1 = 8;
        int result1 = solution.pathSum(root1, targetSum1);
        System.out.println("Test Case 1: " + result1); // 预期输出：3

        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(4);
        root2.right = new TreeNode(8);
        root2.left.left = new TreeNode(11);
        root2.left.left.left = new TreeNode(7);
        root2.left.left.right = new TreeNode(2);
        root2.right.left = new TreeNode(13);
        root2.right.right = new TreeNode(4);
        root2.right.right.left = new TreeNode(5);
        root2.right.right.right = new TreeNode(1);

        int targetSum2 = 22;
        int result2 = solution.pathSum(root2, targetSum2);
        System.out.println("Test Case 2: " + result2); // 预期输出：3

        TreeNode root = new TreeNode(1000000000);
        root.left = new TreeNode(1000000000);
        root.left.left = new TreeNode(294967296);
        root.left.left.left = new TreeNode(1000000000);
        root.left.left.left.left = new TreeNode(1000000000);
        root.left.left.left.right = new TreeNode(1000000000);

        int targetSum3 = 0;
        int result3 = solution.pathSum(root, targetSum3);
        System.out.println("Test Case3: " + result3); // 预期输出：0
    }
}
