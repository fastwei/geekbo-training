package com.geekbo.training.leetcode.daily;

import com.geekbo.training.leetcode.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;


/**
 * 297. Serialize and Deserialize Binary Tree
 * Hard
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 *
 */
public class Codec {
    /**
     * The serialize method converts a binary tree into a string representation using a level-order traversal.
     * The resulting string is enclosed in square brackets and each node value is separated by commas.
     * Null nodes are represented by the string "null".
     *
     * The deserialize method converts the serialized string back into a binary tree.
     * It uses a queue to keep track of the nodes and their children.
     * It starts by creating the root node and then iteratively assigns the left and right child values based on the serialized string.
     *
     * The time complexity of both serialize and deserialize methods is O(n), where n is the number of nodes in the binary tree.
     * The space complexity is also O(n) as we use a queue to store the nodes during the deserialization process.
     *
     * @param root
     * @return
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append("null,");
                continue;
            }
            sb.append(node.val).append(",");
            queue.offer(node.left);
            queue.offer(node.right);
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("[]")) {
            return null;
        }

        String[] values = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (!values[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(values[i]));
                queue.offer(node.left);
            }
            i++;

            if (!values[i].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(values[i]));
                queue.offer(node.right);
            }
            i++;
        }

        return root;
    }

    public static void main(String[] args) {
        Codec codec = new Codec();

        // Test case 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(5);

        String serialized1 = codec.serialize(root1);
        TreeNode deserialized1 = codec.deserialize(serialized1);
        String serializedAgain1 = codec.serialize(deserialized1);

        System.out.println("Serialized: " + serialized1);
        System.out.println("Deserialized: " + serializedAgain1);
        System.out.println("Is serialization correct? " + serialized1.equals(serializedAgain1));

        // Test case 2
        TreeNode root2 = null;

        String serialized2 = codec.serialize(root2);
        TreeNode deserialized2 = codec.deserialize(serialized2);
        String serializedAgain2 = codec.serialize(deserialized2);

        System.out.println("Serialized: " + serialized2);
        System.out.println("Deserialized: " + serializedAgain2);
        System.out.println("Is serialization correct? " + serialized2.equals(serializedAgain2));
    }
}

