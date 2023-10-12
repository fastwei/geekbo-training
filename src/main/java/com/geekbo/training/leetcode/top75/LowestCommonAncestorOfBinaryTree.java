package com.geekbo.training.leetcode.top75;

/**
 * 236. Lowest Common Ancestor of a Binary Tree
 * <p>
 * <p>
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * <p>
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * <p>
 * Example 2:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 * <p>
 * Example 3:
 * <p>
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 * <p>
 * Lowest Common Ancestor of a Binary Tree
 * <p>
 * 给定一个二叉树，找到两个给定节点的最低共同祖先（LCA）。
 * <p>
 * 根据维基百科对LCA的定义：“最低共同祖先是在T中具有p和q作为后代的最低节点
 * （其中我们允许节点作为自身的后代）。”
 * <p>
 * 解题思路：
 * <p>
 * 从根节点开始，递归地搜索左子树和右子树，直到找到节点p或q，或者到达叶子节点。
 * <p>
 * 如果当前节点等于p或q，直接返回当前节点，因为它本身就是最低共同祖先。
 * <p>
 * 如果左子树和右子树都不为空，说明p和q分别在左右子树中，当前节点就是最低共同祖先。
 * <p>
 * 如果左子树为空，那么最低共同祖先一定在右子树中，返回右子树的结果。
 * <p>
 * 如果右子树为空，那么最低共同祖先一定在左子树中，返回左子树的结果。
 * <p>
 * 算法复杂度分析：
 * <p>
 * 时间复杂度：O(N)，其中N是二叉树的节点数。我们需要遍历每个节点来查找p和q。
 * 空间复杂度：O(H)，其中H是二叉树的高度。递归调用栈的深度最多为树的高度。
 * 这个算法的时间复杂度和空间复杂度都是很高效的，适用于大多数二叉树。
 */
public class LowestCommonAncestorOfBinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 如果根节点为空，返回null
        if (root == null) {
            return null;
        }

        // 如果根节点等于p或q，直接返回根节点
        if (root == p || root == q) {
            return root;
        }

        // 递归搜索左子树和右子树
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 如果左子树和右子树都不为空，说明p和q分别在左右子树中，根节点就是最低共同祖先
        if (left != null && right != null) {
            return root;
        }

        // 否则，返回不为空的子树
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        // 创建示例树
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        LowestCommonAncestorOfBinaryTree solver = new LowestCommonAncestorOfBinaryTree();

        // 测试用例
        TreeNode result1 = solver.lowestCommonAncestor(root, root.left, root.right);
        TreeNode result2 = solver.lowestCommonAncestor(root, root.left, root.left.right.right);
        TreeNode result3 = solver.lowestCommonAncestor(root, root, root.left);

        System.out.println("Example 1 Output: " + result1.val);
        System.out.println("Example 2 Output: " + result2.val);
        System.out.println("Example 3 Output: " + result3.val);
    }
}
