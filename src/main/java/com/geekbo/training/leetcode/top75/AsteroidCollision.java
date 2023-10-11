package com.geekbo.training.leetcode.top75;

import java.util.Arrays;
import java.util.Stack;

/**
 * 735. Asteroid Collision
 *
 * We are given an array asteroids of integers representing asteroids in a row.
 *
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
 *
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 *
 *
 *
 * Example 1:
 *
 * Input: asteroids = [5,10,-5]
 * Output: [5,10]
 * Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
 * Example 2:
 *
 * Input: asteroids = [8,-8]
 * Output: []
 * Explanation: The 8 and -8 collide exploding each other.
 * Example 3:
 *
 * Input: asteroids = [10,2,-5]
 * Output: [10]
 * Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 *
 * 使用栈来解决。遍历给定的行星数组，对于每个行星，有以下情况：
 *
 * 如果栈为空，直接将当前行星入栈。
 * 如果栈不为空，分以下几种情况讨论：
 * a. 如果当前行星是正向的（向右移动），则直接入栈。
 * b. 如果当前行星是负向的（向左移动），需要与栈顶的行星比较：
 * 如果栈顶行星是正向的，它们会相撞。比较它们的大小，较小的行星爆炸，继续比较当前行星与栈内下一个行星。
 * 如果栈顶行星是负向的，或者栈为空，当前行星直接入栈。
 * 最后，栈内剩余的行星就是最终状态的行星。
 *
 * 算法复杂度分析：
 *
 * 时间复杂度：遍历一次输入数组，每个行星最多入栈和出栈一次，所以时间复杂度为 O(n)，其中 n 是行星数组的长度。
 * 空间复杂度：使用了一个栈来存储行星，最坏情况下需要存储所有行星，所以空间复杂度也为 O(n)。
 *
 */
public class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int asteroid : asteroids) {
            // Handle the current asteroid.
            while (!stack.isEmpty() && stack.peek() > 0 && asteroid < 0) {
                int prevAsteroid = stack.pop();
                if (prevAsteroid == -asteroid) {
                    // Both asteroids explode.
                    asteroid = 0;
                    break;
                } else if (prevAsteroid > -asteroid) {
                    // Previous asteroid survives.
                    stack.push(prevAsteroid);
                    asteroid = 0;
                }
            }
            if (asteroid != 0) {
                stack.push(asteroid);
            }
        }

        // Convert the stack to an array.
        int[] result = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;
    }

    public static void main(String[] args) {
        AsteroidCollision solution = new AsteroidCollision();

        int[] asteroids1 = {5, 10, -5};
        int[] result1 = solution.asteroidCollision(asteroids1);
        System.out.println(Arrays.toString(result1)); // Output: [5, 10]

        int[] asteroids2 = {8, -8};
        int[] result2 = solution.asteroidCollision(asteroids2);
        System.out.println(Arrays.toString(result2)); // Output: []

        int[] asteroids3 = {10, 2, -5};
        int[] result3 = solution.asteroidCollision(asteroids3);
        System.out.println(Arrays.toString(result3)); // Output: [10]

        int[] asteroids4 = {-2, -1, 1, 2};
        int[] result4 = solution.asteroidCollision(asteroids4);
        System.out.println(Arrays.toString(result4)); // Output: [-2, -1, 1, 2]
    }
}
