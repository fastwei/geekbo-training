package com.geekbo.training.leetcode.daily;

/**
 * 331. Verify Preorder Serialization of a Binary Tree
 * Medium
 * One way to serialize a binary tree is to use preorder traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as '#'.
 * <p>
 * <p>
 * For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where '#' represents a null node.
 * <p>
 * Given a string of comma-separated values preorder, return true if it is a correct preorder traversal serialization of a binary tree.
 * <p>
 * It is guaranteed that each comma-separated value in the string must be either an integer or a character '#' representing null pointer.
 * <p>
 * You may assume that the input format is always valid.
 * <p>
 * For example, it could never contain two consecutive commas, such as "1,,3".
 * Note: You are not allowed to reconstruct the tree.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * Output: true
 * Example 2:
 * <p>
 * Input: preorder = "1,#"
 * Output: false
 * Example 3:
 * <p>
 * Input: preorder = "9,#,#,1"
 * Output: false
 */
class VerifyPreorderSerializationOfBinaryTree {
    /**
     * 解题思路：
     * <p>
     * 使用前序遍历的方式遍历字符串preorder。
     * 维护一个可用的槽位数量，初始值为1。
     * 遍历字符串preorder的每个节点，对于每个节点，占用一个槽位。
     * 检查是否还有可用的槽位，如果没有，则返回false。
     * 如果节点不为空（节点值不为"#"），则增加两个槽位。
     * 最后，检查是否所有槽位都被占用，如果是，则返回true；否则，返回false。
     *
     * @param preorder
     * @return
     */
    public boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");

        // Initialize the available slots
        int slots = 1;

        // Iterate through the nodes
        for (String node : nodes) {
            // Occupied a slot
            slots--;

            // Check if there are still available slots
            if (slots < 0) {
                return false;
            }

            // Check if the node is not null
            if (!node.equals("#")) {
                // Each non-null node occupies two slots
                slots += 2;
            }
        }

        // All slots should be occupied at the end
        return slots == 0;
    }

    public static void main(String[] args) {
        VerifyPreorderSerializationOfBinaryTree solution = new VerifyPreorderSerializationOfBinaryTree();

        String preorder1 = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        System.out.println(solution.isValidSerialization(preorder1)); // Expected output: true

        String preorder2 = "1,#";
        System.out.println(solution.isValidSerialization(preorder2)); // Expected output: false

        String preorder3 = "9,#,#,1";
        System.out.println(solution.isValidSerialization(preorder3)); // Expected output: false
    }
}
