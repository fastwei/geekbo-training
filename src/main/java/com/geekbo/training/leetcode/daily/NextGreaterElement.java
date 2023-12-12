package com.geekbo.training.leetcode.daily;

import java.util.Arrays;
import java.util.Stack;

/**
 *
 * 2454. Next Greater Element IV
 * Hard
 * You are given a 0-indexed array of non-negative integers nums. For each integer in nums, you must find its respective second greater integer.
 *
 * The second greater integer of nums[i] is nums[j] such that:
 *
 * j > i
 * nums[j] > nums[i]
 * There exists exactly one index k such that nums[k] > nums[i] and i < k < j.
 * If there is no such nums[j], the second greater integer is considered to be -1.
 *
 * For example, in the array [1, 2, 4, 3], the second greater integer of 1 is 4, 2 is 3, and that of 3 and 4 is -1.
 * Return an integer array answer, where answer[i] is the second greater integer of nums[i].
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,4,0,9,6]
 * Output: [9,6,6,-1,-1]
 * Explanation:
 * 0th index: 4 is the first integer greater than 2, and 9 is the second integer greater than 2, to the right of 2.
 * 1st index: 9 is the first, and 6 is the second integer greater than 4, to the right of 4.
 * 2nd index: 9 is the first, and 6 is the second integer greater than 0, to the right of 0.
 * 3rd index: There is no integer greater than 9 to its right, so the second greater integer is considered to be -1.
 * 4th index: There is no integer greater than 6 to its right, so the second greater integer is considered to be -1.
 * Thus, we return [9,6,6,-1,-1].
 * Example 2:
 *
 * Input: nums = [3,3]
 * Output: [-1,-1]
 * Explanation:
 * We return [-1,-1] since neither integer has any integer greater than it.
 *
 */
public class NextGreaterElement {

    /**
     * 找到给定数组A中每个元素的第二大元素。
     * <p>
     * 解题思路： 该算法使用两个栈s1和s2来解决问题。栈s1用于保存当前元素的下标，栈s2用于保存当前元素的第二大元素的下标。
     * <p>
     * 从左往右遍历数组A，对于每个元素A[i]，执行以下操作：
     * <p>
     * 如果栈s2不为空，并且栈顶元素对应的数组元素小于A[i]，则将栈顶元素弹出，并将A[i]赋值给栈顶元素对应的结果数组res中的位置。
     * 如果栈s1不为空，并且栈顶元素对应的数组元素小于A[i]，则将栈顶元素移到临时栈tmp中。
     * 将临时栈tmp中的元素依次弹出，并将它们放入栈s2中。
     * 将当前元素的下标i入栈s1。
     * 遍历完整个数组后，结果数组res中存储的就是每个元素的第二大元素。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n)，其中n为数组A的长度。需要遍历整个数组一次。
     * 空间复杂度：O(n)，使用了三个栈s1、s2和tmp来保存元素的下标。在最坏的情况下，栈s1和s2的大小都可能达到n。
     */
    public int[] secondGreaterElement(int[] A) {
        int n = A.length, res[] = new int[n];
        Arrays.fill(res, -1);
        Stack<Integer> s1 = new Stack<>(), s2 = new Stack<>(), tmp = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!s2.empty() && A[s2.peek()] < A[i])
                res[s2.pop()] = A[i];
            while (!s1.empty() && A[s1.peek()] < A[i])
                tmp.push(s1.pop());
            while (!tmp.empty())
                s2.push(tmp.pop());
            s1.push(i);
        }
        return res;
    }

    /**
     * 解题思路：
     * 使用单调递减栈来解决这个问题。从右往左遍历数组，维护一个单调递减的栈，栈中保存的是数组元素的下标。
     * 遍历到当前元素时，如果栈为空，说明当前元素右边没有比它大的元素，将-1加入结果列表。
     * 如果栈不为空，并且栈顶元素对应的数组元素小于当前元素，说明栈顶元素是当前元素的第一个比它大的元素，
     * 弹出栈顶元素，并将当前元素的下标加入结果列表。
     * 如果栈不为空，并且栈顶元素对应的数组元素大于或等于当前元素，继续弹出栈顶元素，直到栈为空或栈顶元素小于当前元素。
     * 遍历完整个数组后，如果栈不为空，说明栈中剩余的元素右边没有比它们大的元素，将-1加入结果列表。
     * 最后将结果列表进行反转，得到最终答案。
     * <p>
     * 算法复杂度分析：
     * 时间复杂度：O(n)，其中 n 为数组的长度，需要遍历整个数组。
     * 空间复杂度：O(n)，使用了一个栈来保存元素的下标。
     * <p>
     * todo：还有测试用例没有通过。
     */
    public static int[] findSecondMaximum(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);

        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                result[i] = nums[stack.peek()];
            }
            stack.push(i);
        }

        return result;
    }

    public static void main(String[] args) {
        // 测试用例1
        int[] nums1 = {2, 4, 0, 9, 6};
        int[] expected1 = {9, 6, 6, -1, -1};
        int[] result1 = findSecondMaximum(nums1);
        System.out.println("测试用例1：");
        System.out.println("Expected: " + Arrays.toString(expected1));
        System.out.println("Result: " + Arrays.toString(result1));

        // 测试用例2
        int[] nums2 = {3, 3};
        int[] expected2 = {-1, -1};
        int[] result2 = findSecondMaximum(nums2);
        System.out.println("测试用例2：");
        System.out.println("Expected: " + Arrays.toString(expected2));
        System.out.println("Result: " + Arrays.toString(result2));
    }
}