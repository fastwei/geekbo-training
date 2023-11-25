package com.geekbo.training.leetcode.daily;

import com.geekbo.training.leetcode.base.TreeNode;

/**
 * 1457. Pseudo-Palindromic Paths in a Binary Tree
 * Medium
 * Given a binary tree where node values are digits from 1 to 9.
 * A path in the binary tree is said to be pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.
 * <p>
 * Return the number of pseudo-palindromic paths going from the root node to leaf nodes.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: root = [2,3,1,3,1,null,1]
 * Output: 2
 * Explanation: The figure above represents the given binary tree.
 * There are three paths going from the root node to leaf nodes: the red path [2,3,3], the green path [2,1,1], and the path [2,3,1]. Among these paths only red path and green path are pseudo-palindromic paths since the red path [2,3,3] can be rearranged in [3,2,3] (palindrome) and the green path [2,1,1] can be rearranged in [1,2,1] (palindrome).
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: root = [2,1,1,1,3,null,null,null,null,null,1]
 * Output: 1
 * Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the green path [2,1,1], the path [2,1,3,1], and the path [2,1]. Among these paths only the green path is pseudo-palindromic since [2,1,1] can be rearranged in [1,2,1] (palindrome).
 * Example 3:
 * <p>
 * Input: root = [9]
 * Output: 1
 */
class PseudoPalindromicPathsInBinaryTree {
    int count = 0;

    /**
     * 解题思路：
     * 这道题需要判断从根节点到叶子节点的路径中，是否存在至少一种排列方式使得路径上的节点值是回文串。
     * 我们可以使用深度优先搜索（DFS）来遍历路径，并使用一个数组`freq`来记录每个节点值出现的频率。
     * 当遍历到叶子节点时，判断`freq`数组中出现奇数次的节点值的个数是否小于等于1，
     * 如果是，则说明该路径可以通过排列得到回文串，计数器`count`加1。最后返回计数器`count`的值。
     * <p>
     * 算法复杂度分析：
     * -时间复杂度：我们需要遍历每个节点，所以时间复杂度是O(n)，其中n是二叉树的节点数。
     * -空间复杂度：我们使用了一个数组`freq`来记录节点值的频率，它的长度是固定的，所以空间复杂度是O(1)。
     *
     * @param root
     * @return
     */
    public int pseudoPalindromicPaths(TreeNode root) {
        dfs(root, new int[10]);
        return count;
    }

    private void dfs(TreeNode node, int[] freq) {
        if (node == null) {
            return;
        }

        freq[node.val]++;

        if (node.left == null && node.right == null) {
            if (isPseudoPalindromic(freq)) {
                count++;
            }
        }

        dfs(node.left, freq);
        dfs(node.right, freq);

        freq[node.val]--;
    }

    private boolean isPseudoPalindromic(int[] freq) {
        int oddCount = 0;

        for (int i = 1; i < freq.length; i++) {
            if (freq[i] % 2 != 0) {
                oddCount++;
            }
        }

        return oddCount <= 1;
    }

    public static void main(String[] args) {
        PseudoPalindromicPathsInBinaryTree solution = new PseudoPalindromicPathsInBinaryTree();
        // Test Case 1
        // Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the red path [2,3,3], the green path [2,1,1], and the path [2,3,1]. Among these paths only red path and green path are pseudo-palindromic paths since the red path [2,3,3] can be rearranged in [3,2,3] (palindrome) and the green path [2,1,1] can be rearranged in [1,2,1] (palindrome).
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(1);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(1);
        root1.right.right = new TreeNode(1);
        int expected1 = 2;
        int result1 = solution.pseudoPalindromicPaths(root1);
        System.out.println(result1 == expected1 ? "Test Case 1 Passed" : "Test Case 1 Failed");

        // Test Case 2
        // Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the green path [2,1,1], the path [2,1,3,1], and the path [2,1]. Among these paths only the green path is pseudo-palindromic since [2,1,1] can be rearranged in [1,2,1] (palindrome).
        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(1);
        root2.left.left = new TreeNode(1);
        root2.left.right = new TreeNode(3);
        root2.right.right = new TreeNode(1);
        int expected2 = 1;
        int result2 = solution.pseudoPalindromicPaths(root2);
        System.out.println(result2 == expected2 ? "Test Case 2 Passed" : "Test Case 2 Failed");

        // Test Case 3
        // Explanation: The figure above represents the given binary tree. There is only one path going from the root node to leaf node [9], and it is a pseudo-palindromic path since [9] is already a palindrome.
        TreeNode root3 = new TreeNode(9);
        int expected3 = 1;
        int result3 = solution.pseudoPalindromicPaths(root3);
        System.out.println(result3 == expected3 ? "Test Case 3 Passed" : "Test Case 3 Failed");
    }
}


