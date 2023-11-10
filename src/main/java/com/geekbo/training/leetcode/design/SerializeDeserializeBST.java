package com.geekbo.training.leetcode.design;

import com.geekbo.training.leetcode.base.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeDeserializeBST {

    /**
     * 序列化二叉搜索树为字符串
     *
     * @param root 根节点
     * @return 序列化后的字符串
     */
    public static String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private static void serializeHelper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        sb.append(node.val).append(",");
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);
    }

    /**
     * 将字符串反序列化为二叉搜索树
     *
     * @param data 字符串
     * @return 反序列化后的二叉搜索树的根节点
     */
    public static TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] values = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(values));
        return deserializeHelper(queue, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static TreeNode deserializeHelper(Queue<String> queue, int lower, int upper) {
        if (queue.isEmpty()) {
            return null;
        }
        String value = queue.peek();
        int val = Integer.parseInt(value);
        if (val < lower || val > upper) {
            return null;
        }
        queue.poll();
        TreeNode node = new TreeNode(val);
        node.left = deserializeHelper(queue, lower, val);
        node.right = deserializeHelper(queue, val, upper);
        return node;
    }

    public static void main(String[] args) {
        // 构造二叉搜索树 [2, 1, 3]
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        // 测试序列化
        String serialized = serialize(root);
        System.out.println(serialized); // 输出：2,1,3

        // 测试反序列化
        TreeNode deserialized = deserialize(serialized);
        System.out.println(deserialized.val); // 输出：2
        System.out.println(deserialized.left.val); // 输出：1
        System.out.println(deserialized.right.val); // 输出：3
    }
}