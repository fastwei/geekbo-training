package com.geekbo.training.leetcode.crackinterview109;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 面试题 16.11. 跳水板
 * 简单
 * 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
 * <p>
 * 返回的长度需要从小到大排列。
 * <p>
 * 示例 1
 * <p>
 * 输入：
 * shorter = 1
 * longer = 2
 * k = 3
 * 输出： [3,4,5,6]
 * 解释：
 * 可以使用 3 次 shorter，得到结果 3；使用 2 次 shorter 和 1 次 longer，得到结果 4 。以此类推，得到最终结果。
 * 提示：
 * <p>
 * 0 < shorter <= longer
 * 0 <= k <= 100000
 */
class DivingBoardLengths {
    /**
     * 生成跳水板所有可能的长度
     * 解题思路： 我们可以通过遍历所有可能的组合来生成跳水板的长度。
     * 假设使用shorter木板的次数为i，那么使用longer木板的次数为k - i。
     * 对于每一种组合，我们可以计算出对应的跳水板长度，并将其存储在一个列表中。
     * 最后，我们将列表中的长度进行排序，即可得到从小到大排列的结果。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历所有组合的时间复杂度为O(k)，排序的时间复杂度为O(klogk)。
     * 因此，总的时间复杂度为O(k + klogk) = O(klogk)。
     * 空间复杂度：存储跳水板长度的列表的空间复杂度为O(k)。
     *
     * @param shorter 较短的木板长度
     * @param longer  较长的木板长度
     * @param k       使用的木板数量
     * @return 跳水板所有可能的长度，从小到大排列
     */
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }

        if (shorter == longer) {
            return new int[]{shorter * k};
        }

        List<Integer> lengths = new ArrayList<>();
        for (int i = 0; i <= k; i++) {
            int length = i * longer + (k - i) * shorter;
            lengths.add(length);
        }

        int[] result = new int[lengths.size()];
        for (int i = 0; i < lengths.size(); i++) {
            result[i] = lengths.get(i);
        }

        return result;
    }

    public static void main(String[] args) {
        DivingBoardLengths solution = new DivingBoardLengths();
        int shorter = 1;
        int longer = 2;
        int k = 3;
        int[] result = solution.divingBoard(shorter, longer, k);
        System.out.println(Arrays.toString(result));
        // 预期输出：[3, 4, 5, 6]
    }
}