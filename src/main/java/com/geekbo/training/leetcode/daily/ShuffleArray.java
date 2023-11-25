package com.geekbo.training.leetcode.daily;

import java.util.Arrays;
import java.util.Random;

/**
 * 384. Shuffle an Array
 * Medium
 * Given an integer array nums, design an algorithm to randomly shuffle the array.
 * All permutations of the array should be equally likely as a result of the shuffling.
 * <p>
 * Implement the Solution class:
 * <p>
 * Solution(int[] nums) Initializes the object with the integer array nums.
 * int[] reset() Resets the array to its original configuration and returns it.
 * int[] shuffle() Returns a random shuffling of the array.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["Solution", "shuffle", "reset", "shuffle"]
 * [[[1, 2, 3]], [], [], []]
 * Output
 * [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
 * <p>
 * Explanation
 * Solution solution = new Solution([1, 2, 3]);
 * solution.shuffle();    // Shuffle the array [1,2,3] and return its result.
 * // Any permutation of [1,2,3] must be equally likely to be returned.
 * // Example: return [3, 1, 2]
 * solution.reset();      // Resets the array back to its original configuration [1,2,3]. Return [1, 2, 3]
 * solution.shuffle();    // Returns the random shuffling of array [1,2,3]. Example: return [1, 3, 2]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 50
 * -106 <= nums[i] <= 106
 * All the elements of nums are unique.
 * At most 104 calls in total will be made to reset and shuffle.
 * <p>
 * <p>
 * 解题思路：
 * <p>
 * 在构造函数中，将给定的数组nums复制一份保存为original，这样在调用reset方法时可以返回初始配置的数组。
 * shuffle方法使用Fisher-Yates算法进行随机洗牌。
 * 它从最后一个元素开始，每次选择一个随机索引j（范围是0到i），并将索引i和j处的元素进行交换。
 * 这样可以确保每个排列都等概率地出现。
 */
class ShuffleArray {
    private int[] nums;
    private int[] original;

    public ShuffleArray(int[] nums) {
        this.nums = nums;
        this.original = nums.clone();
    }

    /**
     * 重置数组为初始配置并返回
     */
    public int[] reset() {
        return original;
    }

    /**
     * 返回数组的随机洗牌结果
     */
    public int[] shuffle() {
        Random rand = new Random();
        for (int i = nums.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            swap(nums, i, j);
        }
        return nums;
    }

    /**
     * 交换数组中的两个元素
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        // 测试用例1
        int[] nums1 = {1, 2, 3};
        ShuffleArray solution1 = new ShuffleArray(nums1);
        int[] shuffleResult1 = solution1.shuffle();
        System.out.println("Test Case 1:");
        System.out.println("Expected: [3, 1, 2]");
        System.out.println("Actual: " + Arrays.toString(shuffleResult1));
        int[] resetResult1 = solution1.reset();
        System.out.println("Expected: [1, 2, 3]");
        System.out.println("Actual: " + Arrays.toString(resetResult1));
        int[] shuffleResult2 = solution1.shuffle();
        System.out.println("Expected: [1, 3, 2]");
        System.out.println("Actual: " + Arrays.toString(shuffleResult2));
        System.out.println();

        // 测试用例2
        int[] nums2 = {4, 5, 6, 7, 8};
        ShuffleArray solution2 = new ShuffleArray(nums2);
        int[] shuffleResult3 = solution2.shuffle();
        System.out.println("Test Case 2:");
        System.out.println("Expected: [7, 8, 4, 6, 5]");
        System.out.println("Actual: " + Arrays.toString(shuffleResult3));
        int[] resetResult2 = solution2.reset();
        System.out.println("Expected: [4, 5, 6, 7, 8]");
        System.out.println("Actual: " + Arrays.toString(resetResult2));
        int[] shuffleResult4 = solution2.shuffle();
        System.out.println("Expected: [6, 7, 5, 4, 8]");
        System.out.println("Actual: " + Arrays.toString(shuffleResult4));
    }
}
