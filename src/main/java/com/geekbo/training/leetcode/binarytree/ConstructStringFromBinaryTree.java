package com.geekbo.training.leetcode.binarytree;

/**
 *
 * 606. Construct String from Binary Tree
 * Easy
 * Given the root of a binary tree, construct a string consisting of parenthesis and integers from a binary tree with the preorder traversal way, and return it.
 *
 * Omit all the empty parenthesis pairs that do not affect the one-to-one mapping relationship between the string and the original binary tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4]
 * Output: "1(2(4))(3)"
 * Explanation: Originally, it needs to be "1(2(4)())(3()())", but you need to omit all the unnecessary empty parenthesis pairs. And it will be "1(2(4))(3)"
 * Example 2:
 *
 *
 * Input: root = [1,2,3,null,4]
 * Output: "1(2()(4))(3)"
 * Explanation: Almost the same as the first example, except we cannot omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -1000 <= Node.val <= 1000
 *
 */
public class ConstructStringFromBinaryTree {

    /**
     * 构造二叉树的字符串表示
     * 
     * @param root 二叉树的根节点
     * @return 构造的字符串
     */
    public String tree2str(TreeNode root) {
        if (root == null) {
            return "";
        }

        String left = tree2str(root.left);
        String right = tree2str(root.right);

        if (left.isEmpty() && right.isEmpty()) {
            return root.val + "";
        } else if (right.isEmpty()) {
            return root.val + "(" + left + ")";
        } else {
            return root.val + "(" + left + ")(" + right + ")";
        }
    }

    public static void main(String[] args) {
        // 创建测试用例
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.right = new TreeNode(4);

        // 测试用例1预期输出: 1(2(4))(3)
        ConstructStringFromBinaryTree solution = new ConstructStringFromBinaryTree();
        String result1 = solution.tree2str(root1);
        System.out.println(result1);

        // 测试用例2预期输出: 1(2()(4))(3)
        String result2 = solution.tree2str(root2);
        System.out.println(result2);
    }
}