package com.geekbo.training.leetcode.daily;

import java.util.Random;

/**
 * 528. Random Pick with Weight
 * You are given a 0-indexed array of positive integers w where w[i] describes the weight of the ith index.
 * <p>
 * You need to implement the function pickIndex(),
 * which randomly picks an index in the range [0, w.length - 1] (inclusive) and returns it.
 * The probability of picking an index i is w[i] / sum(w).
 * <p>
 * For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) = 0.25 (i.e., 25%),
 * and the probability of picking index 1 is 3 / (1 + 3) = 0.75 (i.e., 75%).
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * Output
 * [null,0]
 * <p>
 * Explanation
 * Solution solution = new Solution([1]);
 * solution.pickIndex(); // return 0. The only option is to return 0 since there is only one element in w.
 * Example 2:
 * <p>
 * Input
 * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]]
 * Output
 * [null,1,1,1,1,0]
 * <p>
 * Explanation
 * Solution solution = new Solution([1, 3]);
 * solution.pickIndex(); // return 1. It is returning the second element (index = 1) that has a probability of 3/4.
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 0. It is returning the first element (index = 0) that has a probability of 1/4.
 * <p>
 * Since this is a randomization problem, multiple answers are allowed.
 * All of the following outputs can be considered correct:
 * [null,1,1,1,1,0]
 * [null,1,1,1,1,1]
 * [null,1,1,1,0,0]
 * [null,1,1,1,0,1]
 * [null,1,0,1,0,0]
 * ......
 * and so on.
 */
public class RandomPickWithWeight {
    private int[] prefixSum;
    private Random random;

    /**
     * 解题思路：
     * <p>
     * 首先，我们需要先计算出累计权重数组 prefixSum，其中 prefixSum[i] 表示索引 i 之前（包括 i）的所有权重之和。
     * 然后，我们生成一个随机数 randomWeight，它的范围是 [0, totalWeight - 1]，其中 totalWeight 是所有权重之和。
     * 接下来，我们使用二分查找算法在 prefixSum 中找到第一个大于等于 randomWeight 的索引位置，
     * 然后返回该索引作为随机选取的索引。
     * 算法复杂度分析：
     * <p>
     * 初始化时间复杂度为 O(n)，其中 n 是权重数组的长度。
     * 每次调用 pickIndex() 方法的时间复杂度为 O(log n)。
     * 注意：
     * <p>
     * 在 pickIndex() 方法中，我们使用的是左闭右开区间的二分查找，
     * 因此当找到第一个大于等于 randomWeight 的索引时，我们返回的是左边界 left，而不是 right。
     * 在测试用例中，由于涉及到随机性，因此结果可能会有多个正确的答案。
     *
     * @param w
     */
    public RandomPickWithWeight(int[] w) {
        prefixSum = new int[w.length];
        random = new Random();

        prefixSum[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + w[i];
        }
    }

    public int pickIndex() {
        int totalWeight = prefixSum[prefixSum.length - 1];
        int randomWeight = random.nextInt(totalWeight);

        int left = 0;
        int right = prefixSum.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (randomWeight >= prefixSum[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        // Test case 1
        int[] w1 = {1};
        RandomPickWithWeight randomPickWithWeight1 = new RandomPickWithWeight(w1);
        // Expected output: 0
        System.out.println(randomPickWithWeight1.pickIndex());

        // Test case 2
        int[] w2 = {1, 3};
        RandomPickWithWeight randomPickWithWeight2 = new RandomPickWithWeight(w2);
        // Expected output: 1 1 1 1 0 (randomized)
        for (int i = 0; i < 5; i++) {
            System.out.println(randomPickWithWeight2.pickIndex());
        }
    }
}
