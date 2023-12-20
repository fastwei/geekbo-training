package com.geekbo.training.leetcode.crackinterview109;

import java.util.List;
import java.util.Stack;

/**
 * 面试题 08.06. 汉诺塔问题
 * 简单
 * 在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。移动圆盘时受到以下限制:
 * (1) 每次只能移动一个盘子;
 * (2) 盘子只能从柱子顶端滑出移到下一根柱子;
 * (3) 盘子只能叠在比它大的盘子上。
 * <p>
 * 请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。
 * <p>
 * 你需要原地修改栈。
 * <p>
 * 示例1:
 * <p>
 * 输入：A = [2, 1, 0], B = [], C = []
 * 输出：C = [2, 1, 0]
 * 示例2:
 * <p>
 * 输入：A = [1, 0], B = [], C = []
 * 输出：C = [1, 0]
 * 提示:
 * <p>
 * A中盘子的数目不大于14个。
 */
public class HanoiTower {

    /**
     * 将源柱子上的n-1个盘子移动到辅助柱子上，然后将源柱子上的最大盘子移动到目标柱子上，最后将辅助柱子上的n-1个盘子移动到目标柱子上。
     * <p>
     * 注意，这里使用的是List<Integer>而不是Stack<Integer>作为柱子的数据结构，所以使用add和remove方法来模拟移动盘子的操作。
     *
     * @param A
     * @param B
     * @param C
     */
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        move(A.size(), A, C, B);
    }

    public void move(int n, List<Integer> source, List<Integer> target, List<Integer> auxiliary) {
        if (n > 0) {
            move(n - 1, source, auxiliary, target);
            target.add(source.remove(source.size() - 1));
            move(n - 1, auxiliary, target, source);
        }
    }

    /**
     * 我们首先创建了三个Stack对象，分别对应A、B、C柱子。
     * 然后，我们将输入的List对象A中的元素逆序地放入stackA中。
     * <p>
     * 接下来，我们调用之前定义的move方法，将stackA中的盘子移动到stackC中。
     * <p>
     * 最后，我们将stackC中的元素逐个弹出，并添加到输出的List对象C中，以满足题目要求的形式。
     * <p>
     * 请注意，这里假设输入的List对象B为空，因为在题目中并没有提到B柱子的初始状态。
     * 如果有初始状态，可以按照类似的方式进行处理。
     *
     * @param A
     * @param B
     * @param C
     */
    public void hanotaStack(List<Integer> A, List<Integer> B, List<Integer> C) {
        Stack<Integer> stackA = new Stack<>();
        Stack<Integer> stackB = new Stack<>();
        Stack<Integer> stackC = new Stack<>();

        // 将List转换为Stack
        for (int i = A.size() - 1; i >= 0; i--) {
            stackA.push(A.get(i));
        }

        move(stackA.size(), stackA, stackC, stackB);

        // 将Stack转换为List
        C.clear();
        while (!stackC.isEmpty()) {
            C.add(stackC.pop());
        }
    }

    /**
     * 解题思路：
     * <p>
     * 使用递归的方式解决汉诺塔问题。
     * 将A柱子上的n-1个盘子移动到B柱子上，再将A柱子上的最大盘子移动到C柱子上，最后将B柱子上的n-1个盘子移动到C柱子上。
     * 递归终止条件是n为0。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：O(2^n)，其中n为盘子的个数，每个盘子都需要移动一次。
     * 空间复杂度：O(n)，递归调用栈的深度为n。
     *
     * @param n
     * @param source
     * @param target
     * @param auxiliary
     */
    public static void moveStack(int n, Stack<Integer> source, Stack<Integer> target, Stack<Integer> auxiliary) {
        if (n > 0) {
            moveStack(n - 1, source, auxiliary, target);
            target.push(source.pop());
            moveStack(n - 1, auxiliary, target, source);
        }
    }

}
