package com.geekbo.training.leetcode.daily;

import com.geekbo.training.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given the root of a binary search tree (BST) with duplicates,
 * return all the mode(s) (i.e., the most frequently occurred element) in it.
 * <p>
 * If the tree has more than one mode, return them in any order.
 * <p>
 * Assume a BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 * The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,null,2,2]
 * Output: [2]
 * Example 2:
 * <p>
 * Input: root = [0]
 * Output: [0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 104].
 * -105 <= Node.val <= 105
 * <p>
 * <p>
 * Follow up: Could you do that without using any extra space?
 * (Assume that the implicit stack space incurred due to recursion does not count).
 */
public class FindModeInBinarySearchTree {
    // 定义全局变量，用于保存出现次数最多的元素
    private static List<Integer> modes;
    // 定义全局变量，用于记录当前元素的出现次数
    private static int currentCount;
    // 定义全局变量，用于记录当前元素的上一个节点的值
    private static int prevValue;
    // 定义全局变量，用于记录当前最大的出现次数
    private static int maxCount;

    /**
     * 解题思路： 首先，由于给定的树是二叉搜索树，其中序遍历的结果是一个递增的有序序列。
     * 我们可以通过中序遍历来遍历整个树，并统计每个元素的出现次数。
     * <p>
     * 在中序遍历的过程中，我们使用三个全局变量来记录当前元素的出现次数、当前最大的出现次数以及出现次数最多的元素。
     * 每次遍历到一个新的节点时，我们判断该节点的值与前一个节点的值是否相等，
     * 如果相等，说明当前元素出现的次数加1；如果不相等，说明遇到了一个新的元素，需要将当前元素的出现次数重置为1。
     * 同时，我们还需要更新当前最大的出现次数和出现次数最多的元素。
     * <p>
     * 该算法的时间复杂度为 O(n)。
     * <p>
     * 另外，为了保存出现次数最多的元素，我们使用了一个列表来存储结果。
     * 列表的大小最多为树的高度，即 O(log n)。所以，该算法的空间复杂度为 O(log n)。
     * <p>
     * 需要注意的是，这里的空间复杂度不包括返回的结果数组的空间，因为该数组是作为返回值返回的，不计入空间复杂度的计算。
     * <p>
     * 综上所述，该算法的时间复杂度为 O(n)，空间复杂度为 O(log n)。
     *
     * @param root
     * @return
     */
    public static int[] findMode(TreeNode root) {
        modes = new ArrayList<>();
        currentCount = 0;
        prevValue = Integer.MIN_VALUE;
        maxCount = 0;

        inorderTraversal(root);

        // 将出现次数最多的元素转换为数组
        int[] result = new int[modes.size()];
        for (int i = 0; i < modes.size(); i++) {
            result[i] = modes.get(i);
        }

        return result;
    }

    private static void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        inorderTraversal(root.left);

        // 处理当前节点的值
        if (root.val == prevValue) {
            currentCount++;
        } else {
            currentCount = 1;
            prevValue = root.val;
        }

        // 更新出现次数最多的元素
        if (currentCount > maxCount) {
            modes.clear();
            modes.add(root.val);
            maxCount = currentCount;
        } else if (currentCount == maxCount) {
            modes.add(root.val);
        }

        inorderTraversal(root.right);
    }

    public static void main(String[] args) {
        // 创建测试用例
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(2);

        // 调用方法，获取结果
        int[] result = findMode(root);

        // 打印结果
        System.out.println(Arrays.toString(result));
        // 预期输出：[2]
    }
}
