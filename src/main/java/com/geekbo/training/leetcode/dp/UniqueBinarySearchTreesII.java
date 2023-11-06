package com.geekbo.training.leetcode.dp;

import com.geekbo.training.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n, return all the structurally unique BST's (binary search trees),
 * which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
 * Example 1:
 * <p>
 * <p>
 * Input: n = 3
 * Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: [[1]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 8
 */
public class UniqueBinarySearchTreesII {
    /**
     * 返回具有 n 个节点的唯一值的结构化二叉搜索树的列表
     *
     * @param n 节点数
     * @return 结构化二叉搜索树的列表
     */
    public List<TreeNode> generateTrees2(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return generateTreesRecursive(1, n);
    }

    /**
     * 返回具有从 start 到 end 范围内节点的唯一值的结构化二叉搜索树的列表
     * 解题思路：
     * <p>
     * 我们可以使用递归来生成具有 n 个节点的唯一值的结构化二叉搜索树的列表。
     * 对于每个根节点的值 i，我们递归生成左子树的结构化二叉搜索树的列表和右子树的结构化二叉搜索树的列表。
     * 然后，将左子树的每个结点与右子树的每个结点组合，构造以 i 为根节点的结构化二叉搜索树。
     * 将所有的结构化二叉搜索树添加到结果列表中。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 对于每个节点值 i，我们需要递归生成左子树和右子树的结构化二叉搜索树。
     * 假设节点数为 n，则递归的时间复杂度为 O(n^2)。
     * 最终的结果列表中会有 O(G(n)) 个结构化二叉搜索树，其中 G(n) 是卡塔兰数。
     * 在最坏的情况下，G(n) 的数量级为 O(4^n/n^(3/2))。
     * 因此，总的时间复杂度为 O(n^2 * G(n))。
     *
     * @param start 范围的开始值
     * @param end   范围的结束值
     * @return 结构化二叉搜索树的列表
     */
    private List<TreeNode> generateTreesRecursive(int start, int end) {
        List<TreeNode> trees = new ArrayList<>();

        // 如果范围的开始值大于结束值，则将 null 加入到列表中
        if (start > end) {
            trees.add(null);
            return trees;
        }

        // 遍历范围内的每个值
        for (int i = start; i <= end; i++) {
            // 递归生成左子树的结构化二叉搜索树的列表
            List<TreeNode> leftTrees = generateTreesRecursive(start, i - 1);
            // 递归生成右子树的结构化二叉搜索树的列表
            List<TreeNode> rightTrees = generateTreesRecursive(i + 1, end);

            // 将左子树的每个结点与右子树的每个结点组合，构造以 i 为根节点的结构化二叉搜索树
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    trees.add(root);
                }
            }
        }

        return trees;
    }

    /**
     * 返回具有 n 个节点的唯一值的结构化二叉搜索树的列表
     * 解题思路：
     * <p>
     * 我们可以使用动态规划（DP）来生成具有 n 个节点的唯一值的结构化二叉搜索树的列表。
     * 我们定义一个大小为 n+1 的二维数组 dp，其中 dp[i] 存储了具有 i 个节点的唯一值的结构化二叉搜索树的列表。
     * 初始化 dp[0] 为一个空列表。
     * 对于每个节点值 root，我们遍历 1 到 n 的范围，将 root 作为根节点，
     * 分别生成左子树和右子树的结构化二叉搜索树的列表。
     * 然后，将左子树的每个结点与右子树的每个结点组合，构造以 root 为根节点的结构化二叉搜索树，并将其添加到 dp[n] 中。
     * 最后，返回 dp[n]，即具有 n 个节点的唯一值的结构化二叉搜索树的列表。
     * 算法复杂度分析：
     * <p>
     * 动态规划的时间复杂度为
     * <p>
     * O(n^3)，其中 n 是节点数。在生成 dp 数组的过程中，
     * 我们需要遍历每个节点值和每个节点值对应的左子树和右子树，所以时间复杂度为 O(n^3)。
     * <p>
     * 空间复杂度为 O(n^2)，需要额外的二维数组 dp 来存储结构化二叉搜索树的列表。
     *
     * @param n 节点数
     * @return 结构化二叉搜索树的列表
     */
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode>[] dp = new List[n + 1];
        dp[0] = new ArrayList<>();
        if (n == 0) {
            return dp[0];
        }

        dp[0].add(null);

        for (int len = 1; len <= n; len++) {
            dp[len] = new ArrayList<>();
            for (int root = 1; root <= len; root++) {
                int leftLen = root - 1;
                int rightLen = len - root;

                for (TreeNode left : dp[leftLen]) {
                    for (TreeNode right : dp[rightLen]) {
                        TreeNode treeRoot = new TreeNode(root);
                        treeRoot.left = cloneTree(left, 0);
                        treeRoot.right = cloneTree(right, root);
                        dp[len].add(treeRoot);
                    }
                }
            }
        }

        return dp[n];
    }

    private TreeNode cloneTree(TreeNode root, int offset) {
        if (root == null) {
            return null;
        }
        TreeNode newRoot = new TreeNode(root.val + offset);
        newRoot.left = cloneTree(root.left, offset);
        newRoot.right = cloneTree(root.right, offset);
        return newRoot;
    }

    public static void main(String[] args) {
        // 创建解法对象
        UniqueBinarySearchTreesII uniqueBinarySearchTreesII = new UniqueBinarySearchTreesII();

        // 测试用例1
        // 预期输出: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
        int n1 = 3;
        List<TreeNode> result1 = uniqueBinarySearchTreesII.generateTrees(n1);
        for (TreeNode tree : result1) {
            System.out.println(tree);
        }

        // 测试用例2
        // 预期输出: [[1]]
        int n2 = 1;
        List<TreeNode> result2 = uniqueBinarySearchTreesII.generateTrees(n2);
        for (TreeNode tree : result2) {
            System.out.println(tree);
        }
    }
}

