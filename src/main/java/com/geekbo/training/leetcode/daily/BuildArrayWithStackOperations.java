package com.geekbo.training.leetcode.daily;

import java.util.*;

/**
 * You are given an integer array target and an integer n.
 * <p>
 * You have an empty stack with the two following operations:
 * <p>
 * "Push": pushes an integer to the top of the stack.
 * "Pop": removes the integer on the top of the stack.
 * You also have a stream of the integers in the range [1, n].
 * <p>
 * Use the two stack operations to make the numbers in the stack (from the bottom to the top)
 * equal to target. You should follow the following rules:
 * <p>
 * If the stream of the integers is not empty, pick the next integer from the stream
 * and push it to the top of the stack.
 * If the stack is not empty, pop the integer at the top of the stack.
 * If, at any moment, the elements in the stack (from the bottom to the top) are equal to target,
 * do not read new integers from the stream and do not do more operations on the stack.
 * Return the stack operations needed to build target following the mentioned rules.
 * If there are multiple valid answers, return any of them.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: target = [1,3], n = 3
 * Output: ["Push","Push","Pop","Push"]
 * Explanation: Initially the stack s is empty. The last element is the top of the stack.
 * Read 1 from the stream and push it to the stack. s = [1].
 * Read 2 from the stream and push it to the stack. s = [1,2].
 * Pop the integer on the top of the stack. s = [1].
 * Read 3 from the stream and push it to the stack. s = [1,3].
 * Example 2:
 * <p>
 * Input: target = [1,2,3], n = 3
 * Output: ["Push","Push","Push"]
 * Explanation: Initially the stack s is empty. The last element is the top of the stack.
 * Read 1 from the stream and push it to the stack. s = [1].
 * Read 2 from the stream and push it to the stack. s = [1,2].
 * Read 3 from the stream and push it to the stack. s = [1,2,3].
 * Example 3:
 * <p>
 * Input: target = [1,2], n = 4
 * Output: ["Push","Push"]
 * Explanation: Initially the stack s is empty. The last element is the top of the stack.
 * Read 1 from the stream and push it to the stack. s = [1].
 * Read 2 from the stream and push it to the stack. s = [1,2].
 * Since the stack (from the bottom to the top) is equal to target, we stop the stack operations.
 * The answers that read integer 3 from the stream are not accepted.
 */
public class BuildArrayWithStackOperations {
    public List<String> buildArray(int[] target, int n) {
        HashSet<Integer> targetSet = new HashSet<>();
        for (int num : target) {
            targetSet.add(num);
        }

        List<String> result = new ArrayList<>();
        for (int i = 1; i <= target[target.length - 1]; i++) {
            if (targetSet.contains(i)) {
                result.add("Push");
            } else {
                result.add("Push");
                result.add("Pop");
            }
        }
        return result;
    }

    public static void main(String[] args) {
        BuildArrayWithStackOperations buildArrayWithStackOperations = new BuildArrayWithStackOperations();

        // Test case 1
        int[] target1 = {1, 3};
        int n1 = 3;
        List<String> expected1 = Arrays.asList("Push", "Push", "Pop", "Push");
        List<String> result1 = buildArrayWithStackOperations.buildArray(target1, n1);
        System.out.println(result1.equals(expected1) ? "Pass" : "Fail");

        // Test case 2
        int[] target2 = {1, 2, 3};
        int n2 = 3;
        List<String> expected2 = Arrays.asList("Push", "Push", "Push");
        List<String> result2 = buildArrayWithStackOperations.buildArray(target2, n2);
        System.out.println(result2.equals(expected2) ? "Pass" : "Fail");

        // Test case 3
        int[] target3 = {1, 2};
        int n3 = 4;
        List<String> expected3 = Arrays.asList("Push", "Push");
        List<String> result3 = buildArrayWithStackOperations.buildArray(target3, n3);
        System.out.println(result3.equals(expected3) ? "Pass" : "Fail");
    }
}