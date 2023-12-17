package com.geekbo.training.leetcode.crackinterview109;

import com.geekbo.training.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 面试题 04.09. 二叉搜索树序列
 * 已解答
 * 困难
 * 从左向右遍历一个数组，通过不断将其中的元素插入树中可以逐步地生成一棵二叉搜索树。
 *
 * 给定一个由不同节点组成的二叉搜索树 root，输出所有可能生成此树的数组。
 *
 *
 *
 * 示例 1:
 *
 * 输入: root = [2,1,3]
 * 输出: [[2,1,3],[2,3,1]]
 * 解释: 数组 [2,1,3]、[2,3,1] 均可以通过从左向右遍历元素插入树中形成以下二叉搜索树
 *        2
 *       / \
 *      1   3
 * 示例 2:
 *
 * 输入: root = [4,1,null,null,3,2]
 * 输出: [[4,1,3,2]]
 *
 *
 * 提示：
 *
 * 二叉搜索树中的节点数在 [0, 1000] 的范围内
 * 1 <= 节点值 <= 10^6
 * 用例保证符合要求的数组数量不超过 5000
 *
 */
class BSTSequences {
    // 这两个声明成全局变量，这也回溯函数就少传点参数
    private LinkedList<Integer> path = new LinkedList<>();
    private List<List<Integer>> result = new LinkedList<>();

    public List<List<Integer>> BSTSequences(TreeNode root) {
        Deque<TreeNode> dq = new LinkedList<>();
        if (root != null) {
            dq.offer(root);
        }
        dfs(dq);
        return result;
    }

    // 1.确定递归函数返回值和参数
    public void dfs(Deque<TreeNode> dq) {
        // 2.确定递归终止条件
        // dq是该层剩下可选节点的候选队列，若队列为空，则说明没有候选元素了
        // 因此可直接把当前路径添加到结果集，然后返回
        if (dq.isEmpty()) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        
        //3.确定回溯函数的遍历过程

        // 当前层可与选择的候选节点的个数
        int size = dq.size();
        while(size > 0) {
            TreeNode cur = dq.pollFirst();
            // 向路径中添加当前值
            path.add(cur.val);
            // 记录添加的子节点数量，等会回溯时需要用
            int children = 0;
            // 向候选队列中添加子节点
            if (cur.left != null) {
                children++;
                dq.offerLast(cur.left);
            }
            if (cur.right != null) {
                children++;
                dq.offerLast(cur.right);
            }

            // 递归
            dfs(dq);

            // 回溯候选队列
            while (children > 0) {
                dq.pollLast();
                children--;
            }
            dq.offerLast(cur);
            
            // 回溯路径
            path.removeLast();
            // 当前节点处理完毕，数量减一
            size--;
        }
    }
}