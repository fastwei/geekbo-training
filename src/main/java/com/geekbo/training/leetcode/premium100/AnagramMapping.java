package com.geekbo.training.leetcode.premium100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 760. 找出变位映射
 * 简单
 * 给定两个列表 Aand B，并且 B 是 A 的变位（即 B 是由 A 中的元素随机排列后组成的新列表）。
 *
 * 我们希望找出一个从 A 到 B 的索引映射 P 。一个映射 P[i] = j 指的是列表 A 中的第 i 个元素出现于列表 B 中的第 j 个元素上。
 *
 * 列表 A 和 B 可能出现重复元素。如果有多于一种答案，输出任意一种。
 *
 * 例如，给定
 *
 * A = [12, 28, 46, 32, 50]
 * B = [50, 12, 32, 46, 28]
 *
 *
 * 需要返回
 *
 * [1, 4, 3, 2, 0]
 * P[0] = 1 ，因为 A 中的第 0 个元素出现于 B[1]，而且 P[1] = 4 因为 A 中第 1 个元素出现于 B[4]，以此类推。
 *
 *
 *
 * 注：
 *
 * A, B 有相同的长度，范围为 [1, 100]。
 * A[i], B[i] 都是范围在 [0, 10^5] 的整数。
 *
 */
class AnagramMapping {

    /**
     * 解题思路： 这个问题可以通过构建一个哈希映射来解决。我们可以遍历列表 B，并将每个元素与其索引存储在哈希映射中。
     * 然后，我们遍历列表 A，并通过哈希映射找到每个元素在列表 B 中的索引，并将其存储在结果数组 P 中。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：O(n)，其中 n 是列表 A 和 B 的长度。
     * 空间复杂度：O(n)，用于存储哈希映射。
     *
     * @param A
     * @param B
     * @return
     */
    public int[] anagramMappings(int[] A, int[] B) {
        int[] P = new int[A.length];
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < B.length; i++) {
            map.put(B[i], i);
        }

        for (int i = 0; i < A.length; i++) {
            P[i] = map.get(A[i]);
        }

        return P;
    }

    public static void main(String[] args) {
        AnagramMapping solution = new AnagramMapping();

        int[] A = {12, 28, 46, 32, 50};
        int[] B = {50, 12, 32, 46, 28};

        int[] P = solution.anagramMappings(A, B);

        System.out.println(Arrays.toString(P));
    }
}
