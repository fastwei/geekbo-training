package com.geekbo.training.leetcode.daily;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 414. Third Maximum Number
 * Solved
 * Easy
 * Given an integer array nums, return the third distinct maximum number in this array.
 * If the third maximum does not exist, return the maximum number.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,1]
 * Output: 1
 * Explanation:
 * The first distinct maximum is 3.
 * The second distinct maximum is 2.
 * The third distinct maximum is 1.
 * Example 2:
 * <p>
 * Input: nums = [1,2]
 * Output: 2
 * Explanation:
 * The first distinct maximum is 2.
 * The second distinct maximum is 1.
 * The third distinct maximum does not exist, so the maximum (2) is returned instead.
 * Example 3:
 * <p>
 * Input: nums = [2,2,3,1]
 * Output: 1
 * Explanation:
 * The first distinct maximum is 3.
 * The second distinct maximum is 2 (both 2's are counted together since they have the same value).
 * The third distinct maximum is 1.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 * <p>
 * <p>
 * Follow up: Can you find an O(n) solution?
 */
public class ThirdMaximumNumber {

    /**
     * 这个算法的时间复杂度是 O(n)，其中 n 是输入数组的长度。
     * <p>
     * 在这个算法中，我们只对输入数组进行了一次遍历。在遍历过程中，我们根据当前元素与当前最大值的比较来更新最大值和次大值。我们只保留了三个不同的最大值，而忽略了重复的元素。因此，算法的时间复杂度是线性的，与输入数组的长度成正比。
     * <p>
     * 除了遍历数组之外，算法中的其他操作，比如比较元素和更新最大值，都是常数时间的操作。因此，这个算法的总时间复杂度是 O(n)。
     * <p>
     * 值得注意的是，这个算法没有使用任何额外的数据结构，只使用了常数个变量来维护最大值和次大值。这使得算法的空间复杂度为 O(1)，也就是常数级别的空间消耗。
     *
     * @param nums
     * @return
     */
    public int thirdMax(int[] nums) {
        Integer max1 = null;
        Integer max2 = null;
        Integer max3 = null;

        for (Integer num : nums) {
            if (num.equals(max1) || num.equals(max2) || num.equals(max3)) {
                continue;
            }

            if (max1 == null || num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (max2 == null || num > max2) {
                max3 = max2;
                max2 = num;
            } else if (max3 == null || num > max3) {
                max3 = num;
            }
        }

        return max3 != null ? max3 : max1;
    }

    public int thirdMax2(int[] nums) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        Set<Integer> distinctNums = new HashSet<>();

        for (int num : nums) {
            if (!distinctNums.contains(num)) {
                heap.offer(num);
                distinctNums.add(num);
                if (heap.size() > 3) {
                    heap.poll();
                }
            }
        }

        if (heap.size() == 3) {
            return heap.poll();
        } else {
            int max = Integer.MIN_VALUE;
            while (!heap.isEmpty()) {
                max = Math.max(max, heap.poll());
            }
            return max;
        }
    }

    public static void main(String[] args) {
        ThirdMaximumNumber solution = new ThirdMaximumNumber();

        int[] nums = {3, 2, 1};
        int result = solution.thirdMax(nums);
        System.out.println(result); // Output: 1
    }
}